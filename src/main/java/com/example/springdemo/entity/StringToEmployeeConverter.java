package com.example.springdemo.entity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToEmployeeConverter implements Converter<String, Employee> {
    // Converter<String, Employee>를 구현한 클래스(StringToEmployeeConverter)가 Bean으로 등록
    @Override
    public Employee convert(String from) {
        String[] data = from.split(",");
        return new Employee(Long.parseLong(data[0]),Double.parseDouble(data[1]));
    }
}
