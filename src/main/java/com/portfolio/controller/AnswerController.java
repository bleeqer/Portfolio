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
    public ResponseEntity<AnswerVO> createAnswer(@RequestBody AnswerVO answerVO, Principal principal) throws SQLException {

        // 작성자 셋팅
        answerVO.setUserEmail(principal.getName());

        // 답변 생성 후 ansNo 받기
        Integer ansNo = answerService.create(answerVO);



        return new ResponseEntity<>(answerService.select(ansNo), HttpStatus.OK);

    }

    @GetMapping("select")
    @ResponseBody
    public AnswerVO selectAnswer(Integer ansNo) {

        System.out.println("외않" + ansNo);

        return answerService.select(ansNo);
    }

    @PostMapping("delete")
    @ResponseBody
    public Integer deleteAnswer(@RequestBody AnswerVO answerVO) {

        answerService.delete(answerVO);

        return answerVO.getAnsNo();
    }

    @PostMapping("edit")
    @ResponseBody
    public AnswerVO updateAnswer(@RequestBody AnswerVO answerVO) {

        answerService.update(answerVO);

        return answerService.select(answerVO.getAnsNo());
    }

    @GetMapping("")
    public String getAnswers(AnswerVO answerVO, Model model) {

        List<AnswerVO> answers = answerService.selectAnswers(answerVO);

        model.addAttribute("answers", answers);

        return "templates/answerTemplate";

    }

    @GetMapping("like")
    @ResponseBody
    public ResponseEntity<AnswerLikeVO> likeAnswer(AnswerLikeVO likeVO, Principal principal) {

        System.out.println(likeVO.getAnsNo());
        likeVO.setUserEmail(principal.getName());

        return new ResponseEntity<>(answerService.addLike(likeVO), HttpStatus.OK);
    }

    @GetMapping("dislike")
    @ResponseBody
    public ResponseEntity<AnswerLikeVO> dislikeAnswer(AnswerLikeVO likeVO, Principal principal) {

        System.out.println(likeVO.getAnsNo());
        likeVO.setUserEmail(principal.getName());

        return new ResponseEntity<>(answerService.subtractLike(likeVO), HttpStatus.OK);

    }

    @GetMapping("checkAnswered")
    @ResponseBody
    public boolean checkAnswered(Integer quesNo, Principal principal) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setUserEmail(principal.getName());

        return answerService.checkAnswered(questionVO);
    }

    @GetMapping("checkLiked")
    @ResponseBody
    public AnswerLikeVO checkLiked(Integer ansNo, Principal principal) {

        AnswerVO answerVO = new AnswerVO();

        answerVO.setAnsNo(ansNo);
        answerVO.setUserEmail(principal.getName());

        AnswerLikeVO likeVO = answerService.checkLiked(answerVO);

        if (likeVO == null) {
            likeVO = new AnswerLikeVO();
            likeVO.setLikeType("None");
        }

        return likeVO;
    }

//    @GetMapping("like")
//    @ResponseBody
//    public String likeAnswer(Integer ansNo) {
//
//        Integer likeCnt = answerService.addLikeCnt(ansNo);
//
//        return
//    }
}
