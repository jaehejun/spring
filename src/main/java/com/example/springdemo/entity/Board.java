package com.example.springdemo.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
//@Table(name="board") 없으면 entity 이름과 동일한 테이블 생성
@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    @Column(length = 40, nullable = false)
    private String title;
    @Column(length = 1000, nullable = false)
    private String content;
    @Column(length = 40,  nullable = false)
    private String writer;

    @CreatedDate //엔티티가 처음 저장될 때 시간 자동 입력(Auditing)
    @Column(name = "regdate", updatable = false)
    private LocalDateTime regDate;

    @Builder.Default
    private Integer hit = 0;
}
