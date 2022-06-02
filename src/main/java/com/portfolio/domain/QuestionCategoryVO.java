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
    private String categoryPhoto;

}
