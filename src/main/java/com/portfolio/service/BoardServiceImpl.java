package com.portfolio.service;

import com.portfolio.domain.BoardVO;
import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.BoardMapper;
import com.portfolio.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    ImageMapper fileMapper;

    @Transactional
    @Override
    public void create(BoardVO boardVO) {

        // boardVO 인서트 성공 시 bno property에 자동생성된 bno 세팅
        boardMapper.insert(boardVO);

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
        for (String uploadPath : boardVO.getImageList()) {
            ImageVO imgVO = new ImageVO();

            imgVO.setBno(boardVO.getBno());
            imgVO.setUploadPath(uploadPath);

            fileMapper.insert(imgVO);
        }
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
