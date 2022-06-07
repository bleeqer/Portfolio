package com.portfolio.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchVO {

    String keyword;
    String target;
    Long categoryCode;
    Long quesNo;

}
