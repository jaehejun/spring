package com.example.springdemo.controller;

import com.example.springdemo.entity.CustomException;
import com.example.springdemo.entity.ErrorCode;
import com.example.springdemo.entity.ExceptionUserService;
import com.example.springdemo.entity.UserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class ExceptionController {
    private final ExceptionUserService userService;
    @PostMapping
    public ResponseEntity<String> createUser(@Valid @RequestBody UserDTO userDTO) {
        // Business logic to create a user
        if (userService.userExists(userDTO.getUsername())) {
            throw new CustomException(ErrorCode.INVALID_INPUT);
        }
        return ResponseEntity.ok("User created successfully");
    }
    @PostMapping("/fail")
    public void forceFail() {
        throw new CustomException(ErrorCode.SERVER_ERROR); // E003로 이동
    }
}
