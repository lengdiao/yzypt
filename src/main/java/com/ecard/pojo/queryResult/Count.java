package com.ecard.pojo.queryResult;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Count {

    private int count;
    private BigDecimal totalAmount;
    private List<CountQr> countQrs;

    public List<CountQr> getCountQrs() {
        return countQrs;
    }

    public void setCountQrs(List<CountQr> countQrs) {
        this.countQrs = countQrs;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
