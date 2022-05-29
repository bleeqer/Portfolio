package com.portfolio.controller;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.LikeVO;
import com.portfolio.security.CustomUserDetails;
import com.portfolio.security.CustomUserDetailsService;
import com.portfolio.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("create")
    @ResponseBody
    public AnswerVO createAnswer(@RequestBody AnswerVO answer) {

        System.out.println(answer.getQuesNo());

        int answerNo = answerService.create(answer);

        return answerService.select(answerNo);
    }

    @GetMapping("like")
    @ResponseBody
    public Map<String, Integer> likeAnswer(@RequestParam int ansNo, Principal principal) {

        LikeVO likeVO = new LikeVO();

        likeVO.setAnsNo(ansNo);
        likeVO.setUserEmail(principal.getName());
        likeVO.setLikeType("UP");

        return answerService.addLike(likeVO);
    }

    @GetMapping("dislike")
    @ResponseBody
    public Map<String, Integer> dislikeAnswer(@RequestParam int ansNo, Principal principal) {

        LikeVO likeVO = new LikeVO();

        likeVO.setAnsNo(ansNo);
        likeVO.setUserEmail(principal.getName());
        likeVO.setLikeType("DOWN");

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
