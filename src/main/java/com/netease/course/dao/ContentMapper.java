package com.netease.course.dao;

import java.util.List;

import com.netease.course.model.Content;

public interface ContentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
    
    List<Content> getAllContent();
}