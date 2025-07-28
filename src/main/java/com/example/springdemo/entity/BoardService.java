package com.example.springdemo.entity;

import com.example.springdemo.repository.BoardRepository;
import com.example.springdemo.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    public Long write(BoardDTO dto) {
        log.info("DTO -------------------------------");
        log.info(dto);
        Board entity = dtoToEntity(dto);
        log.info(entity);
        boardRepository.save(entity);
        return entity.getBno();
    }

    public Board dtoToEntity(BoardDTO dto) {
        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();
        Board board = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
        return board;
    }

    public BoardDTO get(Long bno) {
        return boardRepository.getBoardDTOWithWriter(bno);
    }

    @Transactional
    public void removeWithReplies(Long bno) {
        replyRepository.deleteByBno(bno);   //댓글부터 삭제
        boardRepository.deleteById(bno);
    }

    @Transactional
//    @Override
    public void modify(BoardDTO boardDTO) {
        Board board = boardRepository.getOne(boardDTO.getBno());
        // Board board = boardRepository.findById(boardDTO.getBno()).get();
        // -> @Transactional이 필요 없음
        board.changeTitle(boardDTO.getTitle());
        board.changeContent(boardDTO.getContent());
        boardRepository.save(board);
    }

    public PageResultDTO<BoardDTO, BoardDTO> getList(PageRequestDTO requestDTO) {
        log.info("requestDTO: {}", requestDTO);

        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
        //DTO를 바로 반환하는 repository 메서드 호출
        Page<BoardDTO> result = boardRepository.searchWithReplyCount(pageable);
        //변환 함수는 그대로 반환(identity)
        //repository가 이미 BoardDTO로 결과를 반환하므로
        //변환할 필요가 없이 그대로 사용
        Function<BoardDTO, BoardDTO> fn = Function.identity();
        return new PageResultDTO<>(result, fn);
    }
}
//    public Long write(BoardDTO dto) {
//        log.info("DTO -------------------------------");
//        log.info(dto);
//        Board entity = dtoToEntity(dto);
//        log.info(entity);
//        boardRepository.save(entity);
//        return entity.getBno();
//    }
//
//    public Board dtoToEntity(BoardDTO dto) {
//        Board entity = Board.builder()
//                .title(dto.getTitle())
//                .content(dto.getContent())
//                .writer(dto.getWriter())
//                .build();
//        return entity;
//    }
//
//
//    public BoardDTO entityToDto(Board entity) {
//        BoardDTO dto = BoardDTO.builder()
//                .bno(entity.getBno())
//                .title(entity.getTitle())
//                .content(entity.getContent())
//                .writer(entity.getWriter())
//                .regDate(entity.getRegDate())
//                .hit(entity.getHit())
////                .modDate(entity.getModDate())
//                .build();
//        return dto;
//    }
//}
