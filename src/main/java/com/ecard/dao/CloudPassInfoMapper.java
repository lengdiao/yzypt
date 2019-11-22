package com.ecard.dao;

import com.ecard.entity.CloudPassInfo;
import org.apache.ibatis.annotations.Param;

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
}