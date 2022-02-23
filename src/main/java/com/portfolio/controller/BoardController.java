package com.portfolio.controller;

import com.portfolio.commons.util.UploadFileUtils;
import com.portfolio.domain.BoardVO;
import com.portfolio.domain.ImageVO;
import com.portfolio.service.BoardService;
import com.portfolio.service.ImageService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    ImageService fileService;

    @RequestMapping("")
    public String listPosts(Model model) {

        List<BoardVO> posts = boardService.readAll();

        model.addAttribute("posts", posts);

        return "board";
    }

//    @GetMapping("create")
//    public String createPost() {return "createPost";}


    @PostMapping("create/")
    public String createPost(BoardVO boardVO) {
        // @NotNull BoardVO boardVO

        // 게시글 저장
        boardService.create(boardVO);
        System.out.println("됩니까" + boardVO.getImageList());

        return "board";

    }

    @RequestMapping("/{bno}")
    public String viewPost(@PathVariable int bno, Model model) {
        BoardVO post = boardService.read(bno);
        List<ImageVO> files = fileService.readAll(bno);

        for (ImageVO file : files) {
            System.out.println(file.getUploadPath());
        }

        model.addAttribute("post", post);
        model.addAttribute("files", files);

        return "viewPost";
    }

    @ResponseBody
    @GetMapping(value="edit/{bno}") //, produces="application/json"
    public BoardVO editPost(@PathVariable int bno) {


//        model.addAttribute("post", post);
        return boardService.read(bno);
    }

    @PostMapping("edit/")
    public String editPost(BoardVO vo) {
        boardService.update(vo);

        return "";
    }

    @RequestMapping("delete/{bno}")
    public String deletePost(HttpServletRequest request, @PathVariable int bno) {

        boardService.delete(bno);

        // 삭제하려는 게시글의 모든 첨부파일(이미지) 불러오기
        List<ImageVO> atchList = fileService.readAll(bno);

        // 파일관련유틸에 절대경로를 얻기위한 HttpServletRequest 객체, 삭제할 파일 리스트 전달하여 파일삭제
        UploadFileUtils.deleteFile(request, atchList);

        // 첨부파일 리스트 순회하며 데이터베이스에서 삭제
        for (ImageVO atch : atchList) {
            fileService.delete(atch);
        }

        return "redirect:/board";
    }
}
