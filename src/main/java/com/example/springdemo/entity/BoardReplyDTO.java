package com.example.springdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardReplyDTO {
    private Long bno;
    private String title;
    private String content;
    private int hit;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    private Long rno;
    private String reply;
    private String replyer;
    private LocalDateTime replyRegDate;
}
