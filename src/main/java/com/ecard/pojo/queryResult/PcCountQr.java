package com.ecard.pojo.queryResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PcCountQr {
    private String drugName;
    private String drName;
    private BigDecimal saveAmount;
    private double saveSum;
    private BigDecimal addAmount;
    private double addSum;
    private int prescriptionNum;
    @JsonFormat(pattern="yyyy-MM",timezone="GMT+8")
    private Date date;
    private String disName;
    private String disLeaderName;
    private double sum;
    private BigDecimal amountSum;

    public BigDecimal getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(BigDecimal amountSum) {
        this.amountSum = amountSum;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisLeaderName() {
        return disLeaderName;
    }

    public void setDisLeaderName(String disLeaderName) {
        this.disLeaderName = disLeaderName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSaveSum() {
        return saveSum;
    }

    public void setSaveSum(double saveSum) {
        this.saveSum = saveSum;
    }

    public double getAddSum() {
        return addSum;
    }

    public void setAddSum(double addSum) {
        this.addSum = addSum;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public BigDecimal getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }

    public BigDecimal getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(BigDecimal addAmount) {
        this.addAmount = addAmount;
    }

    public int getPrescriptionNum() {
        return prescriptionNum;
    }

    public void setPrescriptionNum(int prescriptionNum) {
        this.prescriptionNum = prescriptionNum;
    }
}
