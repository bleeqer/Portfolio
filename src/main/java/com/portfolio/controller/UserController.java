package com.portfolio.controller;

import com.portfolio.domain.UserDetailsVO;
import com.portfolio.domain.UserVO;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @GetMapping("register")
//    public String loginUser() {
//
//        return "userForm";
//    }

    @PostMapping("register")
    public String register(UserVO userVO) throws Exception {

        String hashedPW = passwordEncoder.encode(userVO.getUserPW());

        userVO.setUserPW(hashedPW);

        userService.create(userVO);

        return "redirect:/";
    }

    @GetMapping("login")
    public String userLogin() {
        System.out.println("reached");
        return "login";
    }

//    @PostMapping("login")
//    @ResponseBody
//    public String userLogin(UserDetailsVO user) {
//
//        return "ㅎㅎ";
//    }

//    @PostMapping("login")
//    public String userLoginPost() {
//
//        return "/";
//    }

}
