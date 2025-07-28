package com.example.springdemo.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class BoardDTO {
    private Long bno;
    private String title, content, writerEmail, writerName;
    private LocalDateTime regDate, modDate;
    private Integer replyCount; // 해당 게시글의 댓글 수
    private Integer hit;
}
