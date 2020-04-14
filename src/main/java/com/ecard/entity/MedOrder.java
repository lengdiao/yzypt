package com.ecard.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class MedOrder {
    private Long orderNo;

    private String medRecordNo;

    private String drugStoreNo;

    private String execUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date execTime;

    private String execLoc;

    private String execRefuseReason;

    private Integer orderStatus;

    private BigDecimal orderAmount;

    private String diagContent;

    private Integer orderType;

    private Integer prescriptionNum;

    private String prescriptionName;

    private Integer decoctionWay;

    private Integer decoctionNum;

    private String useTimes;

    private String specialUsage;

    private Integer disableFlag;

    private Long createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private Long updateUser;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date updateTime;

    private Integer version;

    private String remark;

    private BigDecimal unitPrice;

    private int addStatus;

    public int getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(int addStatus) {
        this.addStatus = addStatus;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public String getMedRecordNo() {
        return medRecordNo;
    }

    public void setMedRecordNo(String medRecordNo) {
        this.medRecordNo = medRecordNo == null ? null : medRecordNo.trim();
    }

    public String getDrugStoreNo() {
        return drugStoreNo;
    }

    public void setDrugStoreNo(String drugStoreNo) {
        this.drugStoreNo = drugStoreNo == null ? null : drugStoreNo.trim();
    }

    public String getExecUser() {
        return execUser;
    }

    public void setExecUser(String execUser) {
        this.execUser = execUser == null ? null : execUser.trim();
    }

    public Date getExecTime() {
        return execTime;
    }

    public void setExecTime(Date execTime) {
        this.execTime = execTime;
    }

    public String getExecLoc() {
        return execLoc;
    }

    public void setExecLoc(String execLoc) {
        this.execLoc = execLoc == null ? null : execLoc.trim();
    }

    public String getExecRefuseReason() {
        return execRefuseReason;
    }

    public void setExecRefuseReason(String execRefuseReason) {
        this.execRefuseReason = execRefuseReason == null ? null : execRefuseReason.trim();
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDiagContent() {
        return diagContent;
    }

    public void setDiagContent(String diagContent) {
        this.diagContent = diagContent == null ? null : diagContent.trim();
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getPrescriptionNum() {
        return prescriptionNum;
    }

    public void setPrescriptionNum(Integer prescriptionNum) {
        this.prescriptionNum = prescriptionNum;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName == null ? null : prescriptionName.trim();
    }

    public Integer getDecoctionWay() {
        return decoctionWay;
    }

    public void setDecoctionWay(Integer decoctionWay) {
        this.decoctionWay = decoctionWay;
    }

    public Integer getDecoctionNum() {
        return decoctionNum;
    }

    public void setDecoctionNum(Integer decoctionNum) {
        this.decoctionNum = decoctionNum;
    }

    public String getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(String useTimes) {
        this.useTimes = useTimes == null ? null : useTimes.trim();
    }

    public String getSpecialUsage() {
        return specialUsage;
    }

    public void setSpecialUsage(String specialUsage) {
        this.specialUsage = specialUsage == null ? null : specialUsage.trim();
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
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