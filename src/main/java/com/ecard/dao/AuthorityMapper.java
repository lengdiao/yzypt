package com.ecard.dao;

import java.util.List;

import com.ecard.pojo.queryResult.Authoritys;
import org.apache.ibatis.annotations.Mapper;

import com.ecard.entity.Authority;

@Mapper
public interface AuthorityMapper {
    int deleteByPrimaryKey(Long authorityNo);

    int insert(Authority record);

    int insertSelective(Authority record);

    Authority selectByPrimaryKey(Long authorityNo);

    int updateByPrimaryKeySelective(Authority record);

    int updateByPrimaryKey(Authority record);

	List<Authoritys> selectByRoleno(Long roleNo);

	List<Authoritys> selectAllauths();
}