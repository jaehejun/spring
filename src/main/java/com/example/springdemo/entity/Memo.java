package com.example.springdemo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "memo")
@ToString
@Getter
@Builder // 객체를 생성할 수 있게 처리함
// AllArgsConstructor와 NoArgsConstructor를 같이 처리해야 함
@AllArgsConstructor // Builder 사용시 필요
@NoArgsConstructor // 기본생성자 없으면 JPA 엔티티 객체 생성시 오류
public class Memo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;
}
