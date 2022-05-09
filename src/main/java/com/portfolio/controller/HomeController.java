package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.service.AnswerService;
import com.portfolio.service.QuestionCategoryService;
import com.portfolio.service.QuestionService;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
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
    public String getQnAPairs(Model model) {

        // 질문글 + 답변글 1:1 페어 리스트 model에 담기
        model.addAttribute("answerPairs", questionService.selectAnsweredPair(0));

        // 카테고리 목록 model에 담기
        model.addAttribute("categories", questionCategoryService.selectAll());

        return "index";
    }

    @GetMapping("questions") // 미답변 질문글 목록
    public String getQuestions(Model model) {

        QuestionVO questionVO = new QuestionVO();

        // 첫번째 미답변 질문글부터 가져오기
        questionVO.setQuesNo(0);
        questionVO.setAnswered("N");

        List<QuestionVO> questions = questionService.selectListByAnswered(questionVO);

        for (QuestionVO question : questions) {
            System.out.println(question.getCategoryFullPath());
        }

        model.addAttribute("questions", questions);

        return "questions";
    }

    @GetMapping("questions/more") // 미답변 질문글 목록 더보기
    @ResponseBody
    public List<QuestionVO> getMoreQuestions(@RequestParam int quesNo) {

        QuestionVO questionVO = new QuestionVO();

        questionVO.setQuesNo(quesNo);
        questionVO.setAnswered("N");

        return questionService.selectListByUser(questionVO);
    }

    @GetMapping("topic/{topic}")
    public String getQuestionsByTopic(@PathVariable String topic, Model model) {

//        List<QuestionVO> questions = questionService.;
//
//        model.addAttribute("questions", questions);
        model.addAttribute("selectedTopic", topic);

        return "index";
    }

    @PostMapping("topic/more/{topic}")
    public ResponseEntity<List<QuestionVO>> getMoreQuestionsByTopic(@PathVariable String topic, @RequestParam int quesNo, Model model) {

        System.out.println("topic: " + topic);

        if (!topic.equals("All")) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("quesNo", quesNo);
            map.put("topic", topic);
            List<QuestionVO> questions = questionService.getMoreByTopic(map);

            model.addAttribute("questions", questions);

            return new ResponseEntity<>(questions, HttpStatus.OK);
        }

        List<QuestionVO> questions = questionService.getMore(quesNo);

        model.addAttribute("questions", questions);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }



}
