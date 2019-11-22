package com.ecard.dao;

import com.ecard.entity.Role;
import com.ecard.pojo.queryResult.RoleListQr;
import com.ecard.pojo.queryResult.RoleQr;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Long roleNo);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long roleNo);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

	Long selectbyRoleName(String roleName);

	List<RoleListQr> selectRoleList(@Param("roleName") String roleName);

	RoleQr selectByNo(int parseInt);
}