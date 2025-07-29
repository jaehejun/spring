package com.example.springdemo.controller;

import com.example.springdemo.entity.Employee;
import com.example.springdemo.entity.Role;
import com.example.springdemo.entity.UserRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
public class ConvertController {
    @Autowired
    private ConversionService conversionService;

    @GetMapping("/string-to-employee")
    public ResponseEntity<Object> getStringToEmployee(@RequestParam("employee") Employee employee) {
        //Spring이 내부적으로 다음의 작업을 수행:
        //conversionService.convert(employee, Employee.class);
        //@RequestParam("employee")의 값이 String -> Employee로 자동 변환
        log.info("ID: "+employee.getId() +", Salary: "+employee.getSalary());
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/check")
    public String checkRole(@RequestParam Role role) {
        return "입력된 역할: " + role.name();
    }
    @GetMapping("/path/{role}")
    public String roleByPath(@PathVariable Role role) {
        return "Path 역할: " + role.name();
    }
    @PostMapping("/json")
    public String fromJson(@RequestBody UserRequest request) {
        return "요청된 역할: " + request.getRole();
    }
}
