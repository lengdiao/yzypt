package com.ecard.pojo.queryResult;

import com.ecard.entity.*;
import com.ngari.his.recipe.mode.RecipeAuditAiReasonReqTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MallOrderQr {
    private MallOrder mallOrder;
    private MedOrder medOrder;
    private MedRecord medRecord;
    private List<MedItemQr> medItemQr;
    private PtInfoQr ptInfoQr;
    private MrPicture[] mrPictures;
    private ReceiptAddress receiptAddress;
    private DrInfoQr drInfoQr;
    private BigDecimal postage;
    private String dis1Name;
    private String dis2Name;

    public String getDis1Name() {
        return dis1Name;
    }

    public void setDis1Name(String dis1Name) {
        this.dis1Name = dis1Name;
    }

    public String getDis2Name() {
        return dis2Name;
    }

    public void setDis2Name(String dis2Name) {
        this.dis2Name = dis2Name;
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public DrInfoQr getDrInfoQr() {
        return drInfoQr;
    }

    public void setDrInfoQr(DrInfoQr drInfoQr) {
        this.drInfoQr = drInfoQr;
    }

    public ReceiptAddress getReceiptAddress() {
        return receiptAddress;
    }

    public void setReceiptAddress(ReceiptAddress receiptAddress) {
        this.receiptAddress = receiptAddress;
    }

    public MrPicture[] getMrPictures() {
        return mrPictures;
    }

    public void setMrPictures(MrPicture[] mrPictures) {
        this.mrPictures = mrPictures;
    }

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