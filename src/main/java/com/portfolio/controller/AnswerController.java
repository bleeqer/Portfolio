package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.mapper.ImageMapper;
import com.portfolio.service.AnswerService;
import com.portfolio.service.ImageService;
import com.portfolio.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public String createAnswer(@RequestBody AnswerVO answerVO, Principal principal, Model model) {

        // 작성자 셋팅
        answerVO.setUserEmail(principal.getName());

        // 답변 생성 후 ansNo 받기
        long ansNo = answerService.create(answerVO);

        // 생성된 답변 가져오기
        AnswerVO createdAnswer = answerService.select(ansNo);

        // answerTemplate 사용을 위해 answers list에 넣기
        List<AnswerVO> answers = new ArrayList<>();

        answers.add(createdAnswer);

        model.addAttribute("answers", answers);

        return "templates/answerTemplate";

    }

    @GetMapping("select")
    @ResponseBody
    public AnswerVO selectAnswer(long ansNo) {

        return answerService.select(ansNo);
    }

    @PostMapping("delete")
    @ResponseBody
    public long deleteAnswer(@RequestBody AnswerVO answerVO) {

        answerService.delete(answerVO);

        return answerVO.getAnsNo();
    }

    @PostMapping("edit")
    @ResponseBody
    public AnswerVO updateAnswer(AnswerVO answerVO) {

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
    public Map<String, Long> likeAnswer(@RequestParam long ansNo, Principal principal) {

        AnswerLikeVO likeVO = new AnswerLikeVO();

        likeVO.setAnsNo(ansNo);
        likeVO.setUserEmail(principal.getName());

        return answerService.addLike(likeVO);
    }

    @GetMapping("dislike")
    @ResponseBody
    public Map<String, Long> dislikeAnswer(@RequestParam long ansNo, Principal principal) {

        AnswerLikeVO likeVO = new AnswerLikeVO();

        likeVO.setAnsNo(ansNo);
        likeVO.setUserEmail(principal.getName());

        return answerService.subtractLike(likeVO);
    }

    @GetMapping("checkAnswered")
    @ResponseBody
    public boolean checkAnswered(long quesNo, Principal principal) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setUserEmail(principal.getName());

        return answerService.checkAnswered(questionVO);
    }

    @GetMapping("checkLiked")
    @ResponseBody
    public AnswerLikeVO checkLiked(long ansNo, Principal principal) {

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
//    public String likeAnswer(long ansNo) {
//
//        long likeCnt = answerService.addLikeCnt(ansNo);
//
//        return
//    }
}
