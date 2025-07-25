package com.example.springdemo.entity;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer")
@Getter
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY) // 필요하지 않은 데이터를 가져오지 않도록 Lazy로딩을 기본으로 지정
    private Member writer; //연관관계 지정
    @Builder.Default
    private Integer hit = 0;
}

//@Entity
//@EntityListeners(AuditingEntityListener.class)
////@Table(name="board") 없으면 entity 이름과 동일한 테이블 생성
//@ToString
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class Board {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long bno;
//    @Column(length = 40, nullable = false)
//    private String title;
//    @Column(length = 1000, nullable = false)
//    private String content;
//    @Column(length = 40,  nullable = false)
//    private String writer;
//
//    @CreatedDate //엔티티가 처음 저장될 때 시간 자동 입력(Auditing)
//    @Column(name = "regdate", updatable = false)
//    private LocalDateTime regDate;
//
//    @Builder.Default
//    private Integer hit = 0;
//}


