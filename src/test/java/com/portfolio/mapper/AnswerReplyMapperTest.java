package com.portfolio.mapper;

import com.portfolio.domain.AnswerReplyVO;
import com.portfolio.domain.AnswerVO;
import com.portfolio.domain.QuestionCategoryVO;
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

    @Autowired
    QuestionCategoryMapper questionCategoryMapper;

//    @Test
//    public void test() {
//        HashMap<String, Integer> map = new HashMap<>();
//        map.put("replyNo", 25);
//
//        answerReplyMapper.delete(map);
//    }

    @Test
    public void test() {

        List<QuestionCategoryVO> catList = questionCategoryMapper.selectAll();

        String indentation = "";

        for (QuestionCategoryVO cat : catList) {


            for (int i=0; i<cat.getLevel(); i++) {
                indentation = indentation + "  ";
            }
            System.out.println(indentation + cat.getCategoryName());

            indentation = "";
        }
    }
}
