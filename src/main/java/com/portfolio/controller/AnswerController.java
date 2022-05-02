package com.portfolio.controller;

import com.portfolio.domain.AnswerVO;
import com.portfolio.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        System.out.println("answer number: " + answerNo);

        return answerService.readOne(answerNo);
    }

    @PostMapping("more")
    @ResponseBody
    public List<AnswerVO> getMoreAnswers(@RequestBody AnswerVO answer) {

        return answerService.readMoreList(answer);
    }
}
