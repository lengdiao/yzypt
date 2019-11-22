package com.ecard.dao;

import com.ecard.entity.MedOrder;

public interface MedOrderMapper {
    int deleteByPrimaryKey(Long orderNo);

    int insert(MedOrder record);

    int insertSelective(MedOrder record);

    MedOrder selectByPrimaryKey(Long orderNo);

    int updateByPrimaryKeySelective(MedOrder record);

    int updateByPrimaryKey(MedOrder record);

    MedOrder selectByMedRecordNo(Long templateId);

    Long selectId();
}