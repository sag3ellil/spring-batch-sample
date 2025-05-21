package com.example.batch.reader;/*
 * @created 20/05/2025- 2:48 PM
 * @project spring-batch-demo
 * @author bziche
 */

import com.example.batch.domain.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class PersonItemReader {
    /* Created by bziche on 5/20/2025 */
    @Bean
    public FlatFileItemReader<Person> reader()
    {
        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .resource(new ClassPathResource("input/persons.csv"))
                .delimited()
                .names("firstName", "lastName", "email")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){
                    {
                        setTargetType(Person.class);
                    }
                })
                .build();
    }
}
