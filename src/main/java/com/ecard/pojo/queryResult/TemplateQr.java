package com.ecard.pojo.queryResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

@ApiModel(value = "TemplateQr", description = "模板对象")
public class TemplateQr {
    @ApiModelProperty(value = "模板表编号（编辑药品时传medOrderNo）", name = "templateId")
    private Long templateId;
    @ApiModelProperty(value = "模板编号", name = "templateNo")
    private Long templateNo;
    @ApiModelProperty(value = "医生编号", name = "drNo")
    private Long drNo;
    @ApiModelProperty(value = "模板名称", name = "templateName")
    private String templateName;
    @ApiModelProperty(value = "模板类型（1:中药2：西药）", name = "templateType")
    private Integer templateType;
    @ApiModelProperty(value = "中药贴数", name = "prescriptionNum")
    private Integer prescriptionNum;
    @ApiModelProperty(value = "中药用法(1.口服 2.外敷)", name = "specialUsage")
    private String specialUsage;
    @ApiModelProperty(value = "疾病编号", name = "dNo")
    private String dNo;
    @ApiModelProperty(value = "模板备注", name = "remark")
    private String remark;
    @ApiModelProperty(value = "单萜价格", name = "price")
    private BigDecimal price;
    @ApiModelProperty(value = "使用频率（中药）", name = "usageNo")
    private String usageNo;

    @ApiModelProperty(value = "疾病中文", name = "dName")
    private String dName;
    @ApiModelProperty(value = "药品集合", name = "list")
    private List<TemplateDrugSetQr> list;


    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getUsageNo() {
        return usageNo;
    }

    public void setUsageNo(String usageNo) {
        this.usageNo = usageNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getdNo() {
        return dNo;
    }

    public void setdNo(String dNo) {
        this.dNo = dNo;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public Integer getPrescriptionNum() {
        return prescriptionNum;
    }

    public Long getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(Long templateNo) {
        this.templateNo = templateNo;
    }

    public void setPrescriptionNum(Integer prescriptionNum) {
        this.prescriptionNum = prescriptionNum;
    }

    public String getSpecialUsage() {
        return specialUsage;
    }

    public void setSpecialUsage(String specialUsage) {
        this.specialUsage = specialUsage;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public List<TemplateDrugSetQr> getList() {
        return list;
    }

    public void setList(List<TemplateDrugSetQr> list) {
        this.list = list;
    }
}
