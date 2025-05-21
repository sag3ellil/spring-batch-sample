package com.example.batch.partitioner;/*
 * @created 20/05/2025- 5:31 PM
 * @project spring-batch-demo
 * @author bziche
 */

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RangePartitioner implements Partitioner {

    @Override
    public Map<String, ExecutionContext> partition(int gridSize) {
        Map<String, ExecutionContext> partitions = new java.util.HashMap<>();

        int range = 1000;
        int chunkSize = range / gridSize;

        for(int i = 0; i < gridSize; i++) {
            ExecutionContext context = new ExecutionContext();
            context.putInt("minValue", i * chunkSize + 1);
            context.putInt("maxValue", (i + 1) * chunkSize);
            partitions.put("partition" + i, context);
        }
        return partitions;
    }
    /* Created by bziche on 5/20/2025 */

}
