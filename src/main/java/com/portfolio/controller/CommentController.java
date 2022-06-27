package com.portfolio.controller;

import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.domain.CommentVO;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.service.AnswerCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ResponseBody
    public ResponseEntity<Object> createComment(CommentVO commentVO, Principal principal) {

        commentVO.setUserEmail(principal.getName());

        answerCommentService.insert(commentVO);

        return new ResponseEntity<>(answerCommentService.select(commentVO.getCoNo()), HttpStatus.OK);
    }

    @GetMapping("select")
    @ResponseBody
    public CommentVO selectComment(int coNo) {
        return answerCommentService.select(coNo);
    }

    @PostMapping("update")
    @ResponseBody
    public CommentVO updateComment(CommentVO commentVO, Principal principal) {
        
        // 예외처리 할 것
        long res = 0;

        if (commentVO.getUserEmail().equals(principal.getName())) {

            res = answerCommentService.update(commentVO);

        }

        return commentVO;
    }

    @PostMapping("delete")
    @ResponseBody
    public long deleteComment(@RequestBody CommentVO commentVO) {

        answerCommentService.delete(commentVO);

        return commentVO.getCoNo();
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<List<CommentVO>> getComments(CommentVO commentVO) {

        List<CommentVO> comments = answerCommentService.selectList(commentVO);

        return new ResponseEntity<>(comments, HttpStatus.OK);

    }

    @GetMapping("like")
    @ResponseBody
    public Map<String, Long> likeComment(@RequestParam Long coNo, Principal principal) {

        CommentLikeVO likeVO = new CommentLikeVO();

        likeVO.setCoNo(coNo);
        likeVO.setUserEmail(principal.getName());

        return answerCommentService.addLike(likeVO);
    }

    @GetMapping("dislike")
    @ResponseBody
    public Map<String, Long> dislikeComment(@RequestParam Long coNo, Principal principal) {

        CommentLikeVO likeVO = new CommentLikeVO();

        likeVO.setCoNo(coNo);
        likeVO.setUserEmail(principal.getName());

        return answerCommentService.subtractLike(likeVO);
    }

    @GetMapping("checkLiked")
    @ResponseBody
    public CommentLikeVO checkLiked(long coNo, Principal principal) {

        CommentVO commentVO = new CommentVO();

        commentVO.setCoNo(coNo);
        commentVO.setUserEmail(principal.getName());

        CommentLikeVO likeVO = answerCommentService.checkLiked(commentVO);

        if (likeVO == null) {
            likeVO = new CommentLikeVO();
            likeVO.setLikeType("None");
        }

        return likeVO;
    }

    @GetMapping("checkLast")
    @ResponseBody
    public boolean checkLast(CommentVO commentVO) {

        long lastCoNo = answerCommentService.selectLastCoNo(commentVO.getAnsNo());

        if (commentVO.getCoNo() == lastCoNo) {
            return true;
        }
        return false;
    }

}
