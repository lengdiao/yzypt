package com.ecard.pojo.queryResult;

import java.math.BigDecimal;
import java.util.Date;

public class MallOrderCount {
    private Long medOrderNo;
    private Date orderTime;
    private String name;
    private String drName;
    private String contact;
    private String drugStoreName;
    private int shippingStatus;
    private BigDecimal orderAmount;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrName() {
        return drName;
    }

    public void setDrName(String drName) {
        this.drName = drName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDrugStoreName() {
        return drugStoreName;
    }

    public void setDrugStoreName(String drugStoreName) {
        this.drugStoreName = drugStoreName;
    }

    public int getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(int shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }
}
