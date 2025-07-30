package com.example.springdemo.entity;

import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class ExceptionUserService {
    // 예시로 메모리 기반 사용자 목록 사용(실제는 DB조회), 중복 사용자 확인을 위한 로직
    private final Set<String> existingUsers = Set.of("admin", "admin@aaa.com");
    public boolean userExists(String username) {
        return existingUsers.contains(username);
    }
}
