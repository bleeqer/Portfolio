package com.portfolio.service;

import com.portfolio.domain.CommentVO;
import com.portfolio.mapper.AnswerCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
public class AnswerCommentServiceImpl implements AnswerCommentService {

    @Autowired
    AnswerCommentMapper answerCommentMapper;

    @Transactional
    @Override
    public int create(CommentVO answerReply) {

        // answerReply 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        answerCommentMapper.insert(answerReply);

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
//        for (String uploadPath : answerReply.getImageList()) {
//            ImageVO imgVO = new ImageVO();
//
//            imgVO.setPostNo(answerReply.getAnsNo());
//            imgVO.setUploadPath(uploadPath);
//
//            answerImageMapper.insert(imgVO);
//        }

        return 1;
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
    public List<CommentVO> readMoreList(HashMap<String, Integer> map) {
        return answerCommentMapper.selectMoreList(map);
    }

    @Override
    public void update(CommentVO answerReply) {

        answerCommentMapper.update(answerReply);
    }

    @Override
    public void delete(HashMap<String, Integer> postNo) {
        answerCommentMapper.delete(postNo);
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
