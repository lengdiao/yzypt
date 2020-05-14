package com.ecard.dao;

import com.ecard.entity.PtOpen;
import com.ecard.entity.TemporaryPtOpen;

import java.util.List;

public interface TemporaryPtOpenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TemporaryPtOpen record);

    int insertSelective(TemporaryPtOpen record);

    TemporaryPtOpen selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TemporaryPtOpen record);

    int updateByPrimaryKey(TemporaryPtOpen record);

    List<TemporaryPtOpen> selectByOpenId(String openId);
}