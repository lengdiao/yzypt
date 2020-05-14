package com.ecard.pojo.queryResult;

import java.math.BigDecimal;

public class DoctorPacketQr {
    private BigDecimal packetAmount;
    private BigDecimal kittingAmount;
    private BigDecimal rightAmount;

    public BigDecimal getPacketAmount() {
        return packetAmount;
    }

    public void setPacketAmount(BigDecimal packetAmount) {
        this.packetAmount = packetAmount;
    }

    public BigDecimal getKittingAmount() {
        return kittingAmount;
    }

    public void setKittingAmount(BigDecimal kittingAmount) {
        this.kittingAmount = kittingAmount;
    }

    public BigDecimal getRightAmount() {
        return rightAmount;
    }

    public void setRightAmount(BigDecimal rightAmount) {
        this.rightAmount = rightAmount;
    }
}
