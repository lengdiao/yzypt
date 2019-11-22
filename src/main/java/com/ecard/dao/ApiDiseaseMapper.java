package com.ecard.dao;

import com.ecard.entity.ApiDisease;

import java.util.List;

public interface ApiDiseaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(ApiDisease record);

    int insertSelective(ApiDisease record);

    ApiDisease selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApiDisease record);

    int updateByPrimaryKey(ApiDisease record);

    List<ApiDisease> selectAll();
}