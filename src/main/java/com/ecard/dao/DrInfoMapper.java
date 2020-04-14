package com.ecard.dao;

import com.ecard.entity.DrInfo;
import com.ecard.pojo.queryResult.DrInfoQr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DrInfoMapper {
    int deleteByPrimaryKey(Long drNo);

    int insert(DrInfo record);

    int insertSelective(DrInfo record);

    DrInfo selectByPrimaryKey(Long drNo);

    int updateByPrimaryKeySelective(DrInfo record);

    int updateByPrimaryKey(DrInfo record);

    List<DrInfoQr> selectByNameChiefTitleFlag(
            @Param("drName") String drName,
            @Param("chiefNo") String chiefNo,
            @Param("title") String title,
            @Param("disableFlag") Integer disableFlag);

    DrInfoQr selectByCloudPassNo(Long cloudPassNo);

    DrInfoQr selectByDrNo(Long drNo);

    List<DrInfoQr> selectByPtOpenId(String openId);

    List<DrInfoQr> selectDepartment(
            @Param("deptName") String deptName,
            @Param("drName") String drName, String city);
}