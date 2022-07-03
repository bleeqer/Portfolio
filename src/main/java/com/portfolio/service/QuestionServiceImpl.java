package com.portfolio.service;

import com.portfolio.domain.*;
import com.portfolio.mapper.QuestionMapper;
import com.portfolio.mapper.QuestionImageMapper;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionImageMapper questionImageMapper;

    @Override
    public Integer create(QuestionVO questionVO) throws SQLException {
        
        Integer createdAnsNo;
        
        try {

            createdAnsNo = questionMapper.insert(questionVO);

        } catch (Exception e) {

            throw new SQLException("질문 생성에 실패했습니다.");

        }

        return createdAnsNo;

    }

    @Transactional
    @Override
    public QuestionVO select(Integer quesNo) throws SQLException {

        QuestionVO questionVO;

        try {

            questionMapper.addViewCnt(quesNo);
            questionVO = questionMapper.select(quesNo);

        } catch (Exception e) {

            throw new SQLException("질문 조회에 실패했습니다.");

        }


        return questionVO;
    }

    @Override
    public void update(QuestionVO questionVO) throws SQLException {

        try {

            questionMapper.update(questionVO);

        } catch (Exception e) {

            throw new SQLException("질문 업데이트에 실패했습니다.");

        }

    }

    @Override
    public void delete(Integer postNo) throws SQLException {

        try {

            questionMapper.delete(postNo);


        } catch (Exception e) {

            throw new SQLException("질문 삭제에 실패했습니다.");

        }
    }

    @Override
    public Integer selectLastQuesNo() throws SQLException {

        Integer lastQuesNo;

        try {

            lastQuesNo = questionMapper.selectLastQuesNo();


        } catch (Exception e) {

            throw new SQLException("질문을 찾을 수 없습니다.");

        }
        return lastQuesNo;
    }

    @Override
    public List<QuestionVO> selectList(QuestionVO questionVO) throws SQLException {

        List<QuestionVO> questionList;

        try {

            questionList = questionMapper.selectList(questionVO);


        } catch (Exception e) {

            throw new SQLException("질문 목록 조회에 실패했습니다.");

        }

        return questionList;
    }

    @Override
    public List<QAPairVO> selectPairList(QuestionVO questionVO) throws SQLException {

        List<QAPairVO> pairList;

        try {

            pairList = questionMapper.selectPairList(questionVO);


        } catch (Exception e) {

            throw new SQLException("질문/답변 목록 조회에 실패했습니다.");

        }

        return pairList;
    }

    @Override
    public List<CategoryVO> selectCategories() throws SQLException {

        List<CategoryVO> categoryList;

        try {

            categoryList = questionMapper.selectCategories();


        } catch (Exception e) {

            throw new SQLException("카테고리 조회에 실패했습니다.");

        }

        return categoryList;
    }

    @Override
    public List<QuestionVO> searchQuestions(SearchVO searchVO) throws SQLException {

        List<QuestionVO> questionList;

        try {

            questionList = questionMapper.searchList(searchVO);


        } catch (Exception e) {

            throw new SQLException("질문 검색에 실패했습니다.");

        }

        return questionList;
    }

    @Override
    public List<QAPairVO> searchPairs(SearchVO searchVO) throws SQLException {

        List<QAPairVO> pairList;

        try {

            pairList = questionMapper.searchPairs(searchVO);


        } catch (Exception e) {

            throw new SQLException("질문/답변 검색에 실패했습니다.");

        }

        return pairList;
    }

    @Override
    public Integer countQuestions(QuestionVO questionVO) throws SQLException {

        Integer questionCount;

        try {

            questionCount = questionMapper.countQuestions(questionVO);

        } catch (Exception e) {

            throw new SQLException("질문 갯수 카운트에 실패했습니다.");

        }
        return questionCount;
    }

}
