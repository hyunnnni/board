package com.green.board;

import com.green.board.entity.BoardCmtEntity;
import com.green.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardCmtRepository extends JpaRepository<BoardCmtEntity, Long> {
@Modifying
@Query("delete from BoardCmtEntity bc where bc.boardEntity.iboard = :iboard")
    void deleteByBoardEntity(Long iboard);
}
