package com.portfolio.service;

import com.portfolio.domain.ImageVO;
import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.UserVO;
import com.portfolio.mapper.AnswerImageMapper;
import com.portfolio.mapper.AnswerMapper;
import com.portfolio.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AnswerImageMapper answerImageMapper;

    @Transactional
    @Override
    public int create(AnswerVO answerVO) {

        // answerVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        answerMapper.insert(answerVO);

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
//        for (String uploadPath : answerVO.getImageList()) {
//            ImageVO imgVO = new ImageVO();
//
//            imgVO.setPostNo(answerVO.getAnsNo());
//            imgVO.setUploadPath(uploadPath);
//
//            answerImageMapper.insert(imgVO);
//        }

        return answerVO.getAnsNo();
    }

    @Override
    public AnswerVO readOne(int ansNo) {

        return answerMapper.selectOne(ansNo);
    }

    @Override
    public List<AnswerVO> selectAnswers(int quesNo) {

        // 답변글 리스트 조회
        List<AnswerVO> answers = answerMapper.selectAnswers(quesNo);

        // 답변글 순회하며 유저 정보 담기
        for (AnswerVO answer : answers) {

            UserVO user = userMapper.select(answer.getUserNo());
            answer.setUserEmail(user.getEmail());
            answer.setUserName(user.getName());
            answer.setUserNo(user.getUserNo());
            answer.setUserPhoto(user.getPhoto());

        }

        return answers;
    }

    @Override
    public List<AnswerVO> readMoreList(AnswerVO answer) {
        return answerMapper.selectMoreList(answer);
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
    public void addViewCnt(int postNo) {
        answerMapper.addViewCnt(postNo);
    }

    @Override
    public void addLikeCnt(int postNo) {
        answerMapper.addLikeCnt(postNo);
    }
}
