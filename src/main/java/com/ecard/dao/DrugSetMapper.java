package com.ecard.dao;

import com.ecard.entity.DrugSet;
import com.ecard.pojo.queryResult.TemplateDrugSetQr;
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
            @Param("departmentName") String departmentName,
            @Param("category") Integer category,
            @Param("keyword1") String keyword1,
            @Param("disableFlag") Integer disableFlag,
            @Param("version") Integer version,
            @Param("drugNo") Long drugNo);

    TemplateDrugSetQr findByDrugNo(Long drugNo);

    List<DrugSet> selectByCaKeyDis1(Long drugNo);
}