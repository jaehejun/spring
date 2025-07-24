package com.example.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Data
public class BoardDTO {
    private Long bno;
    private String title, content, writer;
    private LocalDateTime regDate;
    private Integer hit;
}
