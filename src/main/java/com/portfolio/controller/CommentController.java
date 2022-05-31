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

        System.out.println("외않되");

        System.out.println(commentVO.getAnsNo());
        System.out.println(commentVO.getParentCoNo());
        System.out.println(commentVO.getAnswerComment());
        System.out.println(commentVO.getUserEmail());

        answerCommentService.insert(commentVO);
    }

    @GetMapping("delete")
    @ResponseBody
    public int deleteComment(CommentVO commentVO) {

        answerCommentService.delete(commentVO);

        return commentVO.getCoNo();
    }

    @GetMapping("")
    public String getComments(CommentVO commentVO, Model model) {

        List<CommentVO> comments = answerCommentService.selectList(commentVO);

        int lastCoNo = answerCommentService.selectLastCoNo(commentVO.getAnsNo());

        model.addAttribute("comments", comments);

        model.addAttribute("isLast", "N");

        // 마지막 댓글일 시 isLast = Y
        if (!comments.isEmpty() && comments.get(comments.size() - 1).getCoNo() <= lastCoNo) {
            model.addAttribute("isLast", "Y");
        }

        return "templates/commentTemplate";

    }

}
