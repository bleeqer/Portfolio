package com.portfolio.service;

import com.portfolio.domain.QuestionVO;
import com.portfolio.domain.ImageVO;
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
    QuestionImageMapper questionImageMapper;

    @Transactional
    @Override
    public void create(QuestionVO questionVO) {

        // questionVO 인서트 성공 시 postNo property에 자동생성된 postNo 세팅
        questionMapper.insert(questionVO);

        // ImageVO에 해당 포스트 이미지정보 세팅 후 인서트
        if (questionVO.getImageList() != null) {
            System.out.println("이미지 삽입");
            for (String uploadPath : questionVO.getImageList()) {
                ImageVO imgVO = new ImageVO();

                imgVO.setPostNo(questionVO.getQuesNo());
                imgVO.setUploadPath(uploadPath);

                questionImageMapper.insert(imgVO);
            }

        }

    }

    @Override
    public QuestionVO read(int postNo) {
        return questionMapper.select(postNo);
    }

    @Override
    public void update(QuestionVO questionVO) {

        questionMapper.update(questionVO);

    }

    @Override
    public void delete(int postNo) {
        questionMapper.delete(postNo);
    }

    @Override
    public List<QuestionVO> readAll() {

        return questionMapper.selectAll();

    }

    @Override
    public List<QuestionVO> readAllByTopic(String topic) {
        return questionMapper.selectAllByTopic(topic);
    }

    @Override
    public void addViewCnt(int postNo) {
        questionMapper.addViewCnt(postNo);
    }

    @Override
    public void addLikeCnt(int postNo) {
        questionMapper.addLikeCnt(postNo);
    }

    @Override
    public List<QuestionVO> getMore(int startQuesNo) {
        return questionMapper.selectMore(startQuesNo);
    }

}
