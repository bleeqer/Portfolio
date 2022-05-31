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

        HashMap<String, Integer> map = new HashMap<>();

        // 답변글 번호
        map.put("ansNo", commentVO.getAnsNo());

        // comment_cnt 더하기 갯수 1
        map.put("coCnt", 1);

        // 코멘트 갯수 업데이트
        answerMapper.updateCommentCnt(map);

        answerCommentMapper.insert(commentVO);

    }

    @Override
    public CommentVO select(int coNo) {

        return answerCommentMapper.select(coNo);
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
    public int update(CommentVO commentVO) {

        return answerCommentMapper.update(commentVO);
    }

    @Transactional
    @Override
    public void delete(CommentVO commentVO) {

        // 코멘트 트리 삭제하고 삭제된 row 갯수 반환
        int res = answerCommentMapper.delete(commentVO.getCoNo());

        HashMap<String, Integer> map = new HashMap<>();

        // 답변글 번호
        map.put("ansNo", commentVO.getAnsNo());

        // 삭제된 댓글 갯수 음수 변환하여 매핑
        map.put("coCnt", -res);

        answerMapper.updateCommentCnt(map);
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
