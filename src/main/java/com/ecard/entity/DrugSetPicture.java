package com.ecard.entity;

public class DrugSetPicture {
    private Long id;

    private Long goodsNo;

    private String titleImg;

    private Integer number;

    private String detailsImg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg == null ? null : titleImg.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDetailsImg() {
        return detailsImg;
    }

    public void setDetailsImg(String detailsImg) {
        this.detailsImg = detailsImg == null ? null : detailsImg.trim();
    }
}