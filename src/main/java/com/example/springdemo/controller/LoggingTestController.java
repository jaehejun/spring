package com.example.springdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LoggingTestController {

    private static final Logger logger = LoggerFactory.getLogger(LoggingTestController.class);

    @GetMapping("/test")    // http://localhost:8080/log/test
    public String testLogging() {
        logger.info("INFO 로그 발생");
        logger.debug("DEBUG 로그 발생");
        logger.warn("WARN 로그 발생");
        logger.error("ERROR 로그 발생");
        return "로그 테스트 완료!";
    }
}
