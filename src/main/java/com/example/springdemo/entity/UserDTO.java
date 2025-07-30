package com.example.springdemo.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.aspectj.bridge.IMessage;

@Data
public class UserDTO {

    @NotBlank(message = "Username is mandatory")
    private String username;
    @Email(message = "Email should be valid")
    private String email;
}
