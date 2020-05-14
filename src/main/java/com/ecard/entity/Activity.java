package com.ecard.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class Activity {
    @ApiModelProperty(value = "修改需要填", name = "activityNo")
    private Long activityNo;
    @ApiModelProperty(value = "编号（新增为空，与drugSetNo一致）", name = "goodsNo")
    private Long goodsNo;
    @ApiModelProperty(value = "优惠商品名称", name = "activityName")
    private String activityName;
    @ApiModelProperty(value = "优惠商品描述", name = "activityDetail")
    private String activityDetail;
    @ApiModelProperty(value = "优惠类型", name = "activityType")
    private String activityType;
    @ApiModelProperty(value = "买个数", name = "leftNumber")
    private Integer leftNumber;
    @ApiModelProperty(value = "增个数", name = "rightNumber")
    private Integer rightNumber;
    @ApiModelProperty(value = "原价", name = "originalPrice")
    private BigDecimal originalPrice;
    @ApiModelProperty(value = "现价", name = "presentPrice")
    private BigDecimal presentPrice;
    @ApiModelProperty(value = "停用标识", name = "disableFlag")
    private Integer disableFlag;
    @ApiModelProperty(value = "商品标识（1：通用2：视界）", name = "goodsStatus")
    private Integer goodsStatus;
    @ApiModelProperty(value = "1需要上传历史病例2不需要", name = "isUpload")
    private Integer IsUpload;
    @ApiModelProperty(value = "是否生成处方(0生成1不生成)", name = "isRecipe")
    private Integer isRecipe;
    @ApiModelProperty(value = "患者主诉", name = "subjective")
    private String subjective;
    @ApiModelProperty(value = "诊断", name = "diagContent")
    private String diagContent;
    @ApiModelProperty(value = "给药途径", name = "wayNo")
    private String wayNo;
    @ApiModelProperty(value = "给药频率", name = "usageNo")
    private String usageNo;
    @ApiModelProperty(value = "满减活动满金额", name = "moneyFull")
    private BigDecimal moneyFull;
    @ApiModelProperty(value = "满减活动减金额", name = "moneyOff")
    private BigDecimal moneyOff;

    public BigDecimal getMoneyFull() {
        return moneyFull;
    }

    public void setMoneyFull(BigDecimal moneyFull) {
        this.moneyFull = moneyFull;
    }

    public BigDecimal getMoneyOff() {
        return moneyOff;
    }

    public void setMoneyOff(BigDecimal moneyOff) {
        this.moneyOff = moneyOff;
    }

    public String getWayNo() {
        return wayNo;
    }

    public void setWayNo(String wayNo) {
        this.wayNo = wayNo;
    }

    public String getUsageNo() {
        return usageNo;
    }

    public void setUsageNo(String usageNo) {
        this.usageNo = usageNo;
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public String getDiagContent() {
        return diagContent;
    }

    public void setDiagContent(String diagContent) {
        this.diagContent = diagContent;
    }

    public Integer getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Integer goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Integer getIsUpload() {
        return IsUpload;
    }

    public void setIsUpload(Integer isUpload) {
        IsUpload = isUpload;
    }

    public Integer getIsRecipe() {
        return isRecipe;
    }

    public void setIsRecipe(Integer isRecipe) {
        this.isRecipe = isRecipe;
    }

    public Long getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(Long activityNo) {
        this.activityNo = activityNo;
    }

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityDetail() {
        return activityDetail;
    }

    public void setActivityDetail(String activityDetail) {
        this.activityDetail = activityDetail == null ? null : activityDetail.trim();
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Integer getLeftNumber() {
        return leftNumber;
    }

    public void setLeftNumber(Integer leftNumber) {
        this.leftNumber = leftNumber;
    }

    public Integer getRightNumber() {
        return rightNumber;
    }

    public void setRightNumber(Integer rightNumber) {
        this.rightNumber = rightNumber;
    }

    public BigDecimal getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(BigDecimal originalPrice) {
        this.originalPrice = originalPrice;
    }

    public BigDecimal getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(BigDecimal presentPrice) {
        this.presentPrice = presentPrice;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }
}