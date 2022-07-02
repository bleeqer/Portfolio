package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.service.AnswerService;
import com.portfolio.service.CustomUserDetailsServiceImpl;
import com.portfolio.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    CustomUserDetailsServiceImpl userService;

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
    @ResponseBody
    public ResponseEntity<List<QAPairVO>> getMorePairs(@RequestParam Integer quesNo) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);

        return new ResponseEntity<>(questionService.selectPairList(questionVO), HttpStatus.OK);
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
    @ResponseBody
    public ResponseEntity<List<QuestionVO>> getMoreQuestions(QuestionVO questionVO) {

        questionVO.setAnswered("N");

        return new ResponseEntity<>(questionService.selectList(questionVO), HttpStatus.OK);
    }

    @GetMapping("questions/category/{categoryCode}")
    public String getQuestionsByTopic(@PathVariable Integer categoryCode, Model model) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setCategoryCode(categoryCode);

        model.addAttribute("questions", questionService.selectList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionService.selectCategories());

        SearchVO search = new SearchVO();
        search.setCategoryCode(categoryCode);

        model.addAttribute("search", search);

        return "questions";
    }

    @GetMapping("category/{categoryCode}")
    public String getPairsByTopic(@PathVariable Integer categoryCode, Model model) {

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
