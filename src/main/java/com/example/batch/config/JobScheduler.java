package com.example.batch.config;/*
 * @created 20/05/2025- 4:53 PM
 * @project spring-batch-demo
 * @author bziche
 */

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class JobScheduler {
    /* Created by bziche on 5/20/2025 */
    private final JobLauncher jobLauncher;
    private final Job importUserJob;

    @Scheduled(fixedRate = 6000) //every 60 seconds
    public void runJob() throws Exception {
        var params = new JobParametersBuilder()
                .addDate("startAt", new Date())
                .toJobParameters();
        jobLauncher.run(importUserJob, params);
    }
}
