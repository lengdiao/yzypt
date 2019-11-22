package com.ecard.entity;

public class CommonMasterKey {
    private Integer bigCategoryNo;

    private String smallCategoryNo;

    public Integer getBigCategoryNo() {
        return bigCategoryNo;
    }

    public void setBigCategoryNo(Integer bigCategoryNo) {
        this.bigCategoryNo = bigCategoryNo;
    }

    public String getSmallCategoryNo() {
        return smallCategoryNo;
    }

    public void setSmallCategoryNo(String smallCategoryNo) {
        this.smallCategoryNo = smallCategoryNo == null ? null : smallCategoryNo.trim();
    }
}