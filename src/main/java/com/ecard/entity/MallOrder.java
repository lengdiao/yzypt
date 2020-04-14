package com.ecard.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class MallOrder {
    @ApiModelProperty(value = "订单编号（新增不用传）", name = "mallNo")
    private Long mallNo;
    @ApiModelProperty(value = "患者编号", name = "ptNo")
    private Long ptNo;
    @ApiModelProperty(value = "医生编号", name = "drNo")
    private Long drNo;
    @ApiModelProperty(value = "没用", name = "disNo")
    private Long disNo;
    @ApiModelProperty(value = "药店编号（创建订单为null）", name = "mallNo")
    private Long drugStoreNo;
    @ApiModelProperty(value = "处方单号", name = "medOrderNo")
    private Long medOrderNo;
    @ApiModelProperty(value = "订单状态(0为已完成，1为未完成）", name = "orderStatus")
    private Integer orderStatus;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "订单创建时间", name = "orderTime")
    private Date orderTime;
    @ApiModelProperty(value = "没用", name = "phone")
    private String phone;
    @ApiModelProperty(value = "地址编号（新增订单使用，查询使用receiptAddress）", name = "address")
    private String address;
    @ApiModelProperty(value = "付款状态（0付款1未付款）", name = "payStatus")
    private Integer payStatus;
    @ApiModelProperty(value = "付款时间", name = "payTime")
    private Date payTime;
    @ApiModelProperty(value = "配送状态  1.未配送，0.已配送 , 2.未自提 ，3.已自提", name = "shippingStatus")
    private Integer shippingStatus;
    @ApiModelProperty(value = "快递公司", name = "shippingCompany")
    private String shippingCompany;
    @ApiModelProperty(value = "没用", name = "shippingContext")
    private String shippingContext;
    @ApiModelProperty(value = "快递编号", name = "shippingNo")
    private String shippingNo;
    @ApiModelProperty(value = "快递单号填写时间", name = "shippingTime")
    private Date shippingTime;
    @ApiModelProperty(value = "实付金额", name = "orderAmount")
    private BigDecimal orderAmount;

    private String createUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    private String updateUser;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
    @ApiModelProperty(value = "1:自主下单2:在线问诊3:自助下单,在线问诊不显示4:优惠商品,在线问诊不显示5:优惠商品,在线问诊显示", name = "version")
    private Integer version;
    @ApiModelProperty(value = "订单地址", name = "receiptAddress")
    private ReceiptAddress receiptAddress;

    @ApiModelProperty(value = "订单总额", name = "rental")
    private BigDecimal rental;
    @ApiModelProperty(value = "优惠描述", name = "activityDetail")
    private String activityDetail;
    @ApiModelProperty(value = "优惠的金额", name = "offMoney")
    private BigDecimal offMoney;
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;
    @ApiModelProperty(value = "红包金额", name = "packetId")
    private Long packetId;
    @ApiModelProperty(value = "红包金额", name = "packetAmount")
    private BigDecimal packetAmount;

    public BigDecimal getPacketAmount() {
        return packetAmount;
    }

    public void setPacketAmount(BigDecimal packetAmount) {
        this.packetAmount = packetAmount;
    }

    public Long getPacketId() {
        return packetId;
    }

    public void setPacketId(Long packetId) {
        this.packetId = packetId;
    }

    public BigDecimal getRental() {
        return rental;
    }

    public void setRental(BigDecimal rental) {
        this.rental = rental;
    }

    public String getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail;
    }

    public BigDecimal getOffMoney() {
        return offMoney;
    }

    public void setOffMoney(BigDecimal offMoney) {
        this.offMoney = offMoney;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public ReceiptAddress getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(ReceiptAddress receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public Long getMallNo() {
        return mallNo;
    }

    public void setMallNo(Long mallNo) {
        this.mallNo = mallNo;
    }

    public Long getPtNo() {
        return ptNo;
    }

    public void setPtNo(Long ptNo) {
        this.ptNo = ptNo;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Long getDisNo() {
        return disNo;
    }

    public void setDisNo(Long disNo) {
        this.disNo = disNo;
    }

    public Long getDrugStoreNo() {
        return drugStoreNo;
    }

    public void setDrugStoreNo(Long drugStoreNo) {
        this.drugStoreNo = drugStoreNo;
    }

    public Long getMedOrderNo() {
        return medOrderNo;
    }

    public void setMedOrderNo(Long medOrderNo) {
        this.medOrderNo = medOrderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany == null ? null : shippingCompany.trim();
    }

    public String getShippingContext() {
        return shippingContext;
    }

    public void setShippingContext(String shippingContext) {
        this.shippingContext = shippingContext == null ? null : shippingContext.trim();
    }

    public String getShippingNo() {
        return shippingNo;
    }

    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo == null ? null : shippingNo.trim();
    }

    public Date getShippingTime() {
        return shippingTime;
    }

    public void setShippingTime(Date shippingTime) {
        this.shippingTime = shippingTime;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}