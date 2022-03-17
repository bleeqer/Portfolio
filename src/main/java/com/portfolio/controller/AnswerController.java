package com.portfolio.controller;

import com.portfolio.domain.AnswerVO;
import com.portfolio.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("create")
    public String createAnswer(AnswerVO answer) {

        answerService.create(answer);

        return "";
    }
}
