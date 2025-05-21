package com.example.batch.domain;/*
 * @created 20/05/2025- 2:30 PM
 * @project spring-batch-demo
 * @author bziche
 */


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Data
@Entity
public class Person {
    /* Created by bziche on 5/20/2025 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
