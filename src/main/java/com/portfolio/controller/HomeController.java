package com.portfolio.controller;

import com.portfolio.domain.QuestionCategoryVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.service.QuestionCategoryService;
import com.portfolio.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionCategoryService questionCategoryService;

    @GetMapping("/search")
    public String getSearchResults(@RequestParam String keyword, Model model) {

        return "index";
    }

    @GetMapping("")
    public String listPosts(Model model) {

//        List<QuestionVO> questions = questionService.readAll();

//        List<QuestionCategoryVO> categories = questionCategoryService.selectAll();
//
//        model.addAttribute("questions", questions);
//        model.addAttribute("categories", categories);
//        model.addAttribute("selectedTopic", "All");

        return "index";
    }

    @GetMapping("questions")
    public String getQuestions(Model model) {

        System.out.println("questions");

        List<QuestionVO> questions = questionService.selectAnswered("N");

        model.addAttribute("questions", questions);

        return "questions";
    }

//    @PostMapping("more")
    @GetMapping("more")
    @ResponseBody
    public ResponseEntity<List<QuestionVO>> getMoreQuestions(@RequestParam int quesNo) {

        int startQuesNo = quesNo - 1;

        List<QuestionVO> questions = questionService.getMore(startQuesNo);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("topic/{topic}")
    public String getQuestionsByTopic(@PathVariable String topic, Model model) {
        System.out.println(topic);
        List<QuestionVO> questions = questionService.readAllByTopic(topic);

        model.addAttribute("questions", questions);
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
