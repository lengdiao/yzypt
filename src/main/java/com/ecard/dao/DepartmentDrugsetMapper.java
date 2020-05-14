package com.ecard.dao;

import com.ecard.entity.DepartmentDrugset;

import java.util.List;

public interface DepartmentDrugsetMapper {
    int deleteByPrimaryKey(Long deptDrugNo);

    int insert(DepartmentDrugset record);

    int insertSelective(DepartmentDrugset record);

    DepartmentDrugset selectByPrimaryKey(Long deptDrugNo);

    int updateByPrimaryKeySelective(DepartmentDrugset record);

    int updateByPrimaryKey(DepartmentDrugset record);

    void deleteByDrugNo(Long drugSetNo);

    List<String> selectByDrugNo(Long drugSetNo);
}