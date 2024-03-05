package com.green.board.model;

import lombok.Data;

@Data
public class BoardUpdDto {

    private Long iboard;
    private String title;
    private String ctnt;
    private String pw;
}
