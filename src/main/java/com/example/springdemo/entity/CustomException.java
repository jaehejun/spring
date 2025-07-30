package com.example.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
