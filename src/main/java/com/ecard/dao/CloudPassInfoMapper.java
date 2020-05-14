package com.ecard.dao;

import com.ecard.entity.CloudPassInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CloudPassInfoMapper {
    int deleteByPrimaryKey(Long cloudPassNo);

    int insert(CloudPassInfo record);

    int insertSelective(CloudPassInfo record);

    CloudPassInfo selectByPrimaryKey(Long cloudPassNo);

    int updateByPrimaryKeySelective(CloudPassInfo record);

    int updateByPrimaryKey(CloudPassInfo record);

    CloudPassInfo selectByPhoneAndPassword(
            @Param("phone") String phone,
            @Param("password") String password,
            @Param("roleNo") Long roleNo);

    CloudPassInfo findByPhone(String contactPhone);

    CloudPassInfo selectByPhoneAndStoreNo(
            @Param("phone") String phone,
            @Param("drugStoreNo") Long drugStoreNo);

    CloudPassInfo selectByPhoneAndDoctor(String phone);

    CloudPassInfo selectByIdNo(String idNo);

    CloudPassInfo selectByPhone(String phone);

    CloudPassInfo selectByPhoneIdNoPtNo(
            @Param("ptNo") Long ptNo,
            @Param("phone") String phone,
            @Param("idNo") String idNo);

    CloudPassInfo selectByPhoneIdNoDrNo(
            @Param("drNo") Long drNo,
            @Param("phone") String phone,
            @Param("idCard") String idCard);

    CloudPassInfo selectByPtNo(Long ptNo);

    CloudPassInfo selectByDrugStoreNo(Long drugStoreNo);

    CloudPassInfo findByPhoneDisRole(String phone);

    List<CloudPassInfo> findByPhoneDis(
            @Param("disNo") Long disNo,
            @Param("phone") String phone);

    List<CloudPassInfo> findByPhoneRole(String phone);

    CloudPassInfo selectByPhoneDrugStore(String phone);

    CloudPassInfo selectByPhoneAndPasswordNull(String phone);

    List<CloudPassInfo> selectByPhoneAndIDNo(
            @Param("phone") String phone,
            @Param("idNo") String idNo);
}