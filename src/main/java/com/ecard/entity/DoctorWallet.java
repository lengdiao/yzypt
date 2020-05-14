package com.ecard.entity;

import java.math.BigDecimal;

public class DoctorWallet {
    private Long walletId;

    private Long drNo;

    private BigDecimal amount;

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
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
}