package com.portfolio.service;

import com.portfolio.domain.*;
import com.portfolio.mapper.QuestionMapper;
import com.portfolio.mapper.QuestionImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionImageMapper questionImageMapper;

    @Transactional
    @Override
    public long create(QuestionVO questionVO) {

        // questionVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
//        if (questionVO.getImageList() != null) {
//            System.out.println("이미지 삽입");
//            for (String uploadPath : questionVO.getImageList()) {
//                ImageVO imgVO = new ImageVO();
//
//                imgVO.setPostNo(questionVO.getQuesNo());
//                imgVO.setUploadPath(uploadPath);
//
//                questionImageMapper.insert(imgVO);
//            }
//        }

        return questionMapper.insert(questionVO);

    }

    @Transactional
    @Override
    public QuestionVO select(long quesNo) {

        questionMapper.addViewCnt(quesNo);

        return questionMapper.select(quesNo);
    }

    @Override
    public void update(QuestionVO questionVO) {

        questionMapper.update(questionVO);

    }

    @Override
    public void delete(long postNo) {
        questionMapper.delete(postNo);
    }

    @Override
    public long selectLastQuesNo() {
        return questionMapper.selectLastQuesNo();
    }

    @Override
    public List<QuestionVO> selectList(QuestionVO questionVO) {

        return questionMapper.selectList(questionVO);
    }

    @Override
    public List<QAPairVO> selectPairList(QuestionVO questionVO) {

        return questionMapper.selectPairList(questionVO);
    }

    @Override
    public List<CategoryVO> selectCategories() {

        return questionMapper.selectCategories();
    }

    @Override
    public List<QuestionVO> searchQuestions(SearchVO searchVO) {
        return questionMapper.searchListByQuestions(searchVO);
    }

    @Override
    public List<QAPairVO> searchPairs(SearchVO searchVO) {
        return questionMapper.searchPairs(searchVO);
    }

}
