package com.ecard.service;

import com.ecard.pojo.Response;

public interface DepartmentService {

    Response insert(String subjectCode, String subjectName, String deptName, String deputyDoctorCertID, String deputyDoctorName, String principalDoctorCertID, String principalDoctorName, Integer disableFlag);

    Response update(Long deptID, String subjectCode, String subjectName, String deptName, String deputyDoctorCertID, String deputyDoctorName, String principalDoctorCertID, String principalDoctorName, Integer disableFlag);

    Response select(String deptName, Integer disableFlag, Integer page, Integer rows);

    Response selectJgptDepartment();
}
