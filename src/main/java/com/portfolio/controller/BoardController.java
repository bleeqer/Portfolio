package com.portfolio.controller;

import com.portfolio.commons.util.UploadFileUtils;
import com.portfolio.domain.BoardVO;
import com.portfolio.domain.FileVO;
import com.portfolio.service.BoardService;
import com.portfolio.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    FileService fileService;

    @RequestMapping("")
    public String listPosts(Model model) {

        List<BoardVO> posts = boardService.readAll();

        model.addAttribute("posts", posts);

        return "board";
    }

    @GetMapping("create")
    public String createPost() {return "createPost";}


    @PostMapping("create")
    public String createPost(MultipartHttpServletRequest mtfRequest, BoardVO boardVO) {

        // 게시글 저장
        boardService.create(boardVO);

        // 업로드 파일이 존재할 때
        if (mtfRequest != null) {

            // 파일(들)을 지정된 경로에 저장하고 데이터베이스에 기록될 경로(들)을 ArrayList로 반환
            List<String> fileDirs = UploadFileUtils.uploadFile(mtfRequest);

            // file 데이터를 담을 VO 생성
            FileVO fileVO = new FileVO();

            // fileDirs 리스트를 순회하며 vo에 데이터를 담기
            for (String fileDir : fileDirs) {

                // board의 insert 수행 후 자동생성된 bno가 담긴 boardVO의 멤버변수 bno 가져오기
                int bno = boardVO.getBno();

                // fileVO 객체에 데이터 채우기
                fileVO.setBno(bno);
                fileVO.setFile(fileDir);

                // 데이터베이스에 저장
                fileService.save(fileVO);
            }

        } else {
            System.out.println("첨부 파일이 없습니다.");
        }

        return "redirect:/board";
    }

    @RequestMapping("/{bno}")
    public String viewPost(@PathVariable int bno, Model model) {
        BoardVO post = boardService.read(bno);
        model.addAttribute("post", post);

        return "viewPost";
    }

    @GetMapping("edit/{bno}")
    public String editPost(@PathVariable int bno, Model model) {
        BoardVO post = boardService.read(bno);

        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("edit/{bno}")
    public String editPost(BoardVO vo) {
        boardService.update(vo);

        return "/board";
    }

    @RequestMapping("delete/{bno}")
    public String deletePost(HttpServletRequest request, @PathVariable int bno) {

        boardService.delete(bno);

        // 삭제하려는 게시글의 모든 첨부파일(이미지) 불러오기
        List<FileVO> atchList = fileService.readAll(bno);

        // 파일관련유틸에 절대경로를 얻기위한 HttpServletRequest 객체, 삭제할 파일 리스트 전달하여 파일삭제
        UploadFileUtils.deleteFile(request, atchList);

        //
        for (FileVO atch : atchList) {
            fileService.delete(atch);
        }

        // 첨부파일 삭제
//       fileService.delete(bno);

        return "redirect:/board";
    }
}
