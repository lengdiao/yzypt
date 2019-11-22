package com.ecard.pojo.queryResult;

import com.ecard.entity.CloudPassInfo;
import com.ecard.entity.MallOrder;
import com.ecard.entity.MedOrder;
import com.ecard.entity.MedRecord;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MallOrderQr {
    private MallOrder mallOrder;
    private MedOrder medOrder;
    private MedRecord medRecord;
    private List<MedItemQr> medItemQr;
    private PtInfoQr ptInfoQr;

    public MallOrder getMallOrder() {
        return mallOrder;
    }

    public void setMallOrder(MallOrder mallOrder) {
        this.mallOrder = mallOrder;
    }

    public MedOrder getMedOrder() {
        return medOrder;
    }

    public void setMedOrder(MedOrder medOrder) {
        this.medOrder = medOrder;
    }

    public MedRecord getMedRecord() {
        return medRecord;
    }

    public void setMedRecord(MedRecord medRecord) {
        this.medRecord = medRecord;
    }

    public List<MedItemQr> getMedItemQr() {
        return medItemQr;
    }

    public void setMedItemQr(List<MedItemQr> medItemQr) {
        this.medItemQr = medItemQr;
    }

    public PtInfoQr getPtInfoQr() {
        return ptInfoQr;
    }

    public void setPtInfoQr(PtInfoQr ptInfoQr) {
        this.ptInfoQr = ptInfoQr;
    }
}
