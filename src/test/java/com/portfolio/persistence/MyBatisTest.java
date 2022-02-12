package com.portfolio.persistence;

import com.portfolio.domain.BoardVO;
import com.portfolio.mapper.BoardMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml"})
public class MyBatisTest {

    static private final Logger logger = LogManager.getLogger(MyBatisTest.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    BoardMapper boardMapper;

    @Test
    public void testFactory() {
        System.out.println(sqlSessionFactory);
    }

    @Test
    public void getBoard() {
        BoardVO vo = boardMapper.getBoard();
    }

}
