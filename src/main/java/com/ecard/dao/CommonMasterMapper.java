package com.ecard.dao;

import com.ecard.entity.CommonMaster;
import com.ecard.entity.CommonMasterKey;

import java.util.List;

public interface CommonMasterMapper {
    int deleteByPrimaryKey(CommonMasterKey key);

    int insert(CommonMaster record);

    int insertSelective(CommonMaster record);

    CommonMaster selectByPrimaryKey(CommonMasterKey key);

    int updateByPrimaryKeySelective(CommonMaster record);

    int updateByPrimaryKey(CommonMaster record);

    List<CommonMaster> selectByNo(int i);
}