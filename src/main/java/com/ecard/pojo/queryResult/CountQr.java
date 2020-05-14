package com.ecard.pojo.queryResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class CountQr {
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date orderTime;
    private BigDecimal orderAmount;
    private Long medRecordNo;
    private Long medOrderNo;
    private double number;

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public Long getMedRecordNo() {
        return medRecordNo;
    }

    public void setMedRecordNo(Long medRecordNo) {
        this.medRecordNo = medRecordNo;
    }

    public Long getMedOrderNo() {
        return medOrderNo;
    }

    public void setMedOrderNo(Long medOrderNo) {
        this.medOrderNo = medOrderNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
}
