package com.ecard.dao;

import com.ecard.entity.MedRecord;

public interface MedRecordMapper {
    int deleteByPrimaryKey(String medRecordNo);

    int insert(MedRecord record);

    int insertSelective(MedRecord record);

    MedRecord selectByPrimaryKey(String medRecordNo);

    int updateByPrimaryKeySelective(MedRecord record);

    int updateByPrimaryKey(MedRecord record);

    Long selectId();
}