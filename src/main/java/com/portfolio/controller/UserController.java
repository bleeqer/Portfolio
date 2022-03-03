package com.portfolio.controller;

import com.portfolio.domain.UserVO;
import com.portfolio.service.QuestionImageService;
import com.portfolio.service.QuestionService;
import com.portfolio.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("register")
    public String loginUser() {

        return "register";

    }

    @PostMapping("register")
    public String register(UserVO userVO) throws Exception {

        String hashedPW = BCrypt.hashpw(userVO.getPassword(), BCrypt.gensalt());

        userVO.setPassword(hashedPW);

        userService.create(userVO);

        return "redirect:/user/register";
    }

//    @PostMapping("login")
//    public ResponseEntity<String> userLogin(UserVO userVO) {
//
//    }

//    @PostMapping("info")
//    public ResponseEntity<UserVO> userInfo(String userId) {
//
//        UserVO user = userService.read(userId);
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

}
