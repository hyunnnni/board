package com.green.board.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "t_board")
public class BoardEntity extends BaseEntity{
    @Id
    @Column(columnDefinition = "BIGINT UNSIGNED")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long iboard;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String ctnt;

    @Column(length = 10, nullable = false)
    private String writer;

    @Column(length = 100, nullable = false)
    private String pw;

    @JsonIgnore
    @ToString.Exclude//string으로 변환 시 얘는 제외하고 변환(무한 루프)
    @OneToMany(mappedBy = "boardEntity",cascade = CascadeType.REMOVE,orphanRemoval = true) //, cascade = CascadeType.PERSIST 모든 댓글들의 정보를 다 불러온다는 문제가 있다.
    //orphanRemoval = true 자식 객체 자동 삭제 해주는 기능
    //그래서 개수 제한이 있다면 사용해도 나쁘진 않다
    //양방향 전이 부모객체 저장 시 자식 객체도 저장한다
    private List<BoardCmtEntity> boardCmtEntity = new ArrayList<>();//one to many니깐 list 타입
    //lazy는 프록시 객체 셀렉할 때만 가져옴
}
