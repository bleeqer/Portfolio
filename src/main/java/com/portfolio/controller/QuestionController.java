package com.portfolio.controller;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import com.portfolio.commons.util.UploadFileUtils;
import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.ImageVO;
import com.portfolio.service.AnswerService;
import com.portfolio.service.QuestionService;
import com.portfolio.service.QuestionImageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionImageService imageService;

    @PostMapping("create")
    @ResponseBody
    public QuestionVO createQuestion(QuestionVO questionVO) {

        // 게시글 저장
        questionService.create(questionVO);

        System.out.println(questionService.read(questionVO.getQuesNo()).getRegDate());

        return questionService.read(questionVO.getQuesNo());
    }

//    @RequestMapping("/{postNo}")
//    public String viewQuestion(@PathVariable int postNo, Model model) {
//
//        QuestionVO question = questionService.read(postNo);
//
//        List<AnswerVO> answers = answerService.readList(postNo);
//
//        model.addAttribute("question", question);
//        model.addAttribute("answers", answers);
////        model.addAttribute("files", files);
//
//        return "viewQuestion";
//    }

    @GetMapping(value="edit/{postNo}") //, produces="application/json"
    @ResponseBody
    public QuestionVO editQuestion(@PathVariable int postNo) {
        return questionService.read(postNo);

    }

    @PostMapping("edit/")
    @ResponseBody
    public QuestionVO editQuestion(HttpServletRequest request, QuestionVO question, Principal principal) {

        List<ImageVO> imgVOList = imageService.readAll(question.getQuesNo());

        for (ImageVO imgVO : imgVOList) {

            System.out.println("기존 업로드 파일 경로:" + imgVO.getUploadPath());

        }

        UploadFileUtils.deleteFile(request, imgVOList);

        questionService.update(question);

        return questionService.read(question.getQuesNo());
    }

    @RequestMapping("delete/{postNo}")
    public String deleteQuestion(HttpServletRequest request, @PathVariable int postNo) {

        questionService.delete(postNo);

        // 삭제하려는 게시글의 모든 첨부파일(이미지) 불러오기
        List<ImageVO> atchList = imageService.readAll(postNo);

        // 파일관련유틸에 절대경로를 얻기위한 HttpServletRequest 객체, 삭제할 파일 리스트 전달하여 파일삭제
        UploadFileUtils.deleteFile(request, atchList);

        // 첨부파일 리스트 순회하며 데이터베이스에서 삭제
        for (ImageVO atch : atchList) {
            imageService.delete(atch);
        }

        return "index";
    }


}
