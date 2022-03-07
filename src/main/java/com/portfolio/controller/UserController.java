package com.portfolio.controller;

import com.portfolio.domain.UserVO;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

//        String hashedPW = BCrypt.hashpw(userVO.getUserPW(), BCrypt.gensalt());
        String hashedPW = passwordEncoder.encode(userVO.getUserPW());

        userVO.setUserPW(hashedPW);

        userService.create(userVO);

        return "redirect:/";
    }

    @PostMapping("login")
    public String userLogin(UserVO userVO) {

        System.out.println(userVO.getUserId());
        System.out.println(userVO.getUserPW());

        return "index";
    }

//    @PostMapping("info")
//    public ResponseEntity<UserVO> userInfo(String userId) {
//
//        UserVO user = userService.read(userId);
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }

}
