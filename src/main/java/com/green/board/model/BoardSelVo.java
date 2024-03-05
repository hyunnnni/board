package com.green.board.model;

import com.green.board.entity.BoardCmtEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardSelVo {
    private Long iboard;
    private String title;
    private String ctnt;
    private String writer;
    private LocalDateTime createdAt;
    //private List<BoardCmtEntity> boardCmts = new ArrayList<>();
}

