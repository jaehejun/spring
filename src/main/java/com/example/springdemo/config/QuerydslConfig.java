package com.example.springdemo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // @Configuration은 이 클래스가 Spring Bean을 정의하는 클래스
public class QuerydslConfig {

    //@PersistenceContext는 JPA에서 사용하는 어노테이션으로, 영속성 컨텍스트의 EntityManager를 주입
    //QueryDSL은 내부적으로 ENtityManager를 통해 DB와 통신
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}
