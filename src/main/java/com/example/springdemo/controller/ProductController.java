package com.example.springdemo.controller;

import com.example.springdemo.entity.Product;
import com.example.springdemo.myinterface.Create;
import com.example.springdemo.myinterface.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

    @PostMapping
    public String create(@RequestBody @Validated(Create.class) Product product) {
        return "Created : " + product.getName();
    }

    @PutMapping
    public String update(@RequestBody @Validated(Update.class) Product product) {
        return "Updated : " + product.getId();
    }
}
