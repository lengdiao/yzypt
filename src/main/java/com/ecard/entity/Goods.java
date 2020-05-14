package com.ecard.entity;

import java.math.BigDecimal;
import java.util.List;

public class Goods {
    private Long goodsNo;

    private String goodsName;

    private BigDecimal price;

    private String describe;

    private String remark;

    private String specification;

    private String subjective;

    private String diagContent;

    private String wayNo;

    private String usageNo;

    private int goodsStatus;

    private String diagContentName;

    private int isUpload;

    private int isRecipe;

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(int isUpload) {
        this.isUpload = isUpload;
    }

    public int getIsRecipe() {
        return isRecipe;
    }

    public void setIsRecipe(int isRecipe) {
        this.isRecipe = isRecipe;
    }

    public String getDiagContentName() {
        return diagContentName;
    }

    public void setDiagContentName(String diagContentName) {
        this.diagContentName = diagContentName;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getUsageNo() {
        return usageNo;
    }

    public void setUsageNo(String usageNo) {
        this.usageNo = usageNo;
    }

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe == null ? null : describe.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification == null ? null : specification.trim();
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective == null ? null : subjective.trim();
    }

    public String getDiagContent() {
        return diagContent;
    }

    public void setDiagContent(String diagContent) {
        this.diagContent = diagContent == null ? null : diagContent.trim();
    }

    public String getWayNo() {
        return wayNo;
    }

    public void setWayNo(String wayNo) {
        this.wayNo = wayNo == null ? null : wayNo.trim();
    }
}