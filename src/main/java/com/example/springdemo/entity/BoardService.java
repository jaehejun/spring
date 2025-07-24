package com.example.springdemo.entity;

import com.example.springdemo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public Long write(BoardDTO dto) {
        log.info("DTO -------------------------------");
        log.info(dto);
        Board entity = dtoToEntity(dto);
        log.info(entity);
        boardRepository.save(entity);
        return entity.getBno();
    }

    public Board dtoToEntity(BoardDTO dto) {
        Board entity = Board.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    public PageResultDTO<BoardDTO, Board> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
        Function<Board, BoardDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    public BoardDTO entityToDto(Board entity) {
        BoardDTO dto = BoardDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .hit(entity.getHit())
//                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
