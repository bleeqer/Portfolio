package com.portfolio.service;

import com.portfolio.domain.AnswerReplyVO;
import com.portfolio.mapper.AnswerReplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;


@Service
public class AnswerReplyServiceImpl implements AnswerReplyService {

    @Autowired
    AnswerReplyMapper answerReplyMapper;

    @Transactional
    @Override
    public int create(AnswerReplyVO answerReply) {

        // answerReply 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        answerReplyMapper.insert(answerReply);

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
//        for (String uploadPath : answerReply.getImageList()) {
//            ImageVO imgVO = new ImageVO();
//
//            imgVO.setPostNo(answerReply.getAnsNo());
//            imgVO.setUploadPath(uploadPath);
//
//            answerImageMapper.insert(imgVO);
//        }

        return answerReply.getReplyNo();
    }

    @Override
    public AnswerReplyVO readOne(int ansNo) {

        return answerReplyMapper.selectOne(ansNo);
    }

    @Override
    public List<AnswerReplyVO> readList(int ansNo) {

        return answerReplyMapper.selectList(ansNo);
    }

    @Override
    public List<AnswerReplyVO> readMoreList(int ansNo) {
        return answerReplyMapper.selectMoreList(ansNo);
    }

    @Override
    public void update(AnswerReplyVO answerReply) {

        answerReplyMapper.update(answerReply);
    }

    @Override
    public void delete(HashMap<String, Integer> postNo) {
        answerReplyMapper.delete(postNo);
    }

    @Override
    public List<AnswerReplyVO> readAll() {

        return answerReplyMapper.selectAll();

    }

    @Override
    public void addLikeCnt(int postNo) {
        answerReplyMapper.addLikeCnt(postNo);
    }

    @Override
    public int selectNestedMaxOrder(int parentCommentNo) {
        return answerReplyMapper.selectNestedMaxOrder(parentCommentNo);
    }

    @Override
    public int selectMaxOrder() {
        return answerReplyMapper.selectMaxOrder();
    }

}
