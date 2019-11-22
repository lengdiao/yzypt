package com.ecard.entity;

import java.util.Date;

public class MedRecord {
    private Long medRecordNo;

    private String subMedRecordNo;

    private Long ptNo;

    private Long drNo;

    private Long symptomNo;

    private String diagContent;

    private String subjective;

    private String objective;

    private String plan;

    private Date visitDate;

    private Integer medRecordStatus;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer version;

    public Long getMedRecordNo() {
        return medRecordNo;
    }

    public void setMedRecordNo(Long medRecordNo) {
        this.medRecordNo = medRecordNo;
    }

    public String getSubMedRecordNo() {
        return subMedRecordNo;
    }

    public void setSubMedRecordNo(String subMedRecordNo) {
        this.subMedRecordNo = subMedRecordNo == null ? null : subMedRecordNo.trim();
    }

    public Long getPtNo() {
        return ptNo;
    }

    public void setPtNo(Long ptNo) {
        this.ptNo = ptNo;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Long getSymptomNo() {
        return symptomNo;
    }

    public void setSymptomNo(Long symptomNo) {
        this.symptomNo = symptomNo;
    }

    public String getDiagContent() {
        return diagContent;
    }

    public void setDiagContent(String diagContent) {
        this.diagContent = diagContent == null ? null : diagContent.trim();
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective == null ? null : subjective.trim();
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective == null ? null : objective.trim();
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan == null ? null : plan.trim();
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getMedRecordStatus() {
        return medRecordStatus;
    }

    public void setMedRecordStatus(Integer medRecordStatus) {
        this.medRecordStatus = medRecordStatus;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}