package com.portfolio.controller;

import com.portfolio.domain.AnswerReplyVO;
import com.portfolio.service.AnswerReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("reply/answer")
public class AnswerReplyController {

    @Autowired
    AnswerReplyService answerReplyService;

    @PostMapping("create")
    @ResponseBody
    public AnswerReplyVO createAnswerReply(@RequestBody AnswerReplyVO answerReply) {

        int answerReplyNo = answerReplyService.create(answerReply);

        return answerReplyService.readOne(answerReplyNo);
    }

    @PostMapping("more")
    @ResponseBody
    public List<AnswerReplyVO> getMoreAnswerReplies(@RequestBody AnswerReplyVO answerReply) {

        List<AnswerReplyVO> answerReplyVOList = answerReplyService.readMoreList(answerReply.getAnsNo());

        for (AnswerReplyVO ans : answerReplyVOList) {
            System.out.println(ans.getReplyNo());
        }
        return answerReplyService.readMoreList(answerReply.getAnsNo());
    }
}
