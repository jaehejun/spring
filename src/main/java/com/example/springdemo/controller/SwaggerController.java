package com.example.springdemo.controller;

import com.example.springdemo.entity.SwaggerDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.web.bind.annotation.*;

@Tag(name = "스웨거", description = "스웨거 API")
@RestController
@RequestMapping("/api/v1")
public class SwaggerController {
    // 기본적으로 controller의 이름을 따라감
    // SwaggerController => swagger-controller
    @Operation(summary = "조회", description = "등록된 게시물을 조회")
    @GetMapping("/post")
    public String getPosts() {
        return "ok";
    }

    @PostMapping("/posts")
    public SwaggerDTO addPosts(@RequestBody SwaggerDTO swaggerDTO) {
        return swaggerDTO;
    }
}
