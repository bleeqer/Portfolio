package com.portfolio.mapper;

import com.portfolio.domain.BoardVO;
import java.util.List;

public interface BoardMapper {

    void insert(BoardVO boardVO);
    BoardVO select(int bno);
    void update(BoardVO boardVO);
    void delete(int bno);
    List<BoardVO> selectAll();
    void addViewCnt(int bno);
    void addLikeCnt(int bno);
}
