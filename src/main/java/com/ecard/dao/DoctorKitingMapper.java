package com.ecard.dao;

import com.ecard.entity.DoctorKiting;

import java.math.BigDecimal;
import java.util.List;

public interface DoctorKitingMapper {
    int deleteByPrimaryKey(Long kitingId);

    int insert(DoctorKiting record);

    int insertSelective(DoctorKiting record);

    DoctorKiting selectByPrimaryKey(Long kitingId);

    int updateByPrimaryKeySelective(DoctorKiting record);

    int updateByPrimaryKey(DoctorKiting record);

    BigDecimal selectAmountByDrNo(Long drNo);
}