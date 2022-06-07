package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.service.AnswerService;
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

    @GetMapping("/search")
    public String getSearchResults(@RequestParam String keyword, Model model) {

        return "index";
    }

    @GetMapping("")
    public String getPairs(Model model) {

        QuestionVO questionVO = new QuestionVO();

        // 질문글 + 답변글 1:1 페어 리스트 model에 담기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionService.selectCategories());

        return "index";
    }

    @GetMapping("more")
    public String getMorePairs(@RequestParam Long quesNo, Model model) {

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

        model.addAttribute("categories", questionService.selectCategories());

        model.addAttribute("questions", questionService.selectList(questionVO));

        return "questions";
    }

    @GetMapping("questions/more") // 미답변 질문글 목록 더보기
    public String getMoreQuestions(@RequestParam Long quesNo, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setAnswered("N");

        List<QuestionVO> questions = questionService.selectList(questionVO);

        model.addAttribute("questions", questions);

        return "templates/questionTemplate";
    }

    @GetMapping("category/{categoryCode}")
    public String getQuestionsByTopic(@PathVariable Long categoryCode, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setCategoryCode(categoryCode);

        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionService.selectCategories());

        SearchVO search = new SearchVO();
        search.setCategoryCode(categoryCode);

        model.addAttribute("search", search);

        return "index";
    }

    @GetMapping("category/{categoryCode}/more")
    public String getMoreQuestionsByTopic(@PathVariable String categoryCode, @RequestParam Long quesNo, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setCategoryName(categoryCode);

        List<QAPairVO> pairs = questionService.selectPairList(questionVO);

        model.addAttribute("answerPairs", pairs);

        return "templates/pairTemplate";
    }

    @GetMapping("search/questions")
    public String searchQs(SearchVO searchVO, Model model) {

        List<QuestionVO> questions = questionService.searchQuestions(searchVO);

        model.addAttribute("categories", questionService.selectCategories());
        model.addAttribute("search", searchVO);
        model.addAttribute("questions", questions);

        return "questions";

    }

    @GetMapping("search/pairs")
    public List<QAPairVO> searchPs(SearchVO searchVO) {

        return questionService.searchPairs(searchVO);
    }

}
