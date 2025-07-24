package com.example.springdemo.repository;

import com.example.springdemo.entity.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByMnoBetweenOrderByMnoDesc(Long from, Long to);
    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);
    void deleteMemoByMnoLessThan(Long num);

    @Query("select m from Memo m order by m.mno desc")
    List<Memo> getListDesc();

    //JPQL
    @Query("SELECT m FROM Memo m WHERE m.memoText LIKE %:keyword%")
    List<Memo> findByTextContaining(@Param("keyword") String keyword);

    //Native SQL
    @Query(value = "SELECT * FROM memo WHERE memo_text LIKE %:keyword%", nativeQuery = true)
    List<Memo> findByTextUsingSQL(@Param("keyword") String keyword);
}
