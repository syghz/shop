package com.netease.course.dao;

import java.util.List;

import com.netease.course.model.Trx;

public interface TrxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Trx record);

    int insertSelective(Trx record);

    Trx selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Trx record);

    int updateByPrimaryKey(Trx record);
    
    Trx getTrxByContentId(int contentId);
    
    List<Trx> getAllTrx();
}