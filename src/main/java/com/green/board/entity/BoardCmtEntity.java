package com.green.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "t_board_cmt")
public class BoardCmtEntity extends BaseEntity{

    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long icmt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(columnDefinition = "BIGINT UNSIGNED", name = "iboard")
    private BoardEntity boardEntity;

    @Column(length = 300, nullable = false)
    private String cmt;

    @Column(length = 100, nullable = false)
    private String pw;

    @Column(length = 10, nullable = false)
    private String writer;
}
