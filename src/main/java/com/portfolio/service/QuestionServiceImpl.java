package com.portfolio.service;

import com.portfolio.commons.util.TimeDiff;
import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.ImageVO;
import com.portfolio.mapper.AnswerMapper;
import com.portfolio.mapper.QuestionMapper;
import com.portfolio.mapper.QuestionImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionImageMapper questionImageMapper;

    @Transactional
    @Override
    public void create(QuestionVO questionVO) {

        // questionVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        questionMapper.insert(questionVO);

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
//        if (questionVO.getImageList() != null) {
//            System.out.println("이미지 삽입");
//            for (String uploadPath : questionVO.getImageList()) {
//                ImageVO imgVO = new ImageVO();
//
//                imgVO.setPostNo(questionVO.getQuesNo());
//                imgVO.setUploadPath(uploadPath);
//
//                questionImageMapper.insert(imgVO);
//            }
//        }
    }

    @Override
    public QuestionVO read(int postNo) {
        return questionMapper.select(postNo);
    }

    @Override
    public void update(QuestionVO questionVO) {

        questionMapper.update(questionVO);

    }

    @Override
    public void delete(int postNo) {
        questionMapper.delete(postNo);
    }

    @Override
    public List<QuestionVO> selectList(int userNo) {
        return questionMapper.selectList(userNo);
    }

    @Override
    public List<QuestionVO> selectNotAnswered(int count) {
        return questionMapper.selectNotAnswered(count);
    };

    @Override
    public List<QAPairVO> selectAnsweredPair(int quesNo) {

//        for (QAPairVO pair : pairList) {
//            pair.setAnswerTimeDiff(TimeDiff.calculateTime(pair.getAnswerRegDate()));
//        }

        return questionMapper.selectAnsweredPair(quesNo);
    }

    @Override
    public List<QuestionVO> readAllByTopic(String topic) {
        return questionMapper.selectAllByTopic(topic);
    }

    @Override
    public void addViewCnt(int postNo) {
        questionMapper.addViewCnt(postNo);
    }

    @Override
    public void addLikeCnt(int postNo) {
        questionMapper.addLikeCnt(postNo);
    }

    @Override
    public List<QuestionVO> getMore(int startQuesNo) {
        return questionMapper.selectMore(startQuesNo);
    }

    @Override
    public List<QuestionVO> getMoreByTopic(HashMap<String, Object> map) {
        return questionMapper.selectMoreByTopic(map);
    }

}
