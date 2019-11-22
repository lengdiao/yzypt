package com.ecard.entity;

public class Department {
    private Long deptID;

    private String subjectCode;

    private String subjectName;

    private String deptName;

    private String deputyDoctorCertID;

    private String deputyDoctorName;

    private String principalDoctorCertID;

    private String principalDoctorName;

    private Integer disableFlag;

    public Long getDeptID() {
        return deptID;
    }

    public void setDeptID(Long deptID) {
        this.deptID = deptID;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode == null ? null : subjectCode.trim();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName == null ? null : subjectName.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeputyDoctorCertID() {
        return deputyDoctorCertID;
    }

    public void setDeputyDoctorCertID(String deputyDoctorCertID) {
        this.deputyDoctorCertID = deputyDoctorCertID == null ? null : deputyDoctorCertID.trim();
    }

    public String getDeputyDoctorName() {
        return deputyDoctorName;
    }

    public void setDeputyDoctorName(String deputyDoctorName) {
        this.deputyDoctorName = deputyDoctorName == null ? null : deputyDoctorName.trim();
    }

    public String getPrincipalDoctorCertID() {
        return principalDoctorCertID;
    }

    public void setPrincipalDoctorCertID(String principalDoctorCertID) {
        this.principalDoctorCertID = principalDoctorCertID == null ? null : principalDoctorCertID.trim();
    }

    public String getPrincipalDoctorName() {
        return principalDoctorName;
    }

    public void setPrincipalDoctorName(String principalDoctorName) {
        this.principalDoctorName = principalDoctorName == null ? null : principalDoctorName.trim();
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }
}