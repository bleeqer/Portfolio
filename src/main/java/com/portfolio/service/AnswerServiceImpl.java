package com.portfolio.service;

import com.portfolio.domain.*;
import com.portfolio.mapper.AnswerMapper;
import com.portfolio.mapper.ImageMapper;
import com.portfolio.mapper.QuestionMapper;
import com.portfolio.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
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
    ImageMapper imageMapper;

    @Transactional
    @Override
    public Integer create(AnswerVO answerVO) throws SQLException {

        // answerVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        try {
            answerMapper.insert(answerVO);

        } catch (Exception e) {
            throw new SQLException("답변 등록에 실패했습니다.");
        }

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(answerVO.getQuesNo());

        // 답변여부 Y
        questionVO.setAnswered("Y");

        try {

            questionMapper.updateAnswered(questionVO);

        } catch (Exception e) {

            throw new SQLException("답변 등록에 실패했습니다.");

        }

        // 답변여부 업데이트

        // 이미지 경로 저장
        ImageVO imageVO = new ImageVO();

        for (String path : answerVO.getImagePath()) {

            imageVO.setAnsNo(answerVO.getAnsNo());
            imageVO.setImagePath(path);
            try {

                imageMapper.insert(imageVO);

            } catch (Exception e) {

                throw new SQLException("이미지를 첨부할 수 없습니다.");

            }

        }

        return answerVO.getAnsNo();
    }

    @Override
    public AnswerVO select(Integer ansNo) {

        return answerMapper.select(ansNo);
    }

    @Override
    public List<AnswerVO> selectAnswers(AnswerVO answerVO) {

        return answerMapper.selectAnswers(answerVO);
    }

    @Override
    public Integer countAnswers(AnswerVO answerVO) {
        return answerMapper.countAnswers(answerVO);
    }

    @Transactional
    @Override
    public void update(AnswerVO answerVO) {

        // 답변 이미지 테이블에 새로운 이미지 저장에 앞서 등록되어 있던 사진 삭제
        imageMapper.delete(answerVO);

        // 새롭게 이미지 경로 저장
        ImageVO imageVO = new ImageVO();

        for (String path : answerVO.getImagePath()) {

            imageVO.setAnsNo(answerVO.getAnsNo());
            imageVO.setImagePath(path);

            imageMapper.insert(imageVO);

        }

        answerMapper.update(answerVO);


    }

    @Transactional
    @Override
    public void delete(AnswerVO answerVO) {

        answerMapper.delete(answerVO.getAnsNo());

        // 답변글 삭제 후 해당 질문글의 답변글 갯수가 0개일 때 답변여부 N 업데이트
        if (answerMapper.countAnswers(answerVO) <= 0) {

            QuestionVO questionVO = new QuestionVO();

            questionVO.setQuesNo(answerVO.getQuesNo());
            questionVO.setAnswered("N");

            questionMapper.updateAnswered(questionVO);
        }

        // 등록되어있는 이미지 함께 삭제
        imageMapper.delete(answerVO);

    }

    @Override
    public AnswerLikeVO addLike(AnswerLikeVO likeVO) {

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
    public AnswerLikeVO subtractLike(AnswerLikeVO likeVO) {

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
