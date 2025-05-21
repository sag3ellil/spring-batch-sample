package com.example.batch.processor;/*
 * @created 20/05/2025- 2:54 PM
 * @project spring-batch-demo
 * @author bziche
 */

import com.example.batch.domain.Person;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    /* Created by bziche on 5/20/2025 */

    @Override
    public Person process(Person item) throws Exception {
       if(item.getEmail().contains("error"))
       {
           throw new IllegalArgumentException("Invalid email format");
       }
        item.setEmail(item.getEmail().toUpperCase());
        return item;
    }
}
