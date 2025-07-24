package com.example.springdemo.entity;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Data
// Page<Entity>의 객체들을 DTO 객체로 변환해서 저장
// 화면 출력에 필요한 페이지 정보들을 구성
public class PageResultDTO<DTO, EN> { // 페이지 결과를 처릴하기 위한 DTO
    private List<DTO> dtoList; // DTO 리스트

    private int totalPage; // 총 페이지 번호
    private int page; // 현재 페이지 번호
    private int size; // 페이지당 목록 갯수
    private int start, end; // 시작 페이지 번호, 끝 페이지 번호
    private boolean prev, next; // 이전, 다음 페이지 유무
    private List<Integer> pageList; // 페이지 번호 목록

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn) {
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1; // 1부터 시작하도록 보정
        this.size = pageable.getPageSize();
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
