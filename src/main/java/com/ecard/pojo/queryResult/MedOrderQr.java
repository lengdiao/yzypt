package com.ecard.pojo.queryResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MedOrderQr {
    private Long medOrderNo;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date orderTime;
    private int payStatus;
    private Long medRecordNo;
    private int ShippingStatus;
    private Long mallNo;
    private int orderStatus;
    private String name;
    private PtInfoQr ptInfoQr;

    public PtInfoQr getPtInfoQr() {
        return ptInfoQr;
    }

    public void setPtInfoQr(PtInfoQr ptInfoQr) {
        this.ptInfoQr = ptInfoQr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getMallNo() {
        return mallNo;
    }

    public void setMallNo(Long mallNo) {
        this.mallNo = mallNo;
    }

    public int getShippingStatus() {
        return ShippingStatus;
    }

    public void setShippingStatus(int shippingStatus) {
        ShippingStatus = shippingStatus;
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

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }
}
