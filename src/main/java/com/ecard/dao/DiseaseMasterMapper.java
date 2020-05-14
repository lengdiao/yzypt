package com.ecard.dao;

import com.ecard.entity.DiseaseMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DiseaseMasterMapper {
    int deleteByPrimaryKey(Long DNo);

    int insert(DiseaseMaster record);

    int insertSelective(DiseaseMaster record);

    DiseaseMaster selectByPrimaryKey(Long DNo);

    int updateByPrimaryKeySelective(DiseaseMaster record);

    int updateByPrimaryKey(DiseaseMaster record);

    List<DiseaseMaster> selectByKeyword1(
            @Param("keyword1") String keyword1,
            @Param("chtType") String chtType);

    List<DiseaseMaster> selectByIcdAndName(
            @Param("chtName") String chtName,
            @Param("icdCode") String icdCode);

    List<DiseaseMaster> selectByIcdAndNameAndNo(
            @Param("chtName") String chtName,
            @Param("icdCode") String icdCode,
            @Param("dNo") Long dNo);
}