package com.example.springdemo.entity;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class Student {
    @NotNull
    @Size(min=3, max=4, message="이름은 3자 이상 4자 이하임")
    private String name;

    @Email
    private String email;

    @NotNull(message="나이는 필수임")
    @Min(value=18, message="나이는 18세 이상임")
    private int age;

    @NotEmpty(message="주소는 필수임")
    private String address;

    @Pattern(regexp = "\\d{3}-\\d{4}-\\d{4}", message = "전화번호는 XXX-XXXX-XXXX임")
    private String phoneNumber;
}

