package com.ecard.dao;

import com.ecard.entity.RedpacketRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RedpacketRuleMapper {
    int deleteByPrimaryKey(Long packetId);

    int insert(RedpacketRule record);

    int insertSelective(RedpacketRule record);

    RedpacketRule selectByPrimaryKey(Long packetId);

    int updateByPrimaryKeySelective(RedpacketRule record);

    int updateByPrimaryKey(RedpacketRule record);

    List<RedpacketRule> selectPacketByIdDrugNo(
            @Param("packetId") Long packetId,
            @Param("drugNo") Long drugNo);

    List<RedpacketRule> selectByDrugNo(Long drugNo);
}