package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Template {
    private Long templateId;

    private Long templateNo;

    private Long drNo;

    private String DNo;

    private String templateName;

    private Integer templateType;

    private Long drugSetNo;

    private Double number;

    private Integer execDay;

    private String wayNo;

    private String execWhen;

    private String execAim;

    private BigDecimal dayDose;

    private BigDecimal dose;

    private String doseUnit;

    private String usageNo;

    private Integer prescriptionNum;

    private String specialUsage;

    private Date createTime;

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public String getDNo() {
        return DNo;
    }

    public void setDNo(String DNo) {
        this.DNo = DNo;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Long getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(Long templateNo) {
        this.templateNo = templateNo;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName == null ? null : templateName.trim();
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public Long getDrugSetNo() {
        return drugSetNo;
    }

    public void setDrugSetNo(Long drugSetNo) {
        this.drugSetNo = drugSetNo;
    }

    public Integer getExecDay() {
        return execDay;
    }

    public void setExecDay(Integer execDay) {
        this.execDay = execDay;
    }

    public String getWayNo() {
        return wayNo;
    }

    public void setWayNo(String wayNo) {
        this.wayNo = wayNo == null ? null : wayNo.trim();
    }

    public String getExecWhen() {
        return execWhen;
    }

    public void setExecWhen(String execWhen) {
        this.execWhen = execWhen == null ? null : execWhen.trim();
    }

    public String getExecAim() {
        return execAim;
    }

    public void setExecAim(String execAim) {
        this.execAim = execAim == null ? null : execAim.trim();
    }

    public BigDecimal getDayDose() {
        return dayDose;
    }

    public void setDayDose(BigDecimal dayDose) {
        this.dayDose = dayDose;
    }

    public BigDecimal getDose() {
        return dose;
    }

    public void setDose(BigDecimal dose) {
        this.dose = dose;
    }

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit == null ? null : doseUnit.trim();
    }

    public String getUsageNo() {
        return usageNo;
    }

    public void setUsageNo(String usageNo) {
        this.usageNo = usageNo == null ? null : usageNo.trim();
    }

    public Integer getPrescriptionNum() {
        return prescriptionNum;
    }

    public void setPrescriptionNum(Integer prescriptionNum) {
        this.prescriptionNum = prescriptionNum;
    }

    public String getSpecialUsage() {
        return specialUsage;
    }

    public void setSpecialUsage(String specialUsage) {
        this.specialUsage = specialUsage == null ? null : specialUsage.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}