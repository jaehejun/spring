package com.example.springdemo;

import com.example.springdemo.entity.Memo;
import com.example.springdemo.repository.MemoRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;
    @Test
    public void testClass() {
        log.info(memoRepository.getClass().getName());
    }

    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample ... " + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testSelect() {
        //데이터베이스에 존재하는 mno
        Long mno =  10L;
        Optional<Memo> result = memoRepository.findById(mno);
        log.info("======================================================");
        if (result.isPresent()) {
            Memo memo = result.get();
            log.info(memo);
        }
    }

//    @Transactional
//    @Test
//    public void testSelect2() {
//        Long mno =  5L;
//        Memo memo = memoRepository.getOne(mno);
//        log.info("========================================================");
//        log.info(memo);
//    }

    @Transactional
//    @Test
//    public void testUpdate() {
//        Memo memo = Memo.builder().mno(5L).memoText("Update Text").build();
//        log.info(memoRepository.save(memo));
//    }

    @Test
    public void testDelete() {
        Long mno = 5L;
        memoRepository.deleteById(mno);
    }

    @Test
    public void testPageDefault() {
        // 1 페이지에 5개, 첫번째 페이지
        Pageable pageable = PageRequest.of(0, 5);
        Page<Memo> result = memoRepository.findAll(pageable);
        log.info(result);
    }

    @Test
    public void testPageDefault2() {
        // 1페이지에 5개, 첫번째 페이지
        Pageable pageable = PageRequest.of(0, 5);
        Page<Memo> result = memoRepository.findAll(pageable);
        log.info(result);
        log.info("------------------------------------------------------");
        log.info("Total Pages: " + result.getTotalPages()); // 전체 페이지 수
        log.info("Total count: " + result.getTotalElements()); // 전체 데이터 개수
        log.info("Page Number: " + result.getNumber()); // 현재 페이지 번호
        log.info("Page Size: " + result.getSize()); // 페이지당 데이터 개수
        log.info("has Next Page?: " + result.hasNext()); // 다음 페이지가 있는가?
        log.info("first Page?: " + result.isFirst()); // 첫 번째 페이지인가?
    }

    @Test
    public void testSort() {
        Sort sort1 = Sort.by("mno").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);
        Page<Memo> result = memoRepository.findAll(pageable);
        result.get().forEach(memo -> {
            log.info(memo);
        });
    }

    @Test
    public void testQueryMethods() {
        List<Memo> list = memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L);
        for (Memo memo : list) {
            log.info(memo);
        }
    }

    @Test
    public void testQueryMethodWithPageable() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());
        Page<Memo> result = memoRepository.findByMnoBetween(10L, 50L, pageable);
        result.get().forEach(memo -> log.info(memo));
    }

    @Commit
    @Transactional
    @Test
    public void testDeleteQueryMethods() {
        memoRepository.deleteMemoByMnoLessThan(20L);
    }

    @Test
    public void testGetListDesc() {
        List<Memo> list = memoRepository.getListDesc();
        for (Memo memo : list) {
            log.info(memo);
        }
    }

    @Test
    public void testJPQL() {
        List<Memo> list = memoRepository.findByTextContaining("1");
        for (Memo memo : list) {
            log.info(memo);
        }
    }

}
