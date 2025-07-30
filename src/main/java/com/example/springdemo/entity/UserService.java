package com.example.springdemo.entity;

import org.springframework.batch.core.BatchStatus;

public interface UserService {
    BatchStatus runBatch(String filePath) throws Exception;
}
