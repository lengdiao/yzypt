package com.ecard.dao;

import com.ecard.entity.ReceiptAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface ReceiptAddressMapper {
    int deleteByPrimaryKey(Long addId);

    int insert(ReceiptAddress record);

    int insertSelective(ReceiptAddress record);

    ReceiptAddress selectByPrimaryKey(Long addId);

    int updateByPrimaryKeySelective(ReceiptAddress record);

    int updateByPrimaryKey(ReceiptAddress record);

	List<ReceiptAddress> selectByPtNo(Long ptNo);

	void updateMarkZero(Long ptNo);

	ReceiptAddress selectReceiptAddress(ReceiptAddress receiptAddress);
}