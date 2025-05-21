package com.example.batch.listener;/*
 * @created 20/05/2025- 3:22 PM
 * @project spring-batch-demo
 * @author bziche
 */

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class JobCompetitionNotificationListener implements JobExecutionListener {
    /* Created by bziche on 5/20/2025 */
    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("======Job starting =====");
    }
    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("======Job finished with status " + jobExecution.getStatus() + "  =====");
    }
}
