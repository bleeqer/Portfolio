package com.portfolio.controller;

import com.portfolio.domain.QuestionVO;
import com.portfolio.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    QuestionService questionService;

    @RequestMapping("")
    public String listPosts(Model model) {

        List<QuestionVO> questions = questionService.readAll();

        model.addAttribute("questions", questions);

        return "index";
    }




}
