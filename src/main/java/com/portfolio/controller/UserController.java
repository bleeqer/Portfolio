package com.portfolio.controller;

import com.portfolio.commons.util.UploadFileUtils;
import com.portfolio.domain.ImageVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.service.QuestionImageService;
import com.portfolio.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
public class UserController {

    @Autowired
    QuestionService questionService;

    @Autowired
    QuestionImageService imageService;

    @PostMapping("create/")
    public String createPost(QuestionVO questionVO) {

        // 게시글 저장
        questionService.create(questionVO);

//        return "board";
        return "index";

    }

    @RequestMapping("/{postNo}")
    public String viewPost(@PathVariable int postNo, Model model) {
        QuestionVO post = questionService.read(postNo);
        List<ImageVO> files = imageService.readAll(postNo);

        for (ImageVO file : files) {
            System.out.println(file.getUploadPath());
        }

        model.addAttribute("post", post);
        model.addAttribute("files", files);

        return "viewPost";
    }

    @ResponseBody
    @GetMapping(value="edit/{postNo}") //, produces="application/json"
    public QuestionVO editPost(@PathVariable int postNo) {

        return questionService.read(postNo);

    }

    @PostMapping("edit/")
    public String editPost(HttpServletRequest request, QuestionVO vo) {

        List<ImageVO> imgVOList = imageService.readAll(vo.getQuesNo());


        for (ImageVO imgVO : imgVOList) {

            System.out.println("기존 업로드 파일 경로:" + imgVO.getUploadPath());

        }

        UploadFileUtils.deleteFile(request, imgVOList);

        questionService.update(vo);

        return "";
    }

    @RequestMapping("delete/{postNo}")
    public String deletePost(HttpServletRequest request, @PathVariable int postNo) {

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
