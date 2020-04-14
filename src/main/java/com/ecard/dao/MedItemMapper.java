package com.ecard.dao;

import com.ecard.entity.MedItem;
import com.ecard.pojo.queryResult.MedItemQr;

import java.util.List;

public interface MedItemMapper {
    int deleteByPrimaryKey(Long medItemNo);

    int insert(MedItem record);

    int insertSelective(MedItem record);

    MedItem selectByPrimaryKey(Long medItemNo);

    int updateByPrimaryKeySelective(MedItem record);

    int updateByPrimaryKey(MedItem record);

    List<MedItemQr> selectByMedOrderNo(Long orderNo);

    List<MedItem> selectByMallNo(Long mallNo);
}