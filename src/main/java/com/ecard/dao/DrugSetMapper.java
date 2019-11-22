package com.ecard.dao;

import com.ecard.entity.DrugSet;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugSetMapper {
    int deleteByPrimaryKey(Long drugSetNo);

    int insert(DrugSet record);

    int insertSelective(DrugSet record);

    DrugSet selectByPrimaryKey(Long drugSetNo);

    int updateByPrimaryKeySelective(DrugSet record);

    int updateByPrimaryKey(DrugSet record);

    List<DrugSet> selectByCaKeyDis(
            @Param("category") Integer category,
            @Param("keyword1") String keyword1,
            @Param("disableFlag") Integer disableFlag);
}