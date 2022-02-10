package com.portfolio.mapper;

import org.apache.ibatis.annotations.Select;

public interface BoardMapper {

    @Select("SELECT title FROM board WHERE bno=1")
    public String getTitle();
}
