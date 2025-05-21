package com.example.batch.config;/*
 * @created 20/05/2025- 3:27 PM
 * @project spring-batch-demo
 * @author bziche
 */

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.stereotype.Component;

@Component
public class EmailCheckDecider implements JobExecutionDecider {
    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        //dummy check
        return Math.random() > 0.5 ? new FlowExecutionStatus("CONTINUE") : new FlowExecutionStatus("STOP");
    }
    /* Created by bziche on 5/20/2025 */
}
