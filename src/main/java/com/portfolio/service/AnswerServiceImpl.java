package com.portfolio.service;

import com.portfolio.domain.ImageVO;
import com.portfolio.domain.AnswerVO;
import com.portfolio.mapper.AnswerImageMapper;
import com.portfolio.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    AnswerImageMapper answerImageMapper;

    @Transactional
    @Override
    public void create(AnswerVO answerVO) {

        // answerVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        answerMapper.insert(answerVO);

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
        for (String uploadPath : answerVO.getImageList()) {
            ImageVO imgVO = new ImageVO();

            imgVO.setPostNo(answerVO.getAnsNo());
            imgVO.setUploadPath(uploadPath);

            answerImageMapper.insert(imgVO);
        }
    }

    @Override
    public AnswerVO read(int postNo) {
        return answerMapper.select(postNo);
    }

    @Override
    public void update(AnswerVO answerVO) {

        answerMapper.update(answerVO);

    }

    @Override
    public void delete(int postNo) {
        answerMapper.delete(postNo);
    }

    @Override
    public List<AnswerVO> readAll() {

        return answerMapper.selectAll();

    }

    @Override
    public void addViewCnt(int postNo) {
        answerMapper.addViewCnt(postNo);
    }

    @Override
    public void addLikeCnt(int postNo) {
        answerMapper.addLikeCnt(postNo);
    }
}
