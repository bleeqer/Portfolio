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

import java.sql.SQLException;
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

//    @GetMapping("/search")
//    public String getSearchResults(@RequestParam String keyword, Model model) {
//
//        return "index";
//    }

    @GetMapping("")
    public String getPairs(Model model) throws SQLException {

        QuestionVO questionVO = new QuestionVO();

        // 질문글 + 답변글 1:1 페어 리스트 model에 담기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionService.selectCategories());

        return "index";
    }

    @GetMapping("more")
    @ResponseBody
    public ResponseEntity<Object> getMorePairs(QuestionVO questionVO) {

        List<QAPairVO> pairList;

        try {

            pairList = questionService.selectPairList(questionVO);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(pairList, HttpStatus.OK);
    }

    @GetMapping("questions") // 미답변 질문글 목록
    public String getQuestions(Model model) throws SQLException {

        QuestionVO questionVO = new QuestionVO();

        // 답변 여부
        questionVO.setAnswered("N");

        model.addAttribute("categories", questionService.selectCategories());

        model.addAttribute("questions", questionService.selectList(questionVO));

        return "questions";
    }

    @GetMapping("questions/more") // 미답변 질문글 목록 더보기
    @ResponseBody
    public ResponseEntity<Object> getMoreQuestions(QuestionVO questionVO) {

        questionVO.setAnswered("N");

        List<QuestionVO> questionList;

        try {

            questionList = questionService.selectList(questionVO);

        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(questionList, HttpStatus.OK);
    }

    @GetMapping("questions/category/{categoryCode}")
    public String getQuestionsByTopic(@PathVariable Integer categoryCode, Model model) throws SQLException {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setCategoryCode(categoryCode);

        model.addAttribute("questions", questionService.selectList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionService.selectCategories());

        model.addAttribute("selectedCategory", categoryCode);


        return "questions";
    }

    @GetMapping("category/{categoryCode}")
    public String getPairsByTopic(@PathVariable Integer categoryCode, Model model) throws SQLException {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setCategoryCode(categoryCode);

        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        // 카테고리 리스트 담기
        model.addAttribute("categories", questionService.selectCategories());

        model.addAttribute("selectedCategory", categoryCode);

        return "index";
    }

//    @GetMapping("search")
//    public String searchPairs(SearchVO searchVO) {
//        System.out.println(searchVO.getKeyword());
//        System.out.println(searchVO.getQuesNo());
//
//        return "index";
//    }

    @GetMapping("search/questions")
    public String searchQuestions(QuestionVO questionVO, Model model) throws SQLException {

        model.addAttribute("keyword", questionVO.getSearchKeyword());

        model.addAttribute("categories", questionService.selectCategories());

        model.addAttribute("questions", questionService.selectList(questionVO));

        return "questions";
    }

    @GetMapping("search/pairs")
    public String searchPairs(QuestionVO questionVO, Model model) throws SQLException {

        model.addAttribute("keyword", questionVO.getSearchKeyword());

        model.addAttribute("categories", questionService.selectCategories());

        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        return "index";
    }

}
