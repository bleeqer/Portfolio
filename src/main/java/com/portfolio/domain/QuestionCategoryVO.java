package com.portfolio.domain;

import com.portfolio.commons.util.TimeDiff;
import java.lang.String;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Date;

@Getter
@Setter
public class QuestionCategoryVO {

    private String space;
    private int categoryCode;
    private String categoryName;
    private int parentCategoryCode;
    private String categoryPhoto;

    public String getSpace() {
        return space;
    }
    public void setSpace(int level) {

        // 텍스트와 이미지를 담은 HTML element 자체를 들여쓰기하기 위해 쿼리에서 처리하지 않고 space라는 attribute를 따로 가짐
        this.space = String.join("", Collections.nCopies(level * 2, "&nbsp;"));

    }
}
