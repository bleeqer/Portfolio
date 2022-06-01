package com.portfolio.controller;

import com.portfolio.domain.CommentLikeVO;
import com.portfolio.domain.CommentVO;
import com.portfolio.domain.AnswerLikeVO;
import com.portfolio.service.AnswerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    AnswerCommentService answerCommentService;

    @PostMapping("create")
    public String createComment(CommentVO commentVO, Principal principal, Model model) {

        commentVO.setUserEmail(principal.getName());

        answerCommentService.insert(commentVO);

        // commentTemplate 사용을 위해 comments list에 넣기
        List<CommentVO> comments = new ArrayList<>();

        comments.add(answerCommentService.select(commentVO.getCoNo()));

        model.addAttribute("comments", comments);

        return "templates/commentTemplate";
    }

    @GetMapping("select")
    @ResponseBody
    public CommentVO selectComment(int coNo) {
        return answerCommentService.select(coNo);
    }

    @GetMapping("update")
    @ResponseBody
    public CommentVO updateComment(CommentVO commentVO, Principal principal) {
        
        // 예외처리 할 것
        int res = 0;

        if (commentVO.getUserEmail().equals(principal.getName())) {

            res = answerCommentService.update(commentVO);

        }

        return commentVO;
    }

    @PostMapping("delete")
    @ResponseBody
    public int deleteComment(CommentVO commentVO) {

        answerCommentService.delete(commentVO);

        return commentVO.getCoNo();
    }

    @GetMapping("")
    public String getComments(CommentVO commentVO, Model model) {

        List<CommentVO> comments = answerCommentService.selectList(commentVO);

        for (CommentVO comment : comments) {
            System.out.println(comment.getLevel());
        }

        int lastCoNo = answerCommentService.selectLastCoNo(commentVO.getAnsNo());

        model.addAttribute("comments", comments);

        model.addAttribute("isLast", "N");

        // 마지막 댓글일 시 isLast = Y
        if (!comments.isEmpty() && comments.get(comments.size() - 1).getCoNo() <= lastCoNo) {
            model.addAttribute("isLast", "Y");
        }

        return "templates/commentTemplate";

    }

    @GetMapping("like")
    @ResponseBody
    public Map<String, Integer> likeComment(@RequestParam int coNo, Principal principal) {

        CommentLikeVO likeVO = new CommentLikeVO();

        likeVO.setCoNo(coNo);
        likeVO.setUserEmail(principal.getName());

        System.out.println(likeVO);

        return answerCommentService.addLike(likeVO);
    }

    @GetMapping("dislike")
    @ResponseBody
    public Map<String, Integer> dislikeComment(@RequestParam int coNo, Principal principal) {

        CommentLikeVO likeVO = new CommentLikeVO();

        likeVO.setCoNo(coNo);
        likeVO.setUserEmail(principal.getName());

        return answerCommentService.subtractLike(likeVO);
    }

}
