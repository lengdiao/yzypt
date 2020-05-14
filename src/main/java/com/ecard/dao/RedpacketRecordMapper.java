package com.ecard.dao;

import com.ecard.entity.RedpacketRecord;

import java.util.List;

public interface RedpacketRecordMapper {
    int deleteByPrimaryKey(Long packetId);

    int insert(RedpacketRecord record);

    int insertSelective(RedpacketRecord record);

    RedpacketRecord selectByPrimaryKey(Long packetId);

    int updateByPrimaryKeySelective(RedpacketRecord record);

    int updateByPrimaryKey(RedpacketRecord record);

    List<RedpacketRecord> selectByPtNoAndDrugNo(Long ptNo, Long drugNo);

    List<RedpacketRecord> selectByDisableFlag();
}