package com.ecard.dao;

import com.ecard.entity.PtInfo;
import com.ecard.pojo.queryResult.PtInfoQr;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PtInfoMapper {
    int deleteByPrimaryKey(Long ptNo);

    int insert(PtInfo record);

    int insertSelective(PtInfo record);

    PtInfo selectByPrimaryKey(Long ptNo);

    int updateByPrimaryKeySelective(PtInfo record);

    int updateByPrimaryKey(PtInfo record);

    PtInfoQr selectByPtNo(Long ptNo);

    List<PtInfoQr> selectPtInfo(
            @Param("name") String name,
            @Param("idNo") String idNo);

    PtInfo selectByPhone(String phone);
}