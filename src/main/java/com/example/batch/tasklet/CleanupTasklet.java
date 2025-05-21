package com.example.batch.tasklet;/*
 * @created 20/05/2025- 5:08 PM
 * @project spring-batch-demo
 * @author bziche
 */

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class CleanupTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Path tempDir = Paths.get("tmp/");
        if(Files.exists(tempDir))
        {
            Files.walk(tempDir)
                    .filter(Files::isRegularFile)
                    .forEach(file -> {
                        try {
                            Files.delete(file);
                            System.out.println("Deleted file: " + file);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

        }
        return RepeatStatus.FINISHED;
    }
    /* Created by bziche on 5/20/2025 */
}
