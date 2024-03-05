package com.green.board;

import com.green.board.entity.BoardCmtEntity;
import com.green.board.entity.BoardEntity;
import com.green.board.model.BoardCmtDto;
import com.green.board.model.BoardSelVo;
import com.green.board.model.BoardUpdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardCmtRepository boardCmtRepository;

    public Long postBoard (BoardEntity boardEntity){

        boardEntity.setPw(BCrypt.hashpw(boardEntity.getPw(), BCrypt.gensalt()));
        boardRepository.save(boardEntity);//insert해주는 메소드
        return boardEntity.getIboard();
    }

    public void delBoard(Long iboard){
        BoardEntity boardEntity = boardRepository.getReferenceById(iboard);
        boardCmtRepository.deleteByBoardEntity(iboard);
        boardRepository.delete(boardEntity);
    }

    @Transactional
    public Long putBoard(BoardUpdDto dto) {
        BoardEntity boardEntity = boardRepository.getReferenceById(dto.getIboard());//pw비교를 위한 원래의 값 조회
        if(!BCrypt.checkpw(dto.getPw(), boardEntity.getPw())){//들어온 pw와 기존 pw가 일치하는지 비교
            return 0L;
        }
        boardEntity.setTitle(dto.getTitle());//영속성으로 인해 자동 수정
        boardEntity.setCtnt(dto.getCtnt());
        return 1L;
    }

    public List<BoardSelVo> getBoardList(Pageable pageable){
        Page<BoardEntity> list = boardRepository.findAll(pageable);
        /*//1 디폴트 쿼리 메소드 2보단 성능이 더 좋다


        List<BoardSelVo> result = new ArrayList<>();

        for(BoardEntity item : list.getContent()){
            result.add(BoardSelVo.builder()
                    .iboard(item.getIboard())
                    .title(item.getTitle())
                    .writer(item.getWriter())
                    .createdAt(item.getCreatedAt())
                    .ctnt(item.getCtnt())
                    .build());
        }

        return result;

        List<BoardSelVo> result1 = list.get().map(item -> BoardSelVo.builder()
                .iboard(item.getIboard())
                .title(item.getTitle())
                .writer(item.getWriter())
                .createdAt(item.getCreatedAt())
                .ctnt(item.getCtnt())
                .build()).collect(Collectors.toList());

         return result1;*/

        //2 Custom Query Method
        /*List<BoardEntity> list2 = boardRepository.findAllByOrderByIboardDesc(pageable);


        List<BoardSelVo> result2 = list2.stream().map(item -> BoardSelVo.builder()
                .iboard(item.getIboard())
                .title(item.getTitle())
                .writer(item.getWriter())
                .createdAt(item.getCreatedAt())
                .ctnt(item.getCtnt())
                .build()).collect(Collectors.toList());

        return result2;*/


        //3 JPQL
       /* List<BoardEntity> list3 = boardRepository.selBoardList(pageable);

        List<BoardSelVo> result3 = list3.stream().map(item -> BoardSelVo.builder()
                .iboard(item.getIboard())
                .title(item.getTitle())
                .writer(item.getWriter())
                .createdAt(item.getCreatedAt())
                .ctnt(item.getCtnt())
                .build()).collect(Collectors.toList());

        //return result3;
        return boardRepository.selBoardList2(pageable);*/


        //4 QueryDSL
        return boardRepository.selBoardListQdsl(pageable);

    }

    public Long postBoardCmt (BoardCmtDto dto){

        BoardEntity board = new BoardEntity();
        board.setIboard(dto.getIboard());

        BoardCmtEntity cmt = new BoardCmtEntity();
        cmt.setBoardEntity(board);
        cmt.setCmt(dto.getCmt());
        cmt.setPw(BCrypt.hashpw(dto.getPw(), BCrypt.gensalt()));
        cmt.setWriter(dto.getWriter());

        boardCmtRepository.save(cmt);//insert해주는 메소드
        return cmt.getIcmt();
    }


}
