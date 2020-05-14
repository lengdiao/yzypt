package com.ecard.entity;

import java.math.BigDecimal;

public class Postage {
    private Long commonNo;

    private Long drugSetNo;

    private String condition;

    private BigDecimal postage;

    public Long getCommonNo() {
        return commonNo;
    }

    public void setCommonNo(Long commonNo) {
        this.commonNo = commonNo;
    }

    public Long getDrugSetNo() {
        return drugSetNo;
    }

    public void setDrugSetNo(Long drugSetNo) {
        this.drugSetNo = drugSetNo;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition == null ? null : condition.trim();
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }
}