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

        // 답변글 번호, comment_cnt 더하기 갯수 1
        answerMapper.updateCommentCnt(commentVO.getAnsNo(), 1);

        answerCommentMapper.insert(commentVO);

    }

    @Override
    public CommentVO select(int ansNo) {

        return answerCommentMapper.select(ansNo);
    }

    @Override
    public List<CommentVO> selectList(CommentVO commentVO) {

        return answerCommentMapper.selectList(commentVO);
    }

    @Override
    public int selectLastCoNo(int ansNo) {
        return answerCommentMapper.selectLastCoNo(ansNo);
    }


    @Override
    public void update(CommentVO answerReply) {

        answerCommentMapper.update(answerReply);
    }

    @Transactional
    @Override
    public void delete(CommentVO commentVO) {

        // 코멘트 트리 삭제하고 삭제된 row 갯수 반환
        int res = answerCommentMapper.delete(commentVO.getCoNo());

        // 해당 답변글의 삭제된 comment row 갯수만큼 답변글 comment_cnt 값 빼기
        answerMapper.updateCommentCnt(commentVO.getAnsNo(), -res);
    }

//    @Override
//    public void addLikeCnt(int postNo) {
//        answerCommentMapper.addLikeCnt(postNo);
//    }

//    @Override
//    public int selectNestedMaxOrder(int parentCommentNo) {
//        return answerCommentMapper.selectNestedMaxOrder(parentCommentNo);
//    }
//
//    @Override
//    public int selectMaxOrder() {
//        return answerCommentMapper.selectMaxOrder();
//    }

}
