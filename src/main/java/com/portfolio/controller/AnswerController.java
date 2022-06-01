package com.portfolio.controller;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.AnswerLikeVO;
import com.portfolio.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("create")
    public String createAnswer(AnswerVO answer, Principal principal, Model model) {

        // 작성자 셋팅
        answer.setUserEmail(principal.getName());

        int ansNo = answerService.create(answer);

        AnswerVO createdAnswer = answerService.select(ansNo);

        // answerTemplate 사용을 위해 answers list에 넣기
        List<AnswerVO> answers = new ArrayList<>();

        answers.add(createdAnswer);

        model.addAttribute("answers", answers);

        return "templates/answerTemplate";
    }

    @PostMapping("delete")
    @ResponseBody
    public int deleteAnswer(@RequestBody AnswerVO answerVO) {

        answerService.delete(answerVO);

        return answerVO.getAnsNo();
    }

    @GetMapping("like")
    @ResponseBody
    public Map<String, Integer> likeAnswer(@RequestParam int ansNo, Principal principal) {

        AnswerLikeVO likeVO = new AnswerLikeVO();

        likeVO.setAnsNo(ansNo);
        likeVO.setUserEmail(principal.getName());

        System.out.println(likeVO);

        return answerService.addLike(likeVO);
    }

    @GetMapping("dislike")
    @ResponseBody
    public Map<String, Integer> dislikeAnswer(@RequestParam int ansNo, Principal principal) {

        AnswerLikeVO likeVO = new AnswerLikeVO();

        likeVO.setAnsNo(ansNo);
        likeVO.setUserEmail(principal.getName());

        return answerService.subtractLike(likeVO);
    }

//    @PostMapping("more")
//    @ResponseBody
//    public List<AnswerVO> getMoreAnswers(@RequestBody AnswerVO answer) {
//
//        return answerService.readMoreList(answer);
//    }

//    @GetMapping("like")
//    @ResponseBody
//    public String likeAnswer(int ansNo) {
//
//        int likeCnt = answerService.addLikeCnt(ansNo);
//
//        return
//    }
}
