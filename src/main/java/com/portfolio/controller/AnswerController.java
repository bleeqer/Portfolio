package com.portfolio.controller;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.AnswerLikeVO;
import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.service.AnswerService;
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

    @PostMapping("create")
    public String createAnswer(AnswerVO answerVO, Principal principal, Model model) {

        // 작성자 셋팅
        answerVO.setUserEmail(principal.getName());

        // 답변 생성 후 ansNo 받기
        int ansNo = answerService.create(answerVO);

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
    public AnswerVO selectAnswer(int ansNo) {

        return answerService.select(ansNo);
    }

    @PostMapping("delete")
    @ResponseBody
    public int deleteAnswer(@RequestBody AnswerVO answerVO) {

        System.out.println(answerVO.getAnsNo());
        System.out.println(answerVO.getAnswer());

        answerService.delete(answerVO);

        return answerVO.getAnsNo();
    }

    @PostMapping("edit")
    @ResponseBody
    public AnswerVO updateAnswer(AnswerVO answerVO) {

        answerService.update(answerVO);

        System.out.println(answerVO.getAnswer());
        System.out.println(answerVO.getAnsNo());

        return answerService.select(answerVO.getAnsNo());
    }



    @GetMapping("like")
    @ResponseBody
    public Map<String, Integer> likeAnswer(@RequestParam int ansNo, Principal principal) {

        AnswerLikeVO likeVO = new AnswerLikeVO();

        likeVO.setAnsNo(ansNo);
        likeVO.setUserEmail(principal.getName());

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
