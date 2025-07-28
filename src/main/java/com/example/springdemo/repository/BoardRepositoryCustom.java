package com.example.springdemo.repository;

import com.example.springdemo.entity.BoardDTO;
import com.example.springdemo.entity.BoardReplyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BoardRepositoryCustom {
    BoardDTO getBoardDTOWithWriter(Long bno);
    List<BoardReplyDTO> getBoardWithReplies(Long bno);
    Page<BoardDTO> searchWithReplyCount(Pageable pageable);
    BoardDTO getBoardByBnoWithReplyCount(Long bno);
}

