package com.example.springdemo;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class SpringDemoApplicationTests {

    @Test
    void contextLoads() {
        log.info("contextLoad.............");
    }

}
