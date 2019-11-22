package com.ecard.dao;

import com.ecard.entity.DiseaseMaster;

import java.util.List;

public interface DiseaseMasterMapper {
    int deleteByPrimaryKey(Long DNo);

    int insert(DiseaseMaster record);

    int insertSelective(DiseaseMaster record);

    DiseaseMaster selectByPrimaryKey(Long DNo);

    int updateByPrimaryKeySelective(DiseaseMaster record);

    int updateByPrimaryKey(DiseaseMaster record);

    List<DiseaseMaster> selectByKeyword1(String keyword1);
}