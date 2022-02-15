package com.portfolio.controller;

import com.portfolio.domain.BoardVO;
import com.portfolio.service.BoardService;
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

    @RequestMapping("")
    public String listPosts(Model model) {

        List<BoardVO> posts = boardService.readAll();

        model.addAttribute("posts", posts);

        return "board";
    }

    @GetMapping("create")
    public String createPost() {return "createPost";}

    @PostMapping("create")
    public String createPost(BoardVO vo) {

        System.out.println("title: " + vo.getTitle());
        System.out.println("content: " + vo.getContent());
        System.out.println("writer: " + vo.getWriter());
        boardService.create(vo);

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
    public String deletePost(@PathVariable int bno) {

        boardService.delete(bno);

        return "redirect:/board";
    }

}
