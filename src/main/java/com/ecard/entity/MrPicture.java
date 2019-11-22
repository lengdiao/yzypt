package com.ecard.entity;

import java.util.Date;

public class MrPicture {
    private Long mrPictureNo;

    private String mrPictureAddress;

    private String medRecordNo;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    private Integer version;

    public Long getMrPictureNo() {
        return mrPictureNo;
    }

    public void setMrPictureNo(Long mrPictureNo) {
        this.mrPictureNo = mrPictureNo;
    }

    public String getMrPictureAddress() {
        return mrPictureAddress;
    }

    public void setMrPictureAddress(String mrPictureAddress) {
        this.mrPictureAddress = mrPictureAddress == null ? null : mrPictureAddress.trim();
    }

    public String getMedRecordNo() {
        return medRecordNo;
    }

    public void setMedRecordNo(String medRecordNo) {
        this.medRecordNo = medRecordNo == null ? null : medRecordNo.trim();
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
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