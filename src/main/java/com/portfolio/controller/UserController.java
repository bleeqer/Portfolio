package com.portfolio.controller;

import com.portfolio.commons.util.UploadFileUtils;
import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.ImageVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.CustomUserDetailsVO;
import com.portfolio.service.AnswerService;
import com.portfolio.service.CustomUserDetailsServiceImpl;
import com.portfolio.service.QuestionService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.security.Principal;
import java.sql.SQLException;
import java.util.*;


@Controller
@RequestMapping("user")
public class UserController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    CustomUserDetailsServiceImpl userService;
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
    public String userProfile(@PathVariable String email, Model model) throws SQLException, UsernameNotFoundException, IllegalArgumentException {

        logger.info("getting user profile: " + email);

        CustomUserDetailsVO user = userService.select(email);

        QuestionVO questionVO = new QuestionVO();
        AnswerVO answerVO = new AnswerVO();

        questionVO.setUserEmail(email);
        answerVO.setUserEmail(email);

        // 전달 객체 타입과 객체 타입의 갯수 (프로필 화면의 회원이 작성한 답변글 갯수 표시)
        Map<String, Object> meta = new HashMap<>();

        meta.put("type", "answers");

        int answerCount = answerService.countAnswers(answerVO);

        meta.put("total", answerCount);

        // 유저가 작성한 답변-질문 페어 가져오기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));


        model.addAttribute("meta", meta);

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("profile/{email}/answers")
    public String userAnswers(@PathVariable String email, Model model) throws SQLException, IllegalArgumentException {

        logger.info("getting user profile: " + email);

        CustomUserDetailsVO user = userService.select(email);

        model.addAttribute("user", user);

        QuestionVO questionVO = new QuestionVO();
        AnswerVO answerVO = new AnswerVO();

        questionVO.setUserEmail(email);
        answerVO.setUserEmail(email);

        // 전달 객체 타입과 객체 타입의 갯수 (프로필 화면의 회원이 작성한 답변글 갯수 표시)
        Map<String, Object> meta = new HashMap<>();

        meta.put("type", "answers");

        meta.put("total", answerService.countAnswers(answerVO));

        // 유저가 작성한 답변-질문 페어 가져오기
        model.addAttribute("answerPairs", questionService.selectPairList(questionVO));

        model.addAttribute("meta", meta);

        model.addAttribute("user", user);

        return "profile";
    }

    @GetMapping("profile/{email}/questions")
    public String userQuestions(@PathVariable String email, Model model) throws SQLException, IllegalArgumentException {

        logger.info("getting user profile: " + email);

        CustomUserDetailsVO user = userService.select(email);

        QuestionVO questionVO = new QuestionVO();
        questionVO.setUserEmail(email);

        // 전달 객체 타입과 객체 타입의 갯수 (프로필 화면의 회원이 작성한 답변글 갯수 표시)
        Map<String, Object> meta = new HashMap<>();

        meta.put("type", "questions");

        meta.put("total", questionService.countQuestions(questionVO));

        // 유저가 작성한 답변-질문 페어 가져오기
        model.addAttribute("questions", questionService.selectList(questionVO));

        model.addAttribute("meta", meta);

        model.addAttribute("user", user);


        return "profile";
    }

    @GetMapping(value="get", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<Object> getUser(Principal principal) {

        if (principal == null) {
            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);
        }

        CustomUserDetailsVO userVO;

        try {

            logger.info("getting user profile: " + principal.getName());

            userVO = userService.select(principal.getName());

        } catch(Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);


        }

        return new ResponseEntity<>(userVO, HttpStatus.OK);
    }

    @PostMapping(value="create", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> createUser(CustomUserDetailsVO userVO, MultipartHttpServletRequest multiRequest) {

        logger.info("user email: " + userVO.getEmail());
        logger.info("user name: " + userVO.getName());
        logger.info("user photo: " + userVO.getPhoto());
        logger.info("user occupation: " + userVO.getOccupation());

        // 프로필 이미지 업로드 후 업로드한 이미지 경로 담기
        try {
            List<String> photoPath = UploadFileUtils.uploadFile(multiRequest);

            // 업로드한 이미지 경로 셋팅
            userVO.setPhoto(photoPath.get(0));

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        // 해싱한 비밀번호로 교체
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));

        userVO.setAuthority("ROLE_USER");

        try {

            userService.create(userVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>("회원가입에 성공했습니다.", HttpStatus.OK);
    }
    @PostMapping(value="update", produces="application/json; charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> editUser(CustomUserDetailsVO userVO, MultipartHttpServletRequest multiRequest, Principal principal) {

        logger.info("user email: " + userVO.getEmail());
        logger.info("user name: " + userVO.getName());
        logger.info("user photo: " + userVO.getPhoto());
        logger.info("user occupation: " + userVO.getOccupation());

        if (principal == null) {
            return new ResponseEntity<>("로그인이 필요한 기능입니다.", HttpStatus.BAD_REQUEST);
        }

        // 유저 불일치 시
        if (!principal.getName().equals(userVO.getEmail())) {
            return new ResponseEntity<>("사용자가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        CustomUserDetailsVO prevUser;

        List<String> photoPath;

        try {

            prevUser = userService.select(userVO.getEmail());

            // 프로필 사진 업로드 후 경로 담기 (프로필 사진 업데이트 안했을 경우 빈 리스트 반환)
            photoPath = UploadFileUtils.uploadFile(multiRequest);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }


        // 프로필 이미지 변경했을 경우
        if (!photoPath.isEmpty()) {

            // 기존 프로필 이미지 파일 삭제
            ImageVO imageVO = new ImageVO();
            imageVO.setImagePath(prevUser.getPhoto());
            List<ImageVO> imgList = new ArrayList<>();
            imgList.add(imageVO);

            try {

                // 프로필 사진 업로드 후 경로 담기 (프로필 사진 업데이트 안했을 경우 빈 리스트 반환)
                UploadFileUtils.deleteFile(multiRequest, imgList);

            } catch (Exception e) {

                logger.error(e.getMessage());
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

            }
            userVO.setPhoto(photoPath.get(0));

        } else {

            userVO.setPhoto(prevUser.getPhoto());

        }

        // 해싱한 비밀번호로 교체
        userVO.setPassword(passwordEncoder.encode(userVO.getPassword()));

        try {

            userService.update(userVO);

        } catch (Exception e) {

            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>("사용자 정보가 업데이트 되었습니다.", HttpStatus.OK);
    }
}
