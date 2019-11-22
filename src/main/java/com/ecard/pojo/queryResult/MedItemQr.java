package com.ecard.pojo.queryResult;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class MedItemQr {
    private Long medItemNo;

    private Long medOrderNo;

    private Long drugNo;

    private Double number;

    private Integer execDay;

    private String wayNo;

    private String execWhen;

    private String execAim;

    private BigDecimal dayDose;

    private BigDecimal dose;

    private String doseUnit;

    private String usageNo;

    @ApiModelProperty(value = "药品编号", name = "drugSetNo")
    private Long drugSetNo;
    @ApiModelProperty(value = "HIS系统内药品编码", name = "hisDrugCode")
    private String hisDrugCode;
    @ApiModelProperty(value = "通用名称", name = "commonName")
    private String commonName;
    @ApiModelProperty(value = "商品名", name = "itemName")
    private String itemName;
    @ApiModelProperty(value = "医保分类", name = "nhiType")
    private Integer nhiType;
    @ApiModelProperty(value = "制造商", name = "manufactory")
    private String manufactory;
    @ApiModelProperty(value = "药物大分类", name = "category")
    private Integer category;
    @ApiModelProperty(value = "基本药物分类", name = "essentialDrug")
    private Integer essentialDrug;
    @ApiModelProperty(value = "管制药品", name = "ctrlDrug")
    private String ctrlDrug;
    @ApiModelProperty(value = "高警讯药品", name = "highAlert")
    private String highAlert;
    @ApiModelProperty(value = "被除数 (销售量)", name = "dividend")
    private BigDecimal dividend;
    @ApiModelProperty(value = "销售单位", name = "saleUnit")
    private String saleUnit;
    @ApiModelProperty(value = "除数 (次剂量)", name = "divisor")
    private BigDecimal divisor;
    @ApiModelProperty(value = "次剂量单位", name = "doseUnit")
    private String doseUnit1;
    @ApiModelProperty(value = "剂型", name = "form")
    private Integer form;
    @ApiModelProperty(value = "材质", name = "material")
    private Integer material;
    @ApiModelProperty(value = "成份剂量    规格（按体重）", name = "dosege")
    private BigDecimal dosege;
    @ApiModelProperty(value = "成份单位", name = "dosegeUnit")
    private String dosegeUnit;
    @ApiModelProperty(value = "体积   规格（按体积）", name = "volume")
    private BigDecimal volume;
    @ApiModelProperty(value = "体积单位", name = "unit")
    private String unit;

    public Long getMedItemNo() {
        return medItemNo;
    }

    public void setMedItemNo(Long medItemNo) {
        this.medItemNo = medItemNo;
    }

    public Long getMedOrderNo() {
        return medOrderNo;
    }

    public void setMedOrderNo(Long medOrderNo) {
        this.medOrderNo = medOrderNo;
    }

    public Long getDrugNo() {
        return drugNo;
    }

    public void setDrugNo(Long drugNo) {
        this.drugNo = drugNo;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Integer getExecDay() {
        return execDay;
    }

    public void setExecDay(Integer execDay) {
        this.execDay = execDay;
    }

    public String getWayNo() {
        return wayNo;
    }

    public void setWayNo(String wayNo) {
        this.wayNo = wayNo;
    }

    public String getExecWhen() {
        return execWhen;
    }

    public void setExecWhen(String execWhen) {
        this.execWhen = execWhen;
    }

    public String getExecAim() {
        return execAim;
    }

    public void setExecAim(String execAim) {
        this.execAim = execAim;
    }

    public BigDecimal getDayDose() {
        return dayDose;
    }

    public void setDayDose(BigDecimal dayDose) {
        this.dayDose = dayDose;
    }

    public BigDecimal getDose() {
        return dose;
    }

    public void setDose(BigDecimal dose) {
        this.dose = dose;
    }

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit;
    }

    public String getDoseUnit1() {
        return doseUnit1;
    }

    public void setDoseUnit1(String doseUnit1) {
        this.doseUnit1 = doseUnit1;
    }

    public String getUsageNo() {
        return usageNo;
    }

    public void setUsageNo(String usageNo) {
        this.usageNo = usageNo;
    }


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
        this.hisDrugCode = hisDrugCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
        this.manufactory = manufactory;
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
        this.ctrlDrug = ctrlDrug;
    }

    public String getHighAlert() {
        return highAlert;
    }

    public void setHighAlert(String highAlert) {
        this.highAlert = highAlert;
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
        this.saleUnit = saleUnit;
    }

    public BigDecimal getDivisor() {
        return divisor;
    }

    public void setDivisor(BigDecimal divisor) {
        this.divisor = divisor;
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
        this.dosegeUnit = dosegeUnit;
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
        this.unit = unit;
    }
}
