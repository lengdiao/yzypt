package com.ecard.dao;

import com.ecard.entity.JgptDepartment;

import java.util.List;

public interface JgptDepartmentMapper {
    int deleteByPrimaryKey(String departmentNo);

    int insert(JgptDepartment record);

    int insertSelective(JgptDepartment record);

    JgptDepartment selectByPrimaryKey(String departmentNo);

    int updateByPrimaryKeySelective(JgptDepartment record);

    int updateByPrimaryKey(JgptDepartment record);

    List<JgptDepartment> selectAll();
}