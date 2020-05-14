package com.ecard.dao;

import com.ecard.entity.Postage;

import java.util.List;

public interface PostageMapper {
    int deleteByPrimaryKey(Long commonNo);

    int insert(Postage record);

    int insertSelective(Postage record);

    Postage selectByPrimaryKey(Long commonNo);

    int updateByPrimaryKeySelective(Postage record);

    int updateByPrimaryKey(Postage record);

    List<Postage> selectByNo(Long drugSetNo);
}