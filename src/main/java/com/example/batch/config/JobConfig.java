package com.example.batch.config;/*
 * @created 20/05/2025- 2:58 PM
 * @project spring-batch-demo
 * @author bziche
 */

import com.example.batch.domain.Person;
import com.example.batch.listener.JobCompetitionNotificationListener;
import com.example.batch.processor.PersonItemProcessor;
import com.example.batch.reader.PersonItemReader;
import com.example.batch.tasklet.CleanupTasklet;
import com.example.batch.writer.PersonItemWriter;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class JobConfig {
    /* Created by bziche on 5/20/2025 */

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    private final PersonItemReader reader;
    private final PersonItemProcessor processor;
    private final PersonItemWriter writer;

   /* private final FlatFileItemReader<Person> reader;
    private final PersonItemProcessor processor;*/
    private final JpaItemWriter<Person> writerJpa;



    @Bean
    public Job importUserJob(
                             JobCompetitionNotificationListener listener
                            ,EmailCheckDecider decider) {
        return new JobBuilder("importUserJob", jobRepository)
                .listener(listener)
                .start(cleanupStep())
                .next(step1(reader, processor, writer))
                .next(decider)
                .from(decider).on("CONTINUE").to(step2())
                .from(decider).on("STOP").end()
                .end()
                .build();
    }

    @Bean
    public Step step1(
             PersonItemReader reader,
     PersonItemProcessor processor,
     PersonItemWriter writer
                     /* FlatFileItemReader<Person> reader,
                      PersonItemProcessor processor,
                      JpaItemWriter <Person> writer*/) {
        return new StepBuilder("importStep", jobRepository)
                .<Person, Person>chunk(10, transactionManager)
                .reader(reader.reader())
                .processor(processor)
                .writer(writerJpa)
                //add skip and retry to step
                .faultTolerant()
                .retryLimit(3)
                .retry(IllegalArgumentException.class)
                .skipLimit(5)
                .skip(IllegalStateException.class)
                .build();
    }

    @Bean
    public Step step2() {
        return new StepBuilder("logStep", jobRepository)
                .tasklet(((contribution, chunkContext) -> {
                    System.out.println("step2 executed after condition.");
                    return RepeatStatus.FINISHED;
                }), transactionManager)
                .build();
    }

    @Bean
    public Step cleanupStep( ) {
        return new StepBuilder("cleanupStep", jobRepository)
                .tasklet(new CleanupTasklet(), transactionManager)
                .build();
    }

}
