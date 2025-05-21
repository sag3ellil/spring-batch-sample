package com.example.batch.controller;/*
 * @created 20/05/2025- 5:03 PM
 * @project spring-batch-demo
 * @author bziche
 */

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobLauncherController {
    /* Created by bziche on 5/20/2025 */

    private final JobLauncher jobLauncher;
    private final Job job;

    @PostMapping("/import")
    public String launchJob(@RequestParam(required = false) String source) throws Exception {
        var params = new JobParametersBuilder()
                .addDate("timestamp", new Date())
                .addString("source",source== null ? "manual" : source)
                .toJobParameters();
        jobLauncher.run(job, params);
        return "Batch job triggered";
    }
}
