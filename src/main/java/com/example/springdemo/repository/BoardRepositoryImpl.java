package com.example.springdemo.repository;

import com.example.springdemo.entity.*;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public BoardDTO getBoardDTOWithWriter(Long bno) {
        QBoard b = QBoard.board;
        QMember m = QMember.member;

        return queryFactory
                .select(Projections.constructor(BoardDTO.class,
                        b.bno,
                        b.title,
                        b.content,
                        m.email,
                        m.name,
                        b.regDate,
                        b.modDate,
                        b.hit
                ))
                .from(b)
                .leftJoin(b.writer, m)
                .where(b.bno.eq(bno))
                .fetchOne();
    }

    @Override
    public List<BoardReplyDTO> getBoardWithReplies(Long bno) {
        QBoard b = QBoard.board;
        QReply r = QReply.reply1;

        return queryFactory
                .select(Projections.constructor(BoardReplyDTO.class,
                        b.bno,
                        b.title,
                        b.content,
                        b.hit,
                        b.regDate,
                        b.modDate,
                        r.rno,
                        r.reply,
                        r.replyer,
                        r.regDate
                ))
                .from(b)
                .leftJoin(r).on(r.board.eq(b))
                .where(b.bno.eq(bno))
                .fetch();
    }

    @Override
    public Page<BoardDTO> searchWithReplyCount(Pageable pageable) {
        QBoard b = QBoard.board;
        QMember m = QMember.member;
        QReply r = QReply.reply1;   //QReply.reply는 필드명 충돌 방지로 인해 reply1로 생성됨

        List<BoardDTO> result = queryFactory
                .select(Projections.constructor(BoardDTO.class,
                        b.bno,
                        b.title,
                        b.content,
                        m.email,
                        m.name,
                        b.regDate,
                        b.modDate,
                        r.count().intValue(),
                        b.hit
                ))
                .from(b)
                .leftJoin(b.writer,m)
                .leftJoin(r).on(r.board.eq(b))
                .groupBy(b.bno,b.title,b.content,m.email,m.name,b.regDate,b.modDate,b.hit)
                .orderBy(b.bno.desc())
                .offset(pageable.getOffset())   //페이지 시작 위치 지정
                .limit(pageable.getPageSize())  //한 페이지당 조회할 개수
                .fetch();   //결과 리스트 조회

        Long total = queryFactory
                .select(b.count())
                .from(b)
                .fetchOne();

        return new PageImpl<>(result, pageable, total != null ? total : 0);
    }

    @Override
    public BoardDTO getBoardByBnoWithReplyCount(Long bno) {
        QBoard b = QBoard.board;
        QMember w = QMember.member;
        QReply r = QReply.reply1;

        return queryFactory
                .select(Projections.constructor(BoardDTO.class,
                        b.bno,
                        b.title,
                        b.content,
                        w.email,
                        w.name,
                        b.regDate,
                        b.modDate,
                        r.count().intValue()  //댓글 수
                ))
                .from(b)
                .leftJoin(b.writer,w)
                .leftJoin(r).on(r.board.eq(b))
                .where(b.bno.eq(bno))
                .groupBy(b.bno,b.title,b.content,w.email,w.name,b.regDate,b.modDate)
                .fetchOne();
    }
}
