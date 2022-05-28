package com.portfolio.service;

import com.portfolio.domain.*;
import com.portfolio.mapper.AnswerImageMapper;
import com.portfolio.mapper.AnswerMapper;
import com.portfolio.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;


@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerMapper answerMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AnswerImageMapper answerImageMapper;

    @Transactional
    @Override
    public int create(AnswerVO answerVO) {

        // answerVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        answerMapper.insert(answerVO);

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
    public AnswerVO select(int ansNo) {
        return answerMapper.select(ansNo);
    }

    @Override
    public List<AnswerVO> selectAnswers(int quesNo) {

        // 답변글 리스트 조회
        List<AnswerVO> answers = answerMapper.selectAnswers(quesNo);

        // 답변글 순회하며 유저 정보 담기
        for (AnswerVO answer : answers) {

            UserVO user = userMapper.select(answer.getUserNo());
            answer.setUserEmail(user.getEmail());
            answer.setUserName(user.getName());
            answer.setUserNo(user.getUserNo());
            answer.setUserPhoto(user.getPhoto());

        }

        return answers;
    }

    @Override
    public List<QAPairVO> selectAnsweredPairByUser(int userNo) {

        return answerMapper.selectAnsweredPairByUser(userNo);
    }

    @Override
    public void update(AnswerVO answerVO) {

        answerMapper.update(answerVO);
    }

    @Override
    public void delete(int postNo) {
        answerMapper.delete(postNo);
    }

    @Override
    public Map<String, Integer> addLike(LikeVO likeVO) {

        LikeVO like = answerMapper.findLike(likeVO);

        if (like == null) {

            answerMapper.addLike(likeVO);
            

        } else if (like.getLikeType().equals("UP")) {

            System.out.println(likeVO.getUserEmail());

            // 이미 좋아요 했다면 좋아요 취소
            answerMapper.deleteLike(likeVO);

        } else {

            // 싫어요 -> 좋아요 업데이트
            answerMapper.updateLike(likeVO);
        }

        return answerMapper.countLike(likeVO.getAnsNo());

    }

    @Override
    public Map<String, Integer> subtractLike(LikeVO likeVO) {

        LikeVO like = answerMapper.findLike(likeVO);

        if (like == null) {

            System.out.println("add dislike");


            answerMapper.addLike(likeVO);

        } else if (like.getLikeType().equals("DOWN")) {

            System.out.println("cancel dislike");


            // 이미 싫어요 했다면 싫어요 취소
            answerMapper.deleteLike(likeVO);

        } else {

            // 좋아요 -> 싫어요 업데이트
            System.out.println("like to dislike");
            answerMapper.updateLike(likeVO);
        }

        return answerMapper.countLike(likeVO.getAnsNo());

    }

}
