package com.example.batch.writer;/*
 * @created 20/05/2025- 2:56 PM
 * @project spring-batch-demo
 * @author bziche
 */

import com.example.batch.domain.Person;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonItemWriter {
    /* Created by bziche on 5/20/2025 */

    @Bean
    public JpaItemWriter<Person> writer(EntityManagerFactory emf) {
        JpaItemWriter<Person> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(emf);
        return writer;
    }
}
