package com.ecard.pojo.queryResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(value = "TemplateDrugSetQr", description = "药品模板对象")
public class TemplateDrugSetQr {
    @ApiModelProperty(value = "药品编号", name = "drugSetNo")
    private Long drugSetNo;
    @ApiModelProperty(value = "药品数量", name = "number")
    private Double number;
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
    @ApiModelProperty(value = "药品名称", name = "commonName")
    private String commonName;
    @ApiModelProperty(value = "除数 (次剂量)", name = "divisor")
    private BigDecimal divisor;
    @ApiModelProperty(value = "规格（按体重）", name = "dosege")
    private BigDecimal dosege;
    @ApiModelProperty(value = "成份单位", name = "dosegeUnit")
    private String dosegeUnit;
    @ApiModelProperty(value = "药品价格", name = "saleGenPrice")
    private BigDecimal saleGenPrice;
    @ApiModelProperty(value = "被除数 (销售量)", name = "dividend")
    private BigDecimal dividend;
    @ApiModelProperty(value = "销售单位", name = "saleUnit")
    private String saleUnit;
    @ApiModelProperty(value = "剂量", name = "dose1")
    private BigDecimal dose1;
    @ApiModelProperty(value = "次剂量单位", name = "doseUnit1")
    private String doseUnit1;

    public BigDecimal getDose1() {
        return dose1;
    }

    public void setDose1(BigDecimal dose1) {
        this.dose1 = dose1;
    }

    public String getDoseUnit1() {
        return doseUnit1;
    }

    public void setDoseUnit1(String doseUnit1) {
        this.doseUnit1 = doseUnit1;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public BigDecimal getSaleGenPrice() {
        return saleGenPrice;
    }

    public void setSaleGenPrice(BigDecimal saleGenPrice) {
        this.saleGenPrice = saleGenPrice;
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
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

    public BigDecimal getDivisor() {
        return divisor;
    }

    public void setDivisor(BigDecimal divisor) {
        this.divisor = divisor;
    }

    public BigDecimal getDosege() {
        return dosege;
    }

    public void setDosege(BigDecimal dosege) {
        this.dosege = dosege;
    }

    public String getDosegeUnit() {
        return dosegeUnit;
    }

    public void setDosegeUnit(String dosegeUnit) {
        this.dosegeUnit = dosegeUnit;
    }

 }
