package com.green.board;

import com.green.board.entity.BoardCmtEntity;
import com.green.board.entity.BoardEntity;
import com.green.board.model.BoardCmtDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

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

    public void delBoard(Long iboard){
        BoardEntity boardEntity = boardRepository.getReferenceById(iboard);
        boardCmtRepository.deleteByBoardEntity(iboard);
        boardRepository.delete(boardEntity);
    }
}
