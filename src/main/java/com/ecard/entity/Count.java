package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Count {
    private Long countId;

    private Long drNo;

    private Long drugNo;

    private Date yearMonth;

    private BigDecimal addAmount;

    private Double addSum;

    private BigDecimal saveAmount;

    private Double saveSum;

    public Long getCountId() {
        return countId;
    }

    public void setCountId(Long countId) {
        this.countId = countId;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Long getDrugNo() {
        return drugNo;
    }

    public void setDrugNo(Long drugNo) {
        this.drugNo = drugNo;
    }

    public Date getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Date yearMonth) {
        this.yearMonth = yearMonth;
    }

    public BigDecimal getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(BigDecimal addAmount) {
        this.addAmount = addAmount;
    }

    public Double getAddSum() {
        return addSum;
    }

    public void setAddSum(Double addSum) {
        this.addSum = addSum;
    }

    public BigDecimal getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }

    public Double getSaveSum() {
        return saveSum;
    }

    public void setSaveSum(Double saveSum) {
        this.saveSum = saveSum;
    }
}