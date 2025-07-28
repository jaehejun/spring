package com.example.springdemo;

import com.example.springdemo.entity.Board;
import com.example.springdemo.entity.BoardDTO;
import com.example.springdemo.entity.BoardReplyDTO;
import com.example.springdemo.entity.Member;
import com.example.springdemo.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    BoardRepository boardRepository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("user" + i + "@aaa.com")
                    .build();
            Board board = Board.builder()
                    .title("Title..."+i)
                    .content("Content..."+i)
                    .writer(member)
                    .build();
            boardRepository.save(board);
        });
    }

    @Transactional
    @Test
    public void testRead1() {
        Optional<Board> result = boardRepository.findById(100L);
        Board board = result.get();
        log.info(board);
        log.info(board.getWriter());
    }

    @Test
    public void testReadWithWriter() {
        Object result = boardRepository.getBoardWithWriter(100L);
        Object[] arr = (Object[]) result;
        log.info("---------------------------------------");
        log.info(Arrays.toString(arr));
    }

    @Test
    public void testGetBoardWithReply() {
        List<Object[]> result = boardRepository.getBoardWithReply(100L);
        for (Object[] arr : result) {
            log.info(Arrays.toString(arr));
        }
    }

    @Test
    public void testWithReplyCount() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);
        result.get().forEach(row->{
            Object[] arr = (Object[]) row;
            log.info(Arrays.toString(arr));
        });
    }

    @Test
    public void testReadWithWriterDTO() {
        BoardDTO dto = boardRepository.getBoardDTOWithWriter(100L);
        log.info("----------------------------------");
        log.info(dto);
    }

    @Test
    public void testGetBoardWithReplies() {
        List<BoardReplyDTO> result = boardRepository.getBoardWithReplies(100L);
        result.forEach(dto -> log.info(dto));
    }

    @Test
    public void testSearchWithReplyCount() {
        Pageable pageable = PageRequest.of(0,10);
        Page<BoardDTO> result = boardRepository.searchWithReplyCount(pageable);

        result.getContent().forEach(dto -> {
            log.info(dto);
        });
    }

    @Test
    public void testGetBoardByBnoWithReplyCount() {
        BoardDTO dto = boardRepository.getBoardByBnoWithReplyCount(10L);
        log.info(dto);
    }
}
