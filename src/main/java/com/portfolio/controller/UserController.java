package com.portfolio.controller;

import com.portfolio.domain.QAPairVO;
import com.portfolio.domain.UserVO;
import com.portfolio.service.AnswerService;
import com.portfolio.service.QuestionService;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;
    @Autowired
    QuestionService questionService;

    @GetMapping("login")
    public String userLogin() {

        return "login";
    }

    @GetMapping("profile/{email}")
    public String userProfile(@PathVariable String email, Model model) {

        // 유저 정보 가져오기
        UserVO user = userService.select(email);

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("profile/{email}/answers")
    public String userAnswers(@PathVariable String email, Model model) {

        // 유저 정보 가져오기
        model.addAttribute("user", userService.select(email));

        // 유저가 작성한 답변-질문 페어 3개 가져오기
        model.addAttribute("answerPairs", answerService.selectAnsweredPairByUser(email));

        return "profile";
    }

    @GetMapping("profile/{email}/questions")
    public String userQuestions(@PathVariable String email, Model model) {

        // 유저 정보 가져오기
        model.addAttribute("user", userService.select(email));

        // 유저가 작성한 질문 리스트 가져오기
//        model.addAttribute("questions", questionService.selectList(userNo));

        return "profile";
    }

    @GetMapping("get")
    @ResponseBody
    public UserVO getUser(Principal principal) {

        return userService.select(principal.getName());
    }

}
