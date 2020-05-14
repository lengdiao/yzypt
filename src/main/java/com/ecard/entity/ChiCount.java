package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class ChiCount {
    private Long coutId;

    private Long drNo;

    private Date yearMonth;

    private Double addSum;

    private BigDecimal addAmount;

    private Double saveSum;

    private BigDecimal saveAmount;

    public Long getCoutId() {
        return coutId;
    }

    public void setCoutId(Long coutId) {
        this.coutId = coutId;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Date getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(Date yearMonth) {
        this.yearMonth = yearMonth;
    }

    public Double getAddSum() {
        return addSum;
    }

    public void setAddSum(Double addSum) {
        this.addSum = addSum;
    }

    public BigDecimal getAddAmount() {
        return addAmount;
    }

    public void setAddAmount(BigDecimal addAmount) {
        this.addAmount = addAmount;
    }

    public Double getSaveSum() {
        return saveSum;
    }

    public void setSaveSum(Double saveSum) {
        this.saveSum = saveSum;
    }

    public BigDecimal getSaveAmount() {
        return saveAmount;
    }

    public void setSaveAmount(BigDecimal saveAmount) {
        this.saveAmount = saveAmount;
    }
}