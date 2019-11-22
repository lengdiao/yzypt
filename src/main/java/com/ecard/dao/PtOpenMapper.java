package com.ecard.dao;

import com.ecard.entity.PtOpen;

import java.util.List;

public interface PtOpenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PtOpen record);

    int insertSelective(PtOpen record);

    PtOpen selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PtOpen record);

    int updateByPrimaryKey(PtOpen record);

    PtOpen findByPtOpenId(String openId);

    String getPhone(String openId);

    List<PtOpen> selectByPtNo(Long ptNo);

    List<PtOpen> selectBtOpenId(String openId);
}