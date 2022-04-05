package com.portfolio.controller;

import com.portfolio.domain.AnswerReplyVO;
import com.portfolio.service.AnswerReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("reply/answer")
public class AnswerReplyController {

    @Autowired
    AnswerReplyService answerReplyService;

//    @PostMapping("create")
//    @ResponseBody
//    public AnswerReplyVO createAnswerReply(@RequestBody AnswerReplyVO answerReply) {
//
//        int answerReplyNo = answerReplyService.create(answerReply);
//
//        return answerReplyService.readOne(answerReplyNo);
//    }

    @PostMapping("create")
    @ResponseBody
    public AnswerReplyVO createAnswerReply(@RequestBody AnswerReplyVO answerReply) {

        answerReply.setParentCommentNo(25);

        int parentCommentNo = answerReply.getParentCommentNo();

        System.out.println("parent Comment Number: " + parentCommentNo);

//        // 부모 댓글 미존재 시 전체 댓글의 순서 + 1
//        answerReply.setOrder(answerReplyService.selectMaxOrder());

        // 부모 댓글 존재 시
        if (parentCommentNo != 0) {

            AnswerReplyVO parentReply = answerReplyService.readOne(parentCommentNo);

        }

        int answerReplyNo = answerReplyService.create(answerReply);

        AnswerReplyVO answerReplyReturn =answerReplyService.readOne(answerReplyNo);

        System.out.println("parent comment number: " + answerReplyReturn.getParentCommentNo());

        return answerReplyService.readOne(answerReplyNo);
    }

    @PostMapping("more")
    @ResponseBody
    public List<AnswerReplyVO> getMoreAnswerReplies(@RequestBody AnswerReplyVO answerReply) {

        HashMap<String, Integer> map = new HashMap<>();
        map.put("ansNo", answerReply.getAnsNo());
        map.put("replyNo", answerReply.getReplyNo());

        List<AnswerReplyVO> answerReplyVOList = answerReplyService.readMoreList(map);

        for (AnswerReplyVO ans : answerReplyVOList) {
            System.out.println(ans.getReplyNo());
        }

        return answerReplyService.readMoreList(map);
    }

}
