package com.ecard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecard.entity.RoleAuthority;
@Mapper
public interface RoleAuthorityMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    RoleAuthority selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAuthority record);

    int updateByPrimaryKey(RoleAuthority record);

	List<RoleAuthority> selectByRoleNo(Long roleNo);

	void deleteByRoleNo(Long roleNo);
}