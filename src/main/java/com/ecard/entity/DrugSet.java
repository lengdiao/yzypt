package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class DrugSet {
    private Long drugSetNo;

    private String hisDrugCode;

    private String commonName;

    private String itemName;

    private Integer nhiType;

    private String manufactory;

    private Integer category;

    private Integer essentialDrug;

    private String ctrlDrug;

    private String highAlert;

    private BigDecimal dividend;

    private String saleUnit;

    private BigDecimal divisor;

    private String doseUnit;

    private Integer form;

    private Integer material;

    private BigDecimal dosege;

    private String dosegeUnit;

    private BigDecimal volume;

    private String unit;

    private String remark;

    private String keyword1;

    private String keyword2;

    private String keyword3;

    private BigDecimal saleGenPrice;

    private Integer disableFlag;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;

    private Integer version;

    public Long getDrugSetNo() {
        return drugSetNo;
    }

    public void setDrugSetNo(Long drugSetNo) {
        this.drugSetNo = drugSetNo;
    }

    public String getHisDrugCode() {
        return hisDrugCode;
    }

    public void setHisDrugCode(String hisDrugCode) {
        this.hisDrugCode = hisDrugCode == null ? null : hisDrugCode.trim();
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName == null ? null : commonName.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Integer getNhiType() {
        return nhiType;
    }

    public void setNhiType(Integer nhiType) {
        this.nhiType = nhiType;
    }

    public String getManufactory() {
        return manufactory;
    }

    public void setManufactory(String manufactory) {
        this.manufactory = manufactory == null ? null : manufactory.trim();
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getEssentialDrug() {
        return essentialDrug;
    }

    public void setEssentialDrug(Integer essentialDrug) {
        this.essentialDrug = essentialDrug;
    }

    public String getCtrlDrug() {
        return ctrlDrug;
    }

    public void setCtrlDrug(String ctrlDrug) {
        this.ctrlDrug = ctrlDrug == null ? null : ctrlDrug.trim();
    }

    public String getHighAlert() {
        return highAlert;
    }

    public void setHighAlert(String highAlert) {
        this.highAlert = highAlert == null ? null : highAlert.trim();
    }

    public BigDecimal getDividend() {
        return dividend;
    }

    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }

    public String getSaleUnit() {
        return saleUnit;
    }

    public void setSaleUnit(String saleUnit) {
        this.saleUnit = saleUnit == null ? null : saleUnit.trim();
    }

    public BigDecimal getDivisor() {
        return divisor;
    }

    public void setDivisor(BigDecimal divisor) {
        this.divisor = divisor;
    }

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit == null ? null : doseUnit.trim();
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public Integer getMaterial() {
        return material;
    }

    public void setMaterial(Integer material) {
        this.material = material;
    }

    public BigDecimal getDosege() {
        return dosege;
    }

    public void setDosege(BigDecimal dosege) {
        this.dosege = dosege;
    }

    public String getDosegeUnit() {
        return dosegeUnit;
    }

    public void setDosegeUnit(String dosegeUnit) {
        this.dosegeUnit = dosegeUnit == null ? null : dosegeUnit.trim();
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1 == null ? null : keyword1.trim();
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2 == null ? null : keyword2.trim();
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3 == null ? null : keyword3.trim();
    }

    public BigDecimal getSaleGenPrice() {
        return saleGenPrice;
    }

    public void setSaleGenPrice(BigDecimal saleGenPrice) {
        this.saleGenPrice = saleGenPrice;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}