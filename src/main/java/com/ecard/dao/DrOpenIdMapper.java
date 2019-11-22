package com.ecard.dao;

import com.ecard.entity.DrOpenId;

public interface DrOpenIdMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DrOpenId record);

    int insertSelective(DrOpenId record);

    DrOpenId selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DrOpenId record);

    int updateByPrimaryKey(DrOpenId record);

    DrOpenId selectByOpenId(String openId);
}