package com.portfolio.service;

import com.portfolio.domain.CommentVO;
import com.portfolio.mapper.AnswerCommentMapper;
import com.portfolio.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
public class AnswerCommentServiceImpl implements AnswerCommentService {

    @Autowired
    AnswerCommentMapper answerCommentMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Transactional
    @Override
    public void insert(CommentVO commentVO) {

        answerMapper.addCommentCnt(commentVO.getAnsNo());
        answerCommentMapper.insert(commentVO);

    }

    @Override
    public CommentVO readOne(int ansNo) {

        return answerCommentMapper.selectOne(ansNo);
    }

    @Override
    public List<CommentVO> selectList(CommentVO commentVO) {

        return answerCommentMapper.selectList(commentVO);
    }


    @Override
    public void update(CommentVO answerReply) {

        answerCommentMapper.update(answerReply);
    }

    @Override
    public void delete(int coNo) {
        answerCommentMapper.delete(coNo);
    }

    @Override
    public List<CommentVO> readAll() {

        return answerCommentMapper.selectAll();

    }

    @Override
    public void addLikeCnt(int postNo) {
        answerCommentMapper.addLikeCnt(postNo);
    }

    @Override
    public int selectNestedMaxOrder(int parentCommentNo) {
        return answerCommentMapper.selectNestedMaxOrder(parentCommentNo);
    }

    @Override
    public int selectMaxOrder() {
        return answerCommentMapper.selectMaxOrder();
    }

}
