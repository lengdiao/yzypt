package com.ecard.dao;

import com.ecard.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long deptID);

    int insert(Department record);

    int insertSelective(Department record);

    Department selectByPrimaryKey(Long deptID);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    List<Department> selectByNameFlag(
            @Param("deptName") String deptName,
            @Param("disableFlag") Integer disableFlag);

    List<Department> selectAll(String deptName);
}