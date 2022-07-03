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

                throw new SQLException("이미지 업로드에 실패했습니다.");

            }

        }

        return answerVO.getAnsNo();
    }

    @Override
    public AnswerVO select(Integer ansNo) throws SQLException {

        try {

            return answerMapper.select(ansNo);


        } catch (Exception e) {

            throw new SQLException("답변을 찾을 수 없습니다.");

        }
    }

    @Override
    public List<AnswerVO> selectAnswers(AnswerVO answerVO) throws SQLException {

        List<AnswerVO> answerList;

        try {

            answerList = answerMapper.selectAnswers(answerVO);

        } catch (Exception e) {

            throw new SQLException("답변 목록을 불러올 수 없습니다.");

        }
        return answerList;
    }

    @Override
    public Integer countAnswers(AnswerVO answerVO) throws SQLException {

        int answerCounts;
        try {

            answerCounts = answerMapper.countAnswers(answerVO);

        } catch (Exception e) {

            throw new SQLException("답변 갯수 카운트에 실패했습니다.");

        }

        return answerCounts;
    }

    @Transactional
    @Override
    public void update(AnswerVO answerVO) throws SQLException {

        try {

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

        } catch (Exception e) {

            throw new SQLException("이미지 업데이트에 실패했습니다.");

        }

    }

    @Transactional
    @Override
    public void delete(AnswerVO answerVO) throws SQLException {

        try {

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

        } catch (Exception e) {

            throw new SQLException("답변 삭제에 실패했습니다.");

        }
    }

    @Override
    public AnswerLikeVO addLike(AnswerLikeVO likeVO) throws SQLException {

        AnswerLikeVO resultLike;

        try {
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

            resultLike = answerMapper.countLike(likeVO.getAnsNo());

        } catch (Exception e) {

            throw new SQLException("좋아요에 실패했습니다.");

        }


        return resultLike;

    }

    @Override
    public AnswerLikeVO subtractLike(AnswerLikeVO likeVO) throws SQLException {

        AnswerLikeVO resultLike;

        try {
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

            resultLike = answerMapper.countLike(likeVO.getAnsNo());

        } catch (Exception e) {

            throw new SQLException("싫어요에 실패했습니다.");

        }

        return resultLike;

    }

    @Override
    public boolean checkAnswered(QuestionVO questionVO) throws SQLException {

        boolean res;

        try {

            res = answerMapper.checkAnswered(questionVO);

        } catch (Exception e) {

            throw new SQLException("답변 여부 조회에 실패했습니다.");

        }

        return res;
    }

    @Override
    public AnswerLikeVO checkLiked(AnswerVO answerVO) throws SQLException {

        AnswerLikeVO res;

        try {

            res = answerMapper.checkLiked(answerVO);

        } catch (Exception e) {
      System.out.println(e.getMessage());
            e.getStackTrace();
            throw new SQLException("답변 좋아요 여부 조회에 실패했습니다.");

        }

        return res;

    }

}
