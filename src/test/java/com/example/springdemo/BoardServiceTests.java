package com.example.springdemo;

import com.example.springdemo.entity.*;
import com.example.springdemo.repository.BoardRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void write() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("Test Title1")
                .content("Test Content1")
                .writerEmail("user5@aaa.com")
                .build();

        log.info(boardService.write(boardDTO));
    }

    @Test
    public void testGet() {
        Long bno = 100L;
        BoardDTO boardDTO = boardService.get(bno);
        log.info(boardDTO.toString());
    }

    @Test
    public void testRemove() {
        Long bno = 1L;
        boardService.removeWithReplies(bno);
    }

    @Test
    public void testModify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(2L)
                .title("제목 변경")
                .content("내용 변경")
                .build();
        boardService.modify(boardDTO);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(5).build();
        PageResultDTO<BoardDTO, BoardDTO> resultDTO = boardService.getList(pageRequestDTO);
        log.info("PREV: " + resultDTO.isPrev());
        log.info("NEXT: " + resultDTO.isNext());
        log.info("TOTAL PAGE: " + resultDTO.getTotalPage());
        log.info("--------------------------------------------");
        for (BoardDTO boardDTO : resultDTO.getDtoList()) {
            log.info(boardDTO);
        }
        log.info("----------------------------------------------");
        resultDTO.getPageList().forEach(i->log.info(i));
    }

//    @Test
//    public void testInsertDummies() {
//        IntStream.rangeClosed(1, 100).forEach(i -> {
//            Board board = Board.builder()
//                    .title("title ... " + i)
//                    .content("content ... " + i)
//                    .writerEmail("writer ... " + i)
//                    .build();
//            boardRepository.save(board);
//        });
//    }
//
//    @Test
//    public void testQuery() {
//        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
//        QBoard qBoard = QBoard.board;
//        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder(); // 조건을 쌓을 수 있는 도구
//        //BooleanExpression: QueryDSL 에서 조건 표현
//        BooleanExpression expression = qBoard.title.contains(keyword);
//        builder.and(expression);
//        Page<Board> result = boardRepository.findAll(builder, pageable);
//
//        result.stream().forEach(log::info);
//    }
//
//    @Test
//    public void testQuery2() {
//        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
//        QBoard qBoard = QBoard.board;
////        String keyword = "1";
//        BooleanBuilder builder = new BooleanBuilder();
//        BooleanExpression exTitle = qBoard.title.contains("1");
//        BooleanExpression exContent = qBoard.content.contains("3");
//        builder.and(qBoard.bno.gt(100L)).and(exTitle).or(exContent);
//        Page<Board> result = boardRepository.findAll(builder, pageable);
//        result.stream().forEach(log::info);
//    }
//
//    @Test
//    public void testList() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(5).build();
//        PageResultDTO<BoardDTO, Board> resultDTO = boardService.getList(pageRequestDTO);
//
//        for (BoardDTO boardDTO : resultDTO.getDtoList()) {
//            log.info(boardDTO);
//        }
//    }
//
//    @Test
//    public void testList2() {
//        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(3).size(15).build();
//        PageResultDTO<BoardDTO, Board> resultDTO = boardService.getList(pageRequestDTO);
//
//        log.info("PREV: " + resultDTO.isPrev());
//        log.info("NEXT: " + resultDTO.isNext());
//        log.info("TOTAL PAGE: " + resultDTO.getTotalPage());
//        log.info("-------------------------------------------------");
//
//        for (BoardDTO boardDTO : resultDTO.getDtoList()) {
//            log.info(boardDTO);
//        }
//        log.info("--------------------------------------------------");
//        resultDTO.getPageList().forEach(i -> log.info(i));
//    }
}
