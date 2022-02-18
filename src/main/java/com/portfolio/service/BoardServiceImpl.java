package com.portfolio.service;

import com.portfolio.domain.BoardVO;
import com.portfolio.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;

    @Override
    public int create(BoardVO boardVO) {

        return boardMapper.insert(boardVO);
    }

    @Override
    public BoardVO read(int bno) {
        return boardMapper.select(bno);
    }

    @Override
    public void update(BoardVO boardVO) {
        boardMapper.update(boardVO);
    }

    @Override
    public void delete(int bno) {
        boardMapper.delete(bno);
    }

    @Override
    public List<BoardVO> readAll() {
        return boardMapper.selectAll();
    }

    @Override
    public void addViewCnt(int bno) {
        boardMapper.addViewCnt(bno);
    }

    @Override
    public void addLikeCnt(int bno) {
        boardMapper.addLikeCnt(bno);
    }
}
