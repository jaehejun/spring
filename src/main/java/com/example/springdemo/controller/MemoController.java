package com.example.springdemo.controller;

import com.example.springdemo.entity.Memo;
import com.example.springdemo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/memo")
public class MemoController {
    @Autowired
    private MemoRepository memoRepository;

    @PostMapping
    public ResponseEntity<Memo> addMemo(@RequestBody Memo memo) {
        Memo saved = memoRepository.save(memo);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //단일메모조회
    @GetMapping("/{id}")
    public ResponseEntity<Memo> findMemo(@PathVariable Long id) {
        return memoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
