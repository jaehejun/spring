package com.example.springdemo.entity;

import lombok.Getter;
import lombok.Setter;

// REST API에서 데이터를 주고받을 수 있는 DTO

@Getter
@Setter
public class SwaggerDTO {
    private String name;
    private String phone;
}
