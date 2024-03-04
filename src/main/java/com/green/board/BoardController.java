package com.green.board;

import com.green.board.entity.BoardCmtEntity;
import com.green.board.entity.BoardEntity;
import com.green.board.model.BoardCmtDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
    private final BoardService service;

    @PostMapping
    public Long postBoard(@RequestBody BoardEntity boardEntity){
        return service.postBoard(boardEntity);
    }
    @DeleteMapping
    public Long deleteBoard(@RequestParam Long iboard){
        service.delBoard(iboard);
        return 1L;
    }

    @PostMapping("/cmt")
    public Long postBoardCmt(@RequestBody BoardCmtDto dto){
        return service.postBoardCmt(dto);
    }


}
