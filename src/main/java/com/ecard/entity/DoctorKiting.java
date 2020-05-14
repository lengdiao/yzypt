package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DoctorKiting {
    private Long kitingId;

    private Long drNo;

    private BigDecimal amount;

    private Date createDate;

    private String cardNo;

    private Long isSuccessful;

    public Long getKitingId() {
        return kitingId;
    }

    public void setKitingId(Long kitingId) {
        this.kitingId = kitingId;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    public Long getIsSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Long isSuccessful) {
        this.isSuccessful = isSuccessful;
    }
}