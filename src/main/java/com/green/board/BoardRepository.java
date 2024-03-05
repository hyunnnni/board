package com.green.board;

import com.green.board.entity.BoardEntity;
import com.green.board.model.BoardSelVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> , BoardQdslRepository{
    List<BoardEntity> findAllByOrderByIboardDesc(Pageable pageable);//orderby 뒤에 적어줬으므로 Pageable에서 설정했던 디폴트 값을 사용 안하고 바로 이걸 사용함

    @Query("select b from BoardEntity b order by b.iboard desc")
    List<BoardEntity> selBoardList(Pageable pageable);

    @Query("select new com.green.board.model.BoardSelVo(b.iboard, b.title, b.ctnt, b.writer, b.createdAt) " +
            "from BoardEntity b order by b.iboard desc")
    //(괄호 안 컬럼 )객체 안 멤버필드 값을 다 채워줘야하고 순서대로 작성해야한다
    List<BoardSelVo> selBoardList2(Pageable pageable);


}
