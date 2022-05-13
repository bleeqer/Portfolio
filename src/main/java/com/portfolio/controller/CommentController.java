package com.portfolio.controller;

import com.portfolio.domain.CommentVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.service.AnswerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    AnswerCommentService answerCommentService;

    @GetMapping("{ansNo}")
    public String getComments(@PathVariable int ansNo, Model model) {
        CommentVO commentVO = new CommentVO();

        commentVO.setAnsNo(ansNo);
//        commentVO.setCoNo(lastCommentNo);
        model.addAttribute("comments", answerCommentService.selectList(commentVO));



        return "templates/commentTemplate";

    }
}
