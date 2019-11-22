package com.ecard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.ecard.entity.Role;
import com.ecard.entity.UserRole;
@Mapper
public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

	UserRole selectByCloudPassNo(Long cloudPassNo);

	UserRole selectByRoleNo(Long roleNo);

	UserRole selectByCPINo(Long cloudPassNo);
}