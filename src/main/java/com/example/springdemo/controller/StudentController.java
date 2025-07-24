package com.example.springdemo.controller;

import com.example.springdemo.entity.Student;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@Log4j2
@RestController
public class StudentController {
    @PostMapping("/student")
    public String createUser(@RequestBody @Valid Student student, BindingResult bindingResult) {
        log.info("aaaa");
        if (bindingResult.hasErrors()) {
            return "검증 오류: \n"
                    + bindingResult.getAllErrors()
                    .stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.joining("\n"))
                    ;
        }
        return "학생 생성 완료 : " + student.toString();
    }
}
