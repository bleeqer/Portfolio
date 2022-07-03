package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.mapper.ImageMapper;
import com.portfolio.service.AnswerService;
import com.portfolio.service.ImageService;
import com.portfolio.service.QuestionService;
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

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @Autowired
    ImageService imageService;

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<Object> createAnswer(@RequestBody AnswerVO answerVO, Principal principal) {

        // 작성자 셋팅
        answerVO.setUserEmail(principal.getName());

        AnswerVO answer;

        try {
            answerService.create(answerVO);
            answer = answerService.select(answerVO.getAnsNo());

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answer, HttpStatus.OK);

    }

    @GetMapping("select")
    @ResponseBody
    public ResponseEntity<Object> selectAnswer(Integer ansNo) {

        AnswerVO answer;

        try {

            answer = answerService.select(ansNo);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answer, HttpStatus.OK);


    }

    @PostMapping("delete")
    @ResponseBody
    public ResponseEntity<Object> deleteAnswer(@RequestBody AnswerVO answerVO) {

        try {

            answerService.delete(answerVO);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answerVO.getAnsNo(), HttpStatus.OK);

    }

    @PostMapping("edit")
    @ResponseBody
    public ResponseEntity<Object> updateAnswer(@RequestBody AnswerVO answerVO) {

        AnswerVO updatedAnswer;

        try {

            answerService.update(answerVO);
            updatedAnswer = answerService.select(answerVO.getAnsNo());

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(updatedAnswer, HttpStatus.OK);

    }

    @GetMapping("")
    public String getAnswers(AnswerVO answerVO, Model model) throws SQLException {

        List<AnswerVO> answers = answerService.selectAnswers(answerVO);

        model.addAttribute("answers", answers);

        return "templates/answerTemplate";

    }

    @GetMapping("like")
    @ResponseBody
    public ResponseEntity<Object> likeAnswer(AnswerLikeVO likeVO, Principal principal) {

        likeVO.setUserEmail(principal.getName());

        AnswerLikeVO answerLike;

        try {

            answerLike = answerService.addLike(likeVO);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answerLike, HttpStatus.OK);
    }

    @GetMapping("dislike")
    @ResponseBody
    public ResponseEntity<Object> dislikeAnswer(AnswerLikeVO likeVO, Principal principal) {

        likeVO.setUserEmail(principal.getName());

        AnswerLikeVO answerLike;

        try {

            answerLike = answerService.subtractLike(likeVO);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(answerLike, HttpStatus.OK);

    }

    @GetMapping("checkAnswered")
    @ResponseBody
    public ResponseEntity<Object> checkAnswered(Integer quesNo, Principal principal) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setUserEmail(principal.getName());

        boolean res;

        try {

            res = answerService.checkAnswered(questionVO);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("checkLiked")
    @ResponseBody
    public ResponseEntity<Object> checkLiked(Integer ansNo, Principal principal) {

        AnswerVO answerVO = new AnswerVO();

        answerVO.setAnsNo(ansNo);

        answerVO.setUserEmail(principal.getName());

        AnswerLikeVO like;

        try {

            like = answerService.checkLiked(answerVO);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        if (like == null) {
            like = new AnswerLikeVO();
            like.setLikeType("None");
        }

        return new ResponseEntity<>(like, HttpStatus.OK);

    }

}
