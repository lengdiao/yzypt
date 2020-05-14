package com.ecard.pojo.queryResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DrCountQr {
    private String drugName;
    private String drName;
    private BigDecimal saveAmount;
    private double saveSum;
    private BigDecimal addAmount;
    private double addSum;
    private int prescriptionNum;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date date;
    private String disName;
    private String disLeaderName;
    private double sum;
    private BigDecimal amountSum;
    private List<PcCountQr> pcCountQrList;

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

    public double getSaveSum() {
        return saveSum;
    }

    public void setSaveSum(double saveSum) {
        this.saveSum = saveSum;
    }

    public BigDecimal getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(BigDecimal addAmount) {
        this.addAmount = addAmount;
    }

    public double getAddSum() {
        return addSum;
    }

    public void setAddSum(double addSum) {
        this.addSum = addSum;
    }

    public int getPrescriptionNum() {
        return prescriptionNum;
    }

    public void setPrescriptionNum(int prescriptionNum) {
        this.prescriptionNum = prescriptionNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public BigDecimal getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(BigDecimal amountSum) {
        this.amountSum = amountSum;
    }

    public List<PcCountQr> getPcCountQrList() {
        return pcCountQrList;
    }

    public void setPcCountQrList(List<PcCountQr> pcCountQrList) {
        this.pcCountQrList = pcCountQrList;
    }
}
