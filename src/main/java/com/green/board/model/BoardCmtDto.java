package com.green.board.model;

import lombok.Data;

@Data
public class BoardCmtDto {
    private Long iboard;
    private String cmt;
    private String pw;
    private String writer;
}
