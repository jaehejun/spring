package com.example.springdemo;

import com.example.springdemo.repository.MemoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;
    @Test
    public void testClass() {
        log.info(memoRepository.getClass().getName());
    }
}
