package com.ecard.service.serviceImpl;

import com.ecard.dao.DepartmentMapper;
import com.ecard.dao.JgptDepartmentMapper;
import com.ecard.entity.Department;
import com.ecard.entity.JgptDepartment;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.queryResult.DrInfoQr;
import com.ecard.service.DepartmentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private JgptDepartmentMapper jgptDepartmentMapper;

    public Response insert(String subjectCode, String subjectName, String deptName, String deputyDoctorCertID, String deputyDoctorName, String principalDoctorCertID, String principalDoctorName, Integer disableFlag) {
        ResponseHasData response = new ResponseHasData();
        try {
            Department department = new Department();
            department.setDisableFlag(disableFlag);
            department.setDeptName(deptName);
            department.setDeputyDoctorCertID(deputyDoctorCertID);
            department.setDeputyDoctorName(deputyDoctorName);
            department.setPrincipalDoctorCertID(principalDoctorCertID);
            department.setPrincipalDoctorName(principalDoctorName);
            department.setSubjectCode(subjectCode);
            department.setSubjectName(subjectName);
            departmentMapper.insertSelective(department);

            response.setMsg("新增科室成功");
            response.setStatus(0);
        } catch (Exception e) {
            response.setMsg("新增科室失败");
            response.setStatus(1);
            e.printStackTrace();
        }
        return response;
    }


    public Response update(Long deptID, String subjectCode, String subjectName, String deptName, String deputyDoctorCertID, String deputyDoctorName, String principalDoctorCertID, String principalDoctorName, Integer disableFlag) {
        ResponseHasData response = new ResponseHasData();
        try {
            Department department = departmentMapper.selectByPrimaryKey(deptID);
            department.setDisableFlag(disableFlag);
            department.setDeptName(deptName);
            department.setDeputyDoctorCertID(deputyDoctorCertID);
            department.setDeputyDoctorName(deputyDoctorName);
            department.setPrincipalDoctorCertID(principalDoctorCertID);
            department.setPrincipalDoctorName(principalDoctorName);
            department.setSubjectCode(subjectCode);
            department.setSubjectName(subjectName);
            departmentMapper.updateByPrimaryKeySelective(department);

            response.setMsg("修改科室成功");
            response.setStatus(0);
        } catch (Exception e) {
            response.setMsg("修改科室失败");
            response.setStatus(1);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response select(String deptName, Integer disableFlag, Integer page, Integer rows) {
        ResponseHasData response = new ResponseHasData();
        try {
            Page<?> pa =  PageHelper.startPage(page, rows);
            List<Department> piq = departmentMapper.selectByNameFlag(deptName,disableFlag);
            //查询结果总数
            Long total = pa.getTotal();
            //创建分页条件
            PageInfo<Department> pageData = new PageInfo<Department>(piq,total.intValue());

            response.setStatus(0);
            response.setMsg("查询科室成功");
            response.setData(pageData);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询科室失败");
        }
        return response;
    }

    @Override
    public Response selectJgptDepartment() {
        ResponseHasData response = new ResponseHasData();
        try {
            List<JgptDepartment> jgptDepartments = jgptDepartmentMapper.selectAll();

            response.setStatus(0);
            response.setMsg("查询科室成功");
            response.setData(jgptDepartments);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(1);
            response.setMsg("查询科室失败");
        }
        return response;
    }
}
