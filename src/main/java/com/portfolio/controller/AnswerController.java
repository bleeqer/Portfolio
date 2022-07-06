package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.mapper.ImageMapper;
import com.portfolio.service.AnswerService;
import com.portfolio.service.ImageService;
import com.portfolio.service.QuestionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("answer")
public class AnswerController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ImageService imageService;

    @PostMapping(value="create", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> createAnswer(@RequestBody AnswerVO answerVO, Principal principal) {

        logger.info("question number: " + answerVO.getQuesNo());
        logger.info("answer: " + answerVO.getAnswer());

        // 미로그인 유저일 때
        if (principal == null) {

            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);

        }

        // 작성자 셋팅
        answerVO.setUserEmail(principal.getName());

        AnswerVO answer;

        try {
            answerService.create(answerVO);
            answer = answerService.select(answerVO.getAnsNo());

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answer, HttpStatus.OK);

    }

    @GetMapping(value="select", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> selectAnswer(Integer ansNo) {

        logger.info("answer number: " + ansNo);

        AnswerVO answer;

        try {

            answer = answerService.select(ansNo);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answer, HttpStatus.OK);


    }

    @PostMapping(value="delete", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> deleteAnswer(@RequestBody AnswerVO answerVO) {

        logger.info("answer number: " + answerVO.getAnsNo());

        try {

            answerService.delete(answerVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answerVO.getAnsNo(), HttpStatus.OK);

    }

    @PostMapping(value="update", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateAnswer(@RequestBody AnswerVO answerVO) {

        logger.info("answer number: " + answerVO.getAnsNo());
        logger.info("answer: " + answerVO.getAnswer());
        logger.info("question number: " + answerVO.getQuesNo());

        AnswerVO updatedAnswer;

        try {

            answerService.update(answerVO);
            updatedAnswer = answerService.select(answerVO.getAnsNo());

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);

    }

    @GetMapping("")
    public String getAnswers(AnswerVO answerVO, Model model) throws SQLException {

        logger.info("question number: " + answerVO.getQuesNo());
        logger.info("email: " + answerVO.getUserEmail());

        List<AnswerVO> answers = answerService.selectAnswers(answerVO);

        model.addAttribute("answers", answers);

        return "templates/answerTemplate";

    }

    @GetMapping(value="like", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> likeAnswer(AnswerLikeVO likeVO, Principal principal) {

        logger.info("answer number: " + likeVO.getAnsNo());
        logger.info("like type: " + likeVO.getLikeType());

        // 미로그인 유저일 때
        if (principal == null) {

            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);

        }

        likeVO.setUserEmail(principal.getName());

        AnswerLikeVO answerLike;

        try {

            answerLike = answerService.addLike(likeVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answerLike, HttpStatus.OK);
    }

    @GetMapping(value="dislike", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> dislikeAnswer(AnswerLikeVO likeVO, Principal principal) {

        logger.info("answer number: " + likeVO.getAnsNo());
        logger.info("like type: " + likeVO.getLikeType());

        // 미로그인 유저일 때
        if (principal == null) {

            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);

        }

        likeVO.setUserEmail(principal.getName());

        AnswerLikeVO answerLike;

        try {

            answerLike = answerService.subtractLike(likeVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answerLike, HttpStatus.OK);

    }

    @GetMapping(value="checkAnswered", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> checkAnswered(Integer quesNo, Principal principal) {

        logger.info("question number: " + quesNo);

        // 미로그인 유저일 때
        if (principal == null) {

            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);

        }

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setUserEmail(principal.getName());

        boolean res;

        try {

            res = answerService.checkAnswered(questionVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping(value="checkLiked", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> checkLiked(Integer ansNo, Principal principal) {

        logger.info("answer number: " + ansNo);

        // 미로그인 유저일 때
        if (principal == null) {
            AnswerLikeVO like = new AnswerLikeVO();
            return new ResponseEntity<>(like, HttpStatus.OK);

        }

        AnswerVO answerVO = new AnswerVO();

        answerVO.setAnsNo(ansNo);

        answerVO.setUserEmail(principal.getName());

        AnswerLikeVO like;

        try {

            like = answerService.checkLiked(answerVO);


        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        if (like == null) {
            like = new AnswerLikeVO();
            like.setLikeType("None");
        }

        return new ResponseEntity<>(like, HttpStatus.OK);

    }

}
