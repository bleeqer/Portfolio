package com.portfolio.persistence;

import com.portfolio.domain.BoardVO;
import com.portfolio.mapper.BoardMapper;
import com.portfolio.service.BoardService;
import com.portfolio.service.BoardServiceImpl;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/applicationContext.xml"})
public class MyBatisTest {

    static private final Logger logger = LogManager.getLogger(MyBatisTest.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Autowired
    BoardMapper boardMapper;

    @Autowired
    BoardService boardService;

    @Test
    public void testFactory() {
        System.out.println(sqlSessionFactory);
    }

    @Test
    public void testBoardMapper() {
//        BoardVO selectedVO = boardMapper.select(1);
//        createdVo.setTitle("인서트 테스트 제목");
//        createdVo.setContent("인서트 테스트 컨텐트");
//        createdVo.setWriter("작성");
//        boardMapper.insert(createdVo);
//        boardMapper.delete(2);
//        selectedVO.setContent("he needs a queen!");
//        boardMapper.update(selectedVO);
//        List<BoardVO> vo = boardMapper.selectAll();
//        boardMapper.addViewCnt(1);
//        List<BoardVO> vo = boardMapper.selectAll();
    }

    @Test
    public void testBoardService() {
        BoardVO createdVo = new BoardVO();
        createdVo.setTitle("인서트 테스트 제목");
        createdVo.setContent("인서트 테스트 컨텐트");
        createdVo.setWriter("작성");

        boardService.readAll();
        boardService.read(1);
        BoardVO selectedVO = boardMapper.select(1);
        selectedVO.setContent("he needs a queen you know!");
        boardService.create(createdVo);
        boardService.update(selectedVO);
        boardService.delete(3);
        boardService.readAll();

    }




}
