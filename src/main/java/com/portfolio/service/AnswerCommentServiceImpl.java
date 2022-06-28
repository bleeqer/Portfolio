package com.portfolio.service;

import com.portfolio.domain.AnswerLikeVO;
import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.CommentVO;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.mapper.AnswerCommentMapper;
import com.portfolio.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerCommentServiceImpl implements AnswerCommentService {

    @Autowired
    AnswerCommentMapper answerCommentMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Transactional
    @Override
    public void insert(CommentVO commentVO) {

        answerCommentMapper.insert(commentVO);

    }

    @Override
    public CommentVO select(Integer coNo) {

        return answerCommentMapper.select(coNo);
    }

    @Override
    public List<CommentVO> selectList(CommentVO commentVO) {

        return answerCommentMapper.selectList(commentVO);
    }

    @Override
    public Integer selectLastCoNo(Integer ansNo) {
        return answerCommentMapper.selectLastCoNo(ansNo);
    }


    @Override
    public Integer update(CommentVO commentVO) {

        return answerCommentMapper.update(commentVO);
    }

    @Transactional
    @Override
    public void delete(CommentVO commentVO) {

        answerCommentMapper.delete(commentVO.getCoNo());

    }
    public Map<String, Integer> addLike(CommentLikeVO likeVO) {

        CommentLikeVO like = answerCommentMapper.findLike(likeVO);

        // likeType 구분 없이 findLike 후 likeType 셋팅
        likeVO.setLikeType("UP");

        System.out.println("add find like");

        if (like == null) {

            answerCommentMapper.addLike(likeVO);
            System.out.println("add add like");

        } else if (like.getLikeType().equals("UP")) {

            // 이미 좋아요 했다면 좋아요 취소
            answerCommentMapper.deleteLike(likeVO);
            System.out.println("add delete like");


        } else {

            // 싫어요 -> 좋아요 업데이트
            answerCommentMapper.updateLike(likeVO);
            System.out.println("add update like");

        }

        return answerCommentMapper.countLike(likeVO.getCoNo());

    }

    @Override
    public Map<String, Integer> subtractLike(CommentLikeVO likeVO) {

        CommentLikeVO like = answerCommentMapper.findLike(likeVO);

        // likeType 구분 없이 findLike 후 likeType 셋팅
        likeVO.setLikeType("DOWN");

        System.out.println("subtract find like");

        if (like == null) {

            answerCommentMapper.addLike(likeVO);
            System.out.println("subtract add like");


        } else if (like.getLikeType().equals("DOWN")) {

            // 이미 싫어요 했다면 싫어요 취소
            answerCommentMapper.deleteLike(likeVO);
            System.out.println("subtract delete like");


        } else {

            // 좋아요 -> 싫어요 업데이트
            answerCommentMapper.updateLike(likeVO);
            System.out.println("subtract update like");
        }

        return answerCommentMapper.countLike(likeVO.getCoNo());

    }

    @Override
    public CommentLikeVO checkLiked(CommentVO commentVO) {
        return answerCommentMapper.checkLiked(commentVO);
    }


}
