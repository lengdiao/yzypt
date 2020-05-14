package com.ecard.pojo.queryResult;

import com.ecard.entity.MallOrder;

import java.util.List;

public class MallOrderQrr {
    private MallOrder mallOrder;
    private MedItemQr medItemQrs;

    public MallOrder getMallOrder() {
        return mallOrder;
    }

    public void setMallOrder(MallOrder mallOrder) {
        this.mallOrder = mallOrder;
    }

    public MedItemQr getMedItemQrs() {
        return medItemQrs;
    }

    public void setMedItemQrs(MedItemQr medItemQrs) {
        this.medItemQrs = medItemQrs;
    }
}
