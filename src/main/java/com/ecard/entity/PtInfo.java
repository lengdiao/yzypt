package com.ecard.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PtInfo {
    private Long ptNo;

    private Long cloudPassNo;

    private String ybType;

    private String ybCardNo;

    private String ybIntCardNo;

    private String ybCardString;

    private String sex;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthDay;

    private Double height;

    private Double weight;

    private Integer disableFlag;

    private String createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String updateUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private Integer version;

    private String name;

    private String IdNo;

    private String phone;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdNo() {
        return IdNo;
    }

    public void setIdNo(String idNo) {
        IdNo = idNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPtNo() {
        return ptNo;
    }

    public void setPtNo(Long ptNo) {
        this.ptNo = ptNo;
    }

    public Long getCloudPassNo() {
        return cloudPassNo;
    }

    public void setCloudPassNo(Long cloudPassNo) {
        this.cloudPassNo = cloudPassNo;
    }

    public String getYbType() {
        return ybType;
    }

    public void setYbType(String ybType) {
        this.ybType = ybType == null ? null : ybType.trim();
    }

    public String getYbCardNo() {
        return ybCardNo;
    }

    public void setYbCardNo(String ybCardNo) {
        this.ybCardNo = ybCardNo == null ? null : ybCardNo.trim();
    }

    public String getYbIntCardNo() {
        return ybIntCardNo;
    }

    public void setYbIntCardNo(String ybIntCardNo) {
        this.ybIntCardNo = ybIntCardNo == null ? null : ybIntCardNo.trim();
    }

    public String getYbCardString() {
        return ybCardString;
    }

    public void setYbCardString(String ybCardString) {
        this.ybCardString = ybCardString == null ? null : ybCardString.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}