package com.portfolio.mapper;

import com.portfolio.domain.QuestionCategoryVO;
import com.portfolio.domain.QuestionVO;
import com.portfolio.service.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml", "file:web/WEB-INF/spring-security.xml"})
public class AnswerReplyMapperTest {

    @Autowired
    AnswerReplyMapper answerReplyMapper;

    @Autowired
    QuestionCategoryMapper questionCategoryMapper;

    @Autowired
    QuestionService questionService;

//    @Test
//    public void test() {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("replyNo", 25);
//
//        answerReplyMapper.delete(map);
//    }

//    @Test
//    public void test() {
//
//        List<QuestionCategoryVO> catList = questionCategoryMapper.selectAll();
//
//        String indentation = "";
//
//        for (QuestionCategoryVO cat : catList) {
//
//
//            for (int i=0; i<cat.getLevel(); i++) {
//                indentation = indentation + "  ";
//            }
//            System.out.println(indentation + cat.getCategoryName());
//
//            indentation = "";
//        }
//    }

    @Test
    public void selectByTopic() {

        HashMap<String, Object> map = new HashMap<>();

        map.put("topic", "JAVA");
        map.put("quesNo", 180);

        List<QuestionVO> categorizedQuestions = questionService.getMoreByTopic(map);

        for (QuestionVO question : categorizedQuestions) {

            System.out.println(question.getQuesNo());

        }

    }

    @Test
    public void selectQuestion () {

        QuestionVO ques = questionService.read(157);

//        System.out.println(ques.getCatPath());

    }
}
