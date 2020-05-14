package com.ecard.dao;

import com.ecard.entity.MallAddress;
import com.ecard.entity.ReceiptAddress;

public interface MallAddressMapper {
    int deleteByPrimaryKey(Long addId);

    int insert(MallAddress record);

    int insertSelective(MallAddress record);

    MallAddress selectByPrimaryKey(Long addId);

    int updateByPrimaryKeySelective(MallAddress record);

    int updateByPrimaryKey(MallAddress record);

    ReceiptAddress selectByPrimaryKey1(Long valueOf);
}