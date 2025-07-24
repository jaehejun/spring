package com.example.springdemo.entity;

import com.example.springdemo.myinterface.Create;
import com.example.springdemo.myinterface.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class Product {
    @NotNull(groups = Update.class)
    private Long id;

    @NotBlank(groups = {Create.class, Update.class})
    private String name;
    private String description;

    @Positive(groups = Create.class)
    private BigDecimal price;
    public Product(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    // Getters and Setters (or use Lombok's @Data
}
