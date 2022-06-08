package com.portfolio.service;

import com.portfolio.domain.*;
import com.portfolio.mapper.AnswerImageMapper;
import com.portfolio.mapper.AnswerMapper;
import com.portfolio.mapper.QuestionMapper;
import com.portfolio.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AnswerImageMapper answerImageMapper;

    @Transactional
    @Override
    public long create(AnswerVO answerVO) {

        // answerVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        answerMapper.insert(answerVO);


        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(answerVO.getQuesNo());

        // 답변여부 Y
        questionVO.setAnswered("Y");

        // 답변여부 업데이트
        questionMapper.updateAnswered(questionVO);

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
    public AnswerVO select(long ansNo) {
        return answerMapper.select(ansNo);
    }

    @Override
    public List<AnswerVO> selectAnswers(AnswerVO answerVO) {

        return answerMapper.selectAnswers(answerVO);
    }

    @Override
    public long countAnswers(long quesNo) {
        return answerMapper.countAnswers(quesNo);
    }

    @Override
    public List<QAPairVO> selectAnsweredPairByUser(String email) {

        return answerMapper.selectAnsweredPairByUser(email);
    }

    @Override
    public void update(AnswerVO answerVO) {

        answerMapper.update(answerVO);
    }

    @Override
    public void delete(AnswerVO answerVO) {

        answerMapper.delete(answerVO.getAnsNo());

        // 답변글 삭제 후 해당 질문글의 답변글 갯수가 0개일 때 답변여부 N 업데이트
        if (answerMapper.countAnswers(answerVO.getQuesNo()) <= 0) {

            QuestionVO questionVO = new QuestionVO();

            questionVO.setQuesNo(answerVO.getQuesNo());
            questionVO.setAnswered("N");

            questionMapper.updateAnswered(questionVO);
        }

    }

    @Override
    public Map<String, Long> addLike(AnswerLikeVO likeVO) {

        AnswerLikeVO like = answerMapper.findLike(likeVO);

        // likeType 구분 없이 findLike 후 likeType 셋팅
        likeVO.setLikeType("UP");

        if (like == null) {

            answerMapper.addLike(likeVO);

        } else if (like.getLikeType().equals("UP")) {

            // 이미 좋아요 했다면 좋아요 취소
            answerMapper.deleteLike(likeVO);

        } else {

            // 싫어요 -> 좋아요 업데이트
            answerMapper.updateLike(likeVO);

        }

        return answerMapper.countLike(likeVO.getAnsNo());

    }

    @Override
    public Map<String, Long> subtractLike(AnswerLikeVO likeVO) {

        AnswerLikeVO like = answerMapper.findLike(likeVO);

        // likeType 구분 없이 findLike 후 likeType 셋팅
        likeVO.setLikeType("DOWN");

        if (like == null) {

            answerMapper.addLike(likeVO);

        } else if (like.getLikeType().equals("DOWN")) {

            // 이미 싫어요 했다면 싫어요 취소
            answerMapper.deleteLike(likeVO);

        } else {

            // 좋아요 -> 싫어요 업데이트
            answerMapper.updateLike(likeVO);

        }

        return answerMapper.countLike(likeVO.getAnsNo());

    }

    @Override
    public boolean checkAnswered(QuestionVO questionVO) {
        return answerMapper.checkAnswered(questionVO);
    }

    @Override
    public AnswerLikeVO checkLiked(AnswerVO answerVO) {
        return answerMapper.checkLiked(answerVO);
    }

}
