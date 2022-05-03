package com.portfolio.controller;

import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("login")
    public String userLogin() {

        return "login";
    }

    @GetMapping("profile/{userNo}")
    public String userProfile(@PathVariable int userNo) {
        System.out.println(userNo);

        return "profile";
    }


}
