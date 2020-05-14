package com.ecard.dao;

import com.ecard.entity.DoctorWallet;

public interface DoctorWalletMapper {
    int deleteByPrimaryKey(Long walletId);

    int insert(DoctorWallet record);

    int insertSelective(DoctorWallet record);

    DoctorWallet selectByPrimaryKey(Long walletId);

    int updateByPrimaryKeySelective(DoctorWallet record);

    int updateByPrimaryKey(DoctorWallet record);

    DoctorWallet selectByDrNo(Long drNo);
}