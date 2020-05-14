package com.ecard.dao;

import com.ecard.entity.ChiCount;
import com.ecard.pojo.queryResult.PcCountQr;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ChiCountMapper {
    int deleteByPrimaryKey(Long coutId);

    int insert(ChiCount record);

    int insertSelective(ChiCount record);

    ChiCount selectByPrimaryKey(Long coutId);

    int updateByPrimaryKeySelective(ChiCount record);

    int updateByPrimaryKey(ChiCount record);

    ChiCount selectByDrNoDrugNoDate(
            @Param("drNo") Long drNo,
            @Param("date") Date date);

    List<PcCountQr> selectByTypeDrNameDate(
            @Param("drName") String drName,
            @Param("drugNo") Long drugNo,
            @Param("disNo1") Long disNo1,
            @Param("disNo2") Long disNo2,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("drPhone") String drPhone,
            @Param("drNo") Long drNo);

    List<PcCountQr> selectByTypeDrNameDate1(
            @Param("drName") String drName,
            @Param("drugNo") Long drugNo,
            @Param("disNo1") Long disNo1,
            @Param("disNo2") Long disNo2,
            @Param("date") String date,
            @Param("drPhone") String drPhone,
            @Param("drNo") Long drNo);
}