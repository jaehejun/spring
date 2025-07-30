package com.example.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INVALID_INPUT("E001", "Invalid input provided", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("E002", "User not found", HttpStatus.NOT_FOUND),
    SERVER_ERROR("E003", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    private final String code;
    private final String message;
    private final HttpStatus httpStatus;
}
