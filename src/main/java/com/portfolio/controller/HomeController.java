package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.service.AnswerService;
import com.portfolio.service.QuestionCategoryService;
import com.portfolio.service.QuestionService;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    UserService userService;

    @Autowired
    QuestionCategoryService questionCategoryService;

    @GetMapping("/search")
    public String getSearchResults(@RequestParam String keyword, Model model) {

        return "index";
    }

    @GetMapping("")
    public String getPairs(Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(0);

        // 질문글 + 답변글 1:1 페어 리스트 model에 담기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionCategoryService.selectAll());

        return "index";
    }

    @GetMapping("more")
    public String getMorePairs(@RequestParam int quesNo, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);

        // 질문글 + 답변글 1:1 페어 리스트 model에 담기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        return "templates/pairTemplate";
    }

    @GetMapping("questions") // 미답변 질문글 목록
    public String getQuestions(Model model) {

        QuestionVO questionVO = new QuestionVO();

        // 답변 여부
        questionVO.setAnswered("N");

        model.addAttribute("questions", questionService.selectList(questionVO));

        return "questions";
    }

    @GetMapping("questions/more") // 미답변 질문글 목록 더보기
    public String getMoreQuestions(@RequestParam int quesNo, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setAnswered("N");

        List<QuestionVO> questions = questionService.selectList(questionVO);

        model.addAttribute("questions", questions);

        return "templates/questionTemplate";
    }

    @GetMapping("topic/{topic}")
    public String getQuestionsByTopic(@PathVariable String topic, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setCategoryName(topic);

        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionCategoryService.selectAll());

        model.addAttribute("topic", topic);

        return "index";
    }

    @GetMapping("topic/{topic}/more")
    public String getMoreQuestionsByTopic(@PathVariable String topic, @RequestParam int quesNo, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setCategoryName(topic);

        List<QAPairVO> pairs = questionService.selectPairList(questionVO);

        model.addAttribute("answerPairs", pairs);

        return "templates/pairTemplate";
    }

}
