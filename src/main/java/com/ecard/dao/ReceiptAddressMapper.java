package com.ecard.dao;

import com.ecard.entity.ReceiptAddress;

public interface ReceiptAddressMapper {
    int deleteByPrimaryKey(Long addId);

    int insert(ReceiptAddress record);

    int insertSelective(ReceiptAddress record);

    ReceiptAddress selectByPrimaryKey(Long addId);

    int updateByPrimaryKeySelective(ReceiptAddress record);

    int updateByPrimaryKey(ReceiptAddress record);
}