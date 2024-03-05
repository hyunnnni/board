package com.green.board;

import com.green.board.model.BoardSelVo;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.green.board.entity.QBoardEntity.boardEntity;

@Slf4j
@RequiredArgsConstructor
public class BoardQdslRepositoryImpl implements BoardQdslRepository{
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<BoardSelVo> selBoardListQdsl(Pageable pageable) {
        //쿼리 dsl에 있는 Projections 기능
        return jpaQueryFactory.select(Projections.constructor(BoardSelVo.class,
                        boardEntity.iboard, boardEntity.title, boardEntity.ctnt, boardEntity.writer, boardEntity.createdAt))
                .from(boardEntity)
                .orderBy(boardEntity.iboard.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
