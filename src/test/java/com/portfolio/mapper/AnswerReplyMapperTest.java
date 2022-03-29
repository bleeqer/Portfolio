package com.portfolio.mapper;

import com.portfolio.domain.AnswerReplyVO;
import com.portfolio.service.AnswerReplyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml", "file:web/WEB-INF/spring-security.xml"})
public class AnswerReplyMapperTest {

    @Autowired
    AnswerReplyMapper answerReplyMapper;

//    @Test
//    public void test() {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("replyNo", 25);
//
//        answerReplyMapper.delete(map);
//    }

    @Test
    public void selectTest() {
        List<AnswerReplyVO> answers = answerReplyMapper.selectList(25);

        for (AnswerReplyVO answer : answers) {
            System.out.println(answer.getReplyNo());
            System.out.println(answer.getContent());
        }
    }
}
