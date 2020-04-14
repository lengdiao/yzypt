package com.ecard.pojo.queryResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PtInfoQr {
    private String name;

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

    private String phone;

    private String idNo;

    private int age;

    private int newMessage;

    public int getNewMessage() {
        return newMessage;
    }

    public void setNewMessage(int newMessage) {
        this.newMessage = newMessage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getYbType() {
        return ybType;
    }

    public void setYbType(String ybType) {
        this.ybType = ybType;
    }

    public String getYbCardNo() {
        return ybCardNo;
    }

    public void setYbCardNo(String ybCardNo) {
        this.ybCardNo = ybCardNo;
    }

    public String getYbIntCardNo() {
        return ybIntCardNo;
    }

    public void setYbIntCardNo(String ybIntCardNo) {
        this.ybIntCardNo = ybIntCardNo;
    }

    public String getYbCardString() {
        return ybCardString;
    }

    public void setYbCardString(String ybCardString) {
        this.ybCardString = ybCardString;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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
        this.createUser = createUser;
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
