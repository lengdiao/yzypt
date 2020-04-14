package com.ecard.dao;

import com.ecard.entity.DrugStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrugStoreMapper {
    int deleteByPrimaryKey(Long drugStoreNo);

    int insert(DrugStore record);

    int insertSelective(DrugStore record);

    DrugStore selectByPrimaryKey(Long drugStoreNo);

    int updateByPrimaryKeySelective(DrugStore record);

    int updateByPrimaryKey(DrugStore record);

    DrugStore selectByCloudPassNo(Long cloudPassNo);

    List<DrugStore> findDrugStore(
            @Param("drugStoreName") String drugStoreName,
            @Param("disableFlag") Integer disableFlag,
            @Param("address") String address,
            @Param("type") Integer type);

}