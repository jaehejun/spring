package com.example.springdemo.controller;

import com.example.springdemo.entity.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.BatchStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BatchController {
    private final UserService userService;

    @PostMapping("/batch")
    public ResponseEntity<String> runBatch(@RequestParam String filePath) throws Exception {
        BatchStatus status = userService.runBatch(filePath);
        return ResponseEntity.ok("Batch Status: " + status);
    }
}
