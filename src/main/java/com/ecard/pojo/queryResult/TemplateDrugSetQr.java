package com.ecard.pojo.queryResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "TemplateDrugSetQr", description = "药品模板对象")
public class TemplateDrugSetQr {
    @ApiModelProperty(value = "药品编号", name = "drugSetNo")
    private Long drugSetNo;
    @ApiModelProperty(value = "药品数量", name = "number")
    private Integer number;
    @ApiModelProperty(value = "给药天数", name = "execDay")
    private Integer execDay;
    @ApiModelProperty(value = "给药途径", name = "wayNo")
    private String wayNo;
    @ApiModelProperty(value = "给药时机", name = "execWhen")
    private String execWhen;
    @ApiModelProperty(value = "给药目的", name = "execAim")
    private String execAim;
    @ApiModelProperty(value = "日剂量", name = "dayDose")
    private BigDecimal dayDose;
    @ApiModelProperty(value = "剂量", name = "dose")
    private BigDecimal dose;
    @ApiModelProperty(value = "剂量单位", name = "doseUnit")
    private String doseUnit;
    @ApiModelProperty(value = "给药频率", name = "usageNo")
    private String usageNo;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
        this.wayNo = wayNo;
    }

    public String getExecWhen() {
        return execWhen;
    }

    public void setExecWhen(String execWhen) {
        this.execWhen = execWhen;
    }

    public String getExecAim() {
        return execAim;
    }

    public void setExecAim(String execAim) {
        this.execAim = execAim;
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
        this.doseUnit = doseUnit;
    }

    public String getUsageNo() {
        return usageNo;
    }

    public void setUsageNo(String usageNo) {
        this.usageNo = usageNo;
    }
}
