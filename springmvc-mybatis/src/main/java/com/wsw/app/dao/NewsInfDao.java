package com.wsw.app.dao;

import com.wsw.app.domain.NewsInf;

public interface NewsInfDao {
    int deleteByPrimaryKey(Integer newsId);

    int insert(NewsInf record);

    int insertSelective(NewsInf record);

    NewsInf selectByPrimaryKey(Integer newsId);

    int updateByPrimaryKeySelective(NewsInf record);

    int updateByPrimaryKey(NewsInf record);
}