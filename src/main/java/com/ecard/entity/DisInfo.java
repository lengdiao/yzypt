package com.ecard.entity;

import java.util.Date;

public class DisInfo {
    private Long disNo;

    private Long cloudPassNo;

    private String disName;

    private String telePhone;

    private String address;

    private String email;

    private String disLevel;

    private Long disLeader;

    private Integer disableFlag;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private String version;

    public Long getDisNo() {
        return disNo;
    }

    public void setDisNo(Long disNo) {
        this.disNo = disNo;
    }

    public Long getCloudPassNo() {
        return cloudPassNo;
    }

    public void setCloudPassNo(Long cloudPassNo) {
        this.cloudPassNo = cloudPassNo;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName == null ? null : disName.trim();
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone == null ? null : telePhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getDisLevel() {
        return disLevel;
    }

    public void setDisLevel(String disLevel) {
        this.disLevel = disLevel == null ? null : disLevel.trim();
    }

    public Long getDisLeader() {
        return disLeader;
    }

    public void setDisLeader(Long disLeader) {
        this.disLeader = disLeader;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }
}