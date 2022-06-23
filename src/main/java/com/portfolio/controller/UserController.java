package com.portfolio.controller;

import com.portfolio.commons.util.UploadFileUtils;
import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.ImageVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.UserVO;
import com.portfolio.service.AnswerService;
import com.portfolio.service.QuestionService;
import com.portfolio.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ReactiveAdapterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.*;


@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionService questionService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("profile/{email}")
    public String userProfile(@PathVariable String email, Model model) {

        // 유저 정보 가져오기
        UserVO user = userService.select(email);

        QuestionVO questionVO = new QuestionVO();
        AnswerVO answerVO = new AnswerVO();

        questionVO.setUserEmail(email);
        answerVO.setUserEmail(email);

        Map<String, Object> meta = new HashMap<>();

        model.addAttribute("meta", meta);

        // 전달 객체 타입과 총 갯수
        meta.put("type", "answers");
        meta.put("total", answerService.countAnswers(answerVO));

        // 유저가 작성한 답변-질문 페어 가져오기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("profile/{email}/answers")
    public String userAnswers(@PathVariable String email, Model model) {

        // 유저 정보 가져오기
        UserVO userVO = userService.select(email);


        model.addAttribute("user", userVO);

        QuestionVO questionVO = new QuestionVO();
        AnswerVO answerVO = new AnswerVO();

        questionVO.setUserEmail(email);
        answerVO.setUserEmail(email);

        Map<String, Object> meta = new HashMap<>();

        meta.put("type", "answers");
        meta.put("total", answerService.countAnswers(answerVO));

        model.addAttribute("meta", meta);

        // 유저가 작성한 답변-질문 페어 가져오기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        return "profile";
    }

    @GetMapping("profile/{email}/questions")
    public String userQuestions(@PathVariable String email, Model model) {

        // 유저 정보 가져오기
        model.addAttribute("user", userService.select(email));

        QuestionVO questionVO = new QuestionVO();
        questionVO.setUserEmail(email);

        Map<String, Object> meta = new HashMap<>();

        meta.put("type", "questions");
        meta.put("total", questionService.countQuestions(questionVO));

        model.addAttribute("meta", meta);

        // 유저가 작성한 질문 리스트 가져오기
        model.addAttribute("questions", questionService.selectList(questionVO));

        return "profile";
    }

    @GetMapping("get")
    @ResponseBody
    public ResponseEntity<UserVO> getUser(Principal principal) {

        UserVO userVO;

        try {
            userVO = userService.select(principal.getName());

        } catch(Exception e) {
            e.getStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<Void> createUser(UserVO userVO, MultipartHttpServletRequest multiRequest) throws IOException {

        // 프로필 이미지 업로드 후 업로드한 이미지 경로 담기
        List<String> photoPath = UploadFileUtils.uploadFile(multiRequest);
        
        // 해싱한 비밀번호로 교체
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));

        userVO.setAuthority("ROLE_USER");
        
        // 업로드한 이미지 경로 셋팅
        userVO.setPhoto(photoPath.get(0));

        try {
            userService.create(userVO);
        }

        catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("update")
    @ResponseBody
    public ResponseEntity<String> editUser(UserVO userVO, MultipartHttpServletRequest multiRequest, Principal principal) throws IOException {

        String message = "";

        // 유저 불일치 시
        if (!principal.getName().equals(userVO.getEmail())) {
            message = "사용자가 일치하지 않습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        UserVO prevUser = userService.select(userVO.getEmail());

        // 새로운 프로필 사진 업로드 후 경로 담기
        List<String> photoPath = UploadFileUtils.uploadFile(multiRequest);


        // 프로필 이미지 변경했을 경우
        if (!photoPath.isEmpty()) {

            // 기존 프로필 이미지 파일 삭제
            ImageVO imageVO = new ImageVO();
            imageVO.setImagePath(prevUser.getPhoto());
            List<ImageVO> imgList = new ArrayList<>();
            imgList.add(imageVO);
            UploadFileUtils.deleteFile(multiRequest, imgList);
            userVO.setPhoto(photoPath.get(0));

        } else {

            userVO.setPhoto(prevUser.getPhoto());

        }

        // 해싱한 비밀번호로 교체
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));

        try {

            userService.update(userVO);
        }

        catch(Exception e) {
            message = "사용자 정보 업데이트에 실패했습니다.";
            new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
