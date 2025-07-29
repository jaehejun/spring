package com.example.springdemo.entity;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEmployeeConverter());
        registry.addConverter(new GenericBigDecimalConverter());
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}
