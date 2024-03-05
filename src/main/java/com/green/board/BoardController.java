package com.green.board;

import com.green.board.entity.BoardCmtEntity;
import com.green.board.entity.BoardEntity;
import com.green.board.model.BoardCmtDto;
import com.green.board.model.BoardSelVo;
import com.green.board.model.BoardUpdDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


import java.util.List;

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
    @PutMapping
    public Long putBoard(@RequestBody BoardUpdDto dto){
        return service.putBoard(dto);
    }
    @GetMapping
    public List<BoardSelVo> getBoardList(@PageableDefault(sort ="iboard", direction = Sort.Direction.DESC) Pageable pageable){
        //sort값을 보내지 않고 보낸다면 기본으로 이 값이 들어가게 된다
        return service.getBoardList(pageable);
    }

    @PostMapping("/cmt")
    public Long postBoardCmt(@RequestBody BoardCmtDto dto){
        return service.postBoardCmt(dto);
    }


}
