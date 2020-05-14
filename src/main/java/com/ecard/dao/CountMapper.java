package com.ecard.dao;

import com.ecard.entity.Count;
import com.ecard.pojo.queryResult.PcCountQr;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CountMapper {
    int deleteByPrimaryKey(Long countId);

    int insert(Count record);

    int insertSelective(Count record);

    Count selectByPrimaryKey(Long countId);

    int updateByPrimaryKeySelective(Count record);

    int updateByPrimaryKey(Count record);

    Count selectByDrNoDrugNoDate(
            @Param("drNo") Long drNo,
            @Param("drugNo")Long drugNo,
            @Param("date")Date date);

    List<PcCountQr> count1(
            @Param("drName") String drName,
            @Param("drugNo") Long drugNo,
            @Param("drPhone") String drPhone,
            @Param("disNo1") Long disNo1,
            @Param("disNo2") Long disNo2,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("drNo") Long drNo);

    List<PcCountQr> count2(
            @Param("drName") String drName,
            @Param("drugNo") Long drugNo,
            @Param("drPhone") String drPhone,
            @Param("disNo1") Long disNo1,
            @Param("disNo2") Long disNo2,
            @Param("startDate") String startDate,
            @Param("endDate") String endDate,
            @Param("drNo") Long drNo);
}