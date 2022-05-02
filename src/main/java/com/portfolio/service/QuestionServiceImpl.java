package com.portfolio.service;

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
    public List<QuestionVO> selectNotAnswered(int count) {
        return questionMapper.selectNotAnswered(count);
    };

    @Override
    public List<QAPairVO> selectAnsweredPair(int count) {

        // 원하는 갯수만큼 질문글 가져오기
        List<QuestionVO> questions = questionMapper.selectAnswered(count);

        // 질문글 + 답변글 Pair 담을 리스트 생성
        List<QAPairVO> pairs = new ArrayList<>();

        // 질문글 순회하며 답변글 조회해서 PairVO 셋팅하고 pairs 리스트에 담기
        for (QuestionVO question : questions) {

            // 한개의 답변글만 가져오기
            AnswerVO answer = answerService.selectAnswers(question.getQuesNo()).get(0);

            // PairVO 생성
            QAPairVO pair = new QAPairVO();

            System.out.println(answer.getUserName());

            // 답변글 데이터
            pair.setAnswer(answer.getAnswer());
            pair.setAnswerEmail(answer.getUserEmail());
            pair.setAnswerUserName(answer.getUserName());
            pair.setAnswerUserPhoto(answer.getUserPhoto());
            pair.setAnswerRegDate(answer.getRegDate());

            // 질문글 데이터
            pair.setQuesNo(question.getQuesNo());
            pair.setQuestion(question.getQuestion());
            pair.setCatPath(question.getCatPath());
            pair.setQuestionRegDate(question.getRegDate());

            // pairs 리스트에 담기
            pairs.add(pair);

        }

        return pairs;
    };

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
