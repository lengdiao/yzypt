package com.ecard.pojo.queryResult;

import com.ecard.entity.MedRecord;
import com.ecard.entity.MrPicture;

public class MedRecordPtInfoQr {
    private MedRecord medRecord;
    private PtInfoQr ptInfoQr;
    private MrPicture[] mrPictures;

    public MrPicture[] getMrPictures() {
        return mrPictures;
    }

    public void setMrPictures(MrPicture[] mrPictures) {
        this.mrPictures = mrPictures;
    }

    public MedRecord getMedRecord() {
        return medRecord;
    }

    public void setMedRecord(MedRecord medRecord) {
        this.medRecord = medRecord;
    }

    public PtInfoQr getPtInfoQr() {
        return ptInfoQr;
    }

    public void setPtInfoQr(PtInfoQr ptInfoQr) {
        this.ptInfoQr = ptInfoQr;
    }
}
