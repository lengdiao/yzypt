package com.ecard.pojo.queryResult;

import com.ecard.entity.Activity;
import com.ecard.entity.DrugSet;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class MedItemQr {
    @ApiModelProperty(value = "处方明细编号", name = "medItemNo")
    private Long medItemNo;
    @ApiModelProperty(value = "处方编号", name = "medOrderNo")
    private Long medOrderNo;
    @ApiModelProperty(value = "药品编号", name = "drugNo")
    private Long drugNo;
    @ApiModelProperty(value = "药品数量", name = "number")
    private Double number;
    @ApiModelProperty(value = "给药天数", name = "execDay")
    private Integer execDay;
    @ApiModelProperty(value = "给药途径", name = "wayNo")
    private String wayNo;
    @ApiModelProperty(value = "给药时机（没用）", name = "execWhen")
    private String execWhen;
    @ApiModelProperty(value = "给药目的（没用）", name = "execAim")
    private String execAim;
    @ApiModelProperty(value = "日剂量（没用）", name = "dayDose")
    private BigDecimal dayDose;
    @ApiModelProperty(value = "剂量", name = "dose")
    private BigDecimal dose;
    @ApiModelProperty(value = "剂量单位", name = "doseUnit")
    private String doseUnit;
    @ApiModelProperty(value = "给药频率", name = "usageNo")
    private String usageNo;
    private DrugSet drugSet;
    private BigDecimal saleGenPrice;
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public BigDecimal getSaleGenPrice() {
        return saleGenPrice;
    }

    public void setSaleGenPrice(BigDecimal saleGenPrice) {
        this.saleGenPrice = saleGenPrice;
    }

    public DrugSet getDrugSet() {
        return drugSet;
    }

    public void setDrugSet(DrugSet drugSet) {
        this.drugSet = drugSet;
    }

    public Long getMedItemNo() {
        return medItemNo;
    }

    public void setMedItemNo(Long medItemNo) {
        this.medItemNo = medItemNo;
    }

    public Long getMedOrderNo() {
        return medOrderNo;
    }

    public void setMedOrderNo(Long medOrderNo) {
        this.medOrderNo = medOrderNo;
    }

    public Long getDrugNo() {
        return drugNo;
    }

    public void setDrugNo(Long drugNo) {
        this.drugNo = drugNo;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
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
