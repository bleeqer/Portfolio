package com.portfolio.controller;

import com.portfolio.domain.CommentVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.service.AnswerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    AnswerCommentService answerCommentService;

    @PostMapping("create")
    public void createComment(CommentVO commentVO) {

        System.out.println(commentVO.getAnsNo());
        System.out.println(commentVO.getParentCoNo());
        System.out.println(commentVO.getAnswerComment());
        System.out.println(commentVO.getUserEmail());

        answerCommentService.insert(commentVO);
    }

    @PostMapping("delete")
    public void deleteComment(CommentVO commentVO) {

        answerCommentService.delete(commentVO.getCoNo());
    }

    @GetMapping("")
    public String getComments(CommentVO commentVO, Model model) {

        System.out.println(commentVO.getLastCoNo());

        model.addAttribute("comments", answerCommentService.selectList(commentVO));

        return "templates/commentTemplate";

    }

}
