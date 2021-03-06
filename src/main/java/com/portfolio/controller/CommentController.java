package com.portfolio.controller;

import com.portfolio.domain.*;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.service.AnswerCommentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("comment")
public class CommentController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    AnswerCommentService answerCommentService;

    @PostMapping(value="create", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> createComment(CommentVO commentVO, Principal principal) {

        logger.info("comment: " + commentVO.getAnswerComment());
        logger.info("parent comment number: " + commentVO.getParentCoNo());
        logger.info("photo: " + commentVO.getUserPhoto());
        logger.info("occupation: " + commentVO.getUserOccupation());

        // 미로그인 유저일 때
        if (principal == null) {

            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);

        }

        CommentVO comment;

        commentVO.setUserEmail(principal.getName());

        try {

            answerCommentService.insert(commentVO);
            comment = answerCommentService.select(commentVO.getCoNo());

        } catch (SQLException e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping(value="select", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> selectComment(int coNo) {

        logger.info("comment number: " + coNo);

        CommentVO comment;

        try {

            comment = answerCommentService.select(coNo);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping(value="update", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> updateComment(CommentVO commentVO, Principal principal) {

        logger.info("comment number: " + commentVO.getCoNo());
        logger.info("comment: " + commentVO.getAnswerComment());
        logger.info("parent comment number: " + commentVO.getParentCoNo());
        logger.info("email: " + commentVO.getUserEmail());
        logger.info("photo: " + commentVO.getUserPhoto());
        logger.info("occupation: " + commentVO.getUserOccupation());

        if (!commentVO.getUserEmail().equals(principal.getName())) {

            return new ResponseEntity<>("작성자가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);

        }

        CommentVO comment;


        try {

            answerCommentService.update(commentVO);
            comment = answerCommentService.select(commentVO.getCoNo());

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(comment, HttpStatus.OK);

    }

    @PostMapping(value="delete", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> deleteComment(@RequestBody CommentVO commentVO) {

        logger.info("comment number: " + commentVO.getCoNo());

        try {

            answerCommentService.delete(commentVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(commentVO.getCoNo(), HttpStatus.OK);

    }

    @GetMapping(value="", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> getComments(CommentVO commentVO) {

        logger.info("answer number: " + commentVO.getAnsNo());

        List<CommentVO> comments;

        try {

            comments = answerCommentService.selectList(commentVO);

        } catch (SQLException e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(comments, HttpStatus.OK);

    }

    @GetMapping(value="like", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> likeComment(@RequestParam Integer coNo, Principal principal) {

        logger.info("comment number: " + coNo);

        // 미로그인 유저일 때
        if (principal == null) {

            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);

        }

        CommentLikeVO likeVO = new CommentLikeVO();

        likeVO.setCoNo(coNo);
        likeVO.setUserEmail(principal.getName());

        CommentLikeVO commentLike;

        try {

            commentLike = answerCommentService.addLike(likeVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(commentLike, HttpStatus.OK);

    }

    @GetMapping(value="dislike", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> dislikeComment(@RequestParam Integer coNo, Principal principal) {

        logger.info("comment number: " + coNo);

        // 미로그인 유저일 때
        if (principal == null) {

            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);

        }

        CommentLikeVO likeVO = new CommentLikeVO();

        likeVO.setCoNo(coNo);
        likeVO.setUserEmail(principal.getName());

        CommentLikeVO commentLike;

        try {

            commentLike = answerCommentService.subtractLike(likeVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(commentLike, HttpStatus.OK);

    }

    @GetMapping(value="checkLiked", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> checkLiked(Integer coNo, Principal principal) {

        logger.info("comment number: " + coNo);

        // 미로그인 유저일 때
        if (principal == null) {

            CommentLikeVO like = new CommentLikeVO();
            return new ResponseEntity<>(like, HttpStatus.OK);

        }

        CommentVO commentVO = new CommentVO();

        commentVO.setCoNo(coNo);

        commentVO.setUserEmail(principal.getName());

        CommentLikeVO like;

        try {

            like = answerCommentService.checkLiked(commentVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        if (like == null) {
            like = new CommentLikeVO();
            like.setLikeType("None");
        }

        return new ResponseEntity<>(like, HttpStatus.OK);

    }

    @GetMapping(value="checkLast", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> checkLast(CommentVO commentVO) {

        logger.info("answer number: " + commentVO.getAnsNo());
        logger.info("last comment number from client side: " + commentVO.getCoNo());

        Integer lastCoNo;

        try {

            lastCoNo = answerCommentService.selectLastCoNo(commentVO.getAnsNo());
            logger.info("last comment number from server side: " + lastCoNo);

            if (Objects.equals(commentVO.getCoNo(), lastCoNo)) {

                return new ResponseEntity<>(true, HttpStatus.OK);

            }

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(false, HttpStatus.OK);

    }

}
