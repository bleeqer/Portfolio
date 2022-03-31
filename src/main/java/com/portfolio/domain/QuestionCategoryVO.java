package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionCategoryVO {

    private int categoryCode;
    private String categoryName;
    private int parentCategoryCode;
    private int level;
}
