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
        System.out.println(answer.getContent());
        System.out.println(answer.getWriter());

        int answerNo = answerService.create(answer);

        System.out.println("answer number: " + answerNo);
        System.out.println("answer content: " + answerService.readOne(answerNo).getContent());

        return answerService.readOne(answerNo);
    }

    @PostMapping("more")
    @ResponseBody
    public List<AnswerVO> getMoreAnswers(@RequestBody AnswerVO answer) {

        List<AnswerVO> answerList = answerService.readMoreList(answer.getAnsNo());

        for (AnswerVO ans : answerList) {
            System.out.println(ans.getAnsNo());
        }
        return answerService.readMoreList(answer.getAnsNo());
    }
}
