package com.portfolio.service;

import com.portfolio.domain.CommentVO;
import com.portfolio.domain.CommentLikeVO;
import com.portfolio.mapper.AnswerCommentMapper;
import com.portfolio.mapper.AnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class AnswerCommentServiceImpl implements AnswerCommentService {

    @Autowired
    AnswerCommentMapper answerCommentMapper;

    @Autowired
    AnswerMapper answerMapper;

    @Transactional
    @Override
    public int insert(CommentVO commentVO) throws SQLException {

        try {

            answerCommentMapper.insert(commentVO);

        } catch (Exception e) {

            throw new SQLException("댓글 등록에 실패했습니다.");

        }

        return 0;
    }

    @Override
    public CommentVO select(Integer coNo) throws SQLException {

        CommentVO comment;

        try {

            comment = answerCommentMapper.select(coNo);

        } catch (Exception e) {

            throw new SQLException("댓글 조회에 실패했습니다.");

        }


        return comment;
    }

    @Override
    public List<CommentVO> selectList(CommentVO commentVO) throws SQLException {

        List<CommentVO> comments;

        try {

            comments = answerCommentMapper.selectList(commentVO);

        } catch (Exception e) {

            throw new SQLException("댓글 조회에 실패했습니다.");

        }

        return comments;

    }

    @Override
    public Integer selectLastCoNo(Integer ansNo) throws SQLException {

        Integer lastCoNo;

        try {

            lastCoNo = answerCommentMapper.selectLastCoNo(ansNo);


        } catch (Exception e) {

            throw new SQLException("댓글을 찾을 수 없습니다.");

        }
        return lastCoNo;
    }


    @Override
    public void update(CommentVO commentVO) throws SQLException {

        try {

            answerCommentMapper.update(commentVO);

        } catch (Exception e) {

            throw new SQLException("댓글 업데이트에 실패했습니다.");

        }

    }

    @Transactional
    @Override
    public void delete(CommentVO commentVO) throws SQLException {

        try {

            answerCommentMapper.delete(commentVO.getCoNo());

        } catch (Exception e) {

            throw new SQLException("댓글 삭제에 실패했습니다.");

        }


    }
    public CommentLikeVO addLike(CommentLikeVO likeVO) throws SQLException {

        CommentLikeVO resultLike;

        try {
            CommentLikeVO like = answerCommentMapper.findLike(likeVO);

            // likeType 구분 없이 findLike 후 likeType 셋팅
            likeVO.setLikeType("UP");

            if (like == null) {

                answerCommentMapper.addLike(likeVO);

            } else if (like.getLikeType().equals("UP")) {

                // 이미 좋아요 했다면 좋아요 취소
                answerCommentMapper.deleteLike(likeVO);


            } else {

                // 싫어요 -> 좋아요 업데이트
                answerCommentMapper.updateLike(likeVO);

            }

            resultLike = answerCommentMapper.countLike(likeVO.getCoNo());

        } catch (Exception e) {
e.getStackTrace();
      System.out.println(e.getMessage());
            throw new SQLException("좋아요에 실패했습니다.");

        }

        return resultLike;
    }

    @Override
    public CommentLikeVO subtractLike(CommentLikeVO likeVO) throws SQLException {

        CommentLikeVO resultLike;

        try {
            CommentLikeVO like = answerCommentMapper.findLike(likeVO);

            // likeType 구분 없이 findLike 후 likeType 셋팅
            likeVO.setLikeType("DOWN");

            if (like == null) {

                answerCommentMapper.addLike(likeVO);

            } else if (like.getLikeType().equals("DOWN")) {

                // 이미 싫어요 했다면 싫어요 취소
                answerCommentMapper.deleteLike(likeVO);

            } else {

                // 좋아요 -> 싫어요 업데이트
                answerCommentMapper.updateLike(likeVO);

            }

            resultLike = answerCommentMapper.countLike(likeVO.getCoNo());

        } catch (Exception e) {

            throw new SQLException("싫어요에 실패했습니다.");

        }

        return resultLike;

    }

    @Override
    public CommentLikeVO checkLiked(CommentVO commentVO) throws SQLException {

        CommentLikeVO res;

        try {

            res = answerCommentMapper.checkLiked(commentVO);

        } catch (Exception e) {
            e.getStackTrace();
            throw new SQLException("댓글 좋아요 여부 조회에 실패했습니다.");

        }

        return res;
    }


}
