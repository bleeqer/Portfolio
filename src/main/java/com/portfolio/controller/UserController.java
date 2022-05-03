package com.portfolio.controller;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.UserVO;
import com.portfolio.service.AnswerService;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;

    @GetMapping("login")
    public String userLogin() {

        return "login";
    }

    @GetMapping("profile/{userNo}")
    public String userProfile(@PathVariable int userNo, Model model) {

        // 유저 정보 가져오기
        UserVO user = userService.select(userNo);

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("profile/{userNo}/answers")
    public String userAnswer(@PathVariable int userNo, Model model) {

        // 유저 정보 가져오기
        model.addAttribute("user", userService.select(userNo));

        // 유저가 작성한 답변-질문 페어 3개 가져오기
        model.addAttribute("answerPairs", answerService.selectAnsweredPairByUser(userNo));

        return "profile";
    }

}
