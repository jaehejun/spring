package com.example.springdemo.controller;

import com.example.springdemo.entity.BoardDTO;
import com.example.springdemo.entity.BoardService;
import com.example.springdemo.entity.PageRequestDTO;
import com.example.springdemo.entity.PageResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@RequiredArgsConstructor
@Log4j2
@Tag(name = "Board API", description = "게시판 목록 조회 API")
public class BoardController {
    private final BoardService boardService;
    
    @Operation(
    summary = "게시글 목록 조회",
    description = "페이징 정보에 따라 게시글 목록과 댓글 수를 함께 조회",
    responses = {
            @ApiResponse(responseCode = "200", description = "정상적으로 목록을 반환"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
        }
    )

    @GetMapping("/list")
    public ResponseEntity<PageResultDTO<BoardDTO, BoardDTO>> list(
            @Parameter(description = "페이지 요청 DTO", required = true) PageRequestDTO pageRequestDTO) {
        PageResultDTO<BoardDTO, BoardDTO> result = boardService.getList(pageRequestDTO);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "게시글 단건 조회", description = "게시글 번호로 게시글 1건을 조회")
    @GetMapping("/{bno}")
    public ResponseEntity<BoardDTO> get(@PathVariable Long bno) {
        return ResponseEntity.ok(boardService.get(bno));
    }

    @Operation(summary = "게시글 수정", description = "게시글의 제목과 내용을 수정")
    @PutMapping("/{bno}")
    public ResponseEntity<Void> modify(
            @PathVariable Long bno,
            @RequestBody BoardDTO boardDTO
    ) {
        boardDTO.setBno(bno);
        boardService.modify(boardDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시글 및 댓글 삭제", description = "게시글과 관련된 댓글을 함께 삭제")
    @DeleteMapping("/{bno}")
    public ResponseEntity<Void> remove(@PathVariable Long bno) {
        boardService.removeWithReplies(bno);
        return ResponseEntity.noContent().build();
    }
}
