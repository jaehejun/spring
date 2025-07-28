package com.example.springdemo.repository;

import com.example.springdemo.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno")
    void deleteByBno(Long bno); // 삭제
}
