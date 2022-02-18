package com.portfolio.service;

import com.portfolio.domain.BoardVO;

import java.util.List;

public interface BoardService {
    int create(BoardVO boardVO);
    BoardVO read(int bno);
    void update(BoardVO boardVO);
    void delete(int bno);
    List<BoardVO> readAll();
    void addViewCnt(int bno);
    void addLikeCnt(int bno);
}
