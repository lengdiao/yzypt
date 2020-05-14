package com.ecard.entity;

public class TemporaryPtOpen {
    private Long id;

    private Long ptNo;

    private String openId;

    private Long drNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPtNo() {
        return ptNo;
    }

    public void setPtNo(Long ptNo) {
        this.ptNo = ptNo;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }
}