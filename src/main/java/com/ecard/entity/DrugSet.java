package com.ecard.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DrugSet {
    @ApiModelProperty(value = "编号（新增为空）", name = "drugSetNo")
    private Long drugSetNo;
    @ApiModelProperty(value = "his编码-没用", name = "hisDrugCode")
    private String hisDrugCode;
    @ApiModelProperty(value = "通用名", name = "commonName")
    private String commonName;
    @ApiModelProperty(value = "商品名", name = "itemName")
    private String itemName;
    @ApiModelProperty(value = "医保分类", name = "nhiType")
    private Integer nhiType;
    @ApiModelProperty(value = "制造商", name = "manufactory")
    private String manufactory;
    @ApiModelProperty(value = "药物大分类（1中药2西药）", name = "category")
    private String category;
    @ApiModelProperty(value = "基本药物分类", name = "essentialDrug")
    private String essentialDrug;
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
    private String doseUnit;
    @ApiModelProperty(value = "剂型", name = "form")
    private String form;
    @ApiModelProperty(value = "材质", name = "material")
    private String material;
    @ApiModelProperty(value = "成份剂量", name = "dosege")
    private BigDecimal dosege;
    @ApiModelProperty(value = "成份单位", name = "dosegeUnit")
    private String dosegeUnit;
    @ApiModelProperty(value = "没用", name = "volume")
    private BigDecimal volume;
    @ApiModelProperty(value = "没用", name = "unit")
    private String unit;
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;
    @ApiModelProperty(value = "拼音码", name = "keyword1")
    private String keyword1;
    @ApiModelProperty(value = "没用", name = "keyword2")
    private String keyword2;
    @ApiModelProperty(value = "没用", name = "keyword3")
    private String keyword3;
    @ApiModelProperty(value = "单价", name = "saleGenPrice")
    private BigDecimal saleGenPrice;
    @ApiModelProperty(value = "批准文号", name = "approvalNumber")
    private String approvalNumber;
    @ApiModelProperty(value = "有效期", name = "validity")
    private String validity;
    @ApiModelProperty(value = "Api编码", name = "apiNo")
    private String apiNo;
    @ApiModelProperty(value = "停用标识（0启用1停用）", name = "disableFlag")
    private Integer disableFlag;

    private Long createUser;

    private Date createTime;

    private Long updateUser;

    private Date updateTime;
    @ApiModelProperty(value = "1:药品 2：商品  3：药品兼商品 4:优惠商品", name = "version")
    private Integer version;
    @ApiModelProperty(value = "没用", name = "describe")
    private String describe;
    @ApiModelProperty(value = "商品标题图", name = "titleImg")
    private String titleImg;
    @ApiModelProperty(value = "商品内容图", name = "detailsImg")
    private String[] detailsImg;
    @ApiModelProperty(value = "1需要上传历史病例2不需要", name = "isUpload")
    private int isUpload;
    @ApiModelProperty(value = "是否生成处方(0生成1不生成)", name = "isRecipe")
    private int isRecipe;
    @ApiModelProperty(value = "科室", name = "departmentName")
    private List<String> departmentName;
    @ApiModelProperty(value = "是否有挂号费（0：有1：没有）", name = "isRegistration")
    private int isRegistration;

    public int getIsRegistration() {
        return isRegistration;
    }

    public void setIsRegistration(int isRegistration) {
        this.isRegistration = isRegistration;
    }

    public List<String> getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(List<String> departmentName) {
        this.departmentName = departmentName;
    }

    public int getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(int isUpload) {
        this.isUpload = isUpload;
    }

    public int getIsRecipe() {
        return isRecipe;
    }

    public void setIsRecipe(int isRecipe) {
        this.isRecipe = isRecipe;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEssentialDrug() {
        return essentialDrug;
    }

    public void setEssentialDrug(String essentialDrug) {
        this.essentialDrug = essentialDrug;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String[] getDetailsImg() {
        return detailsImg;
    }

    public void setDetailsImg(String[] detailsImg) {
        this.detailsImg = detailsImg;
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

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber == null ? null : approvalNumber.trim();
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity == null ? null : validity.trim();
    }

    public String getApiNo() {
        return apiNo;
    }

    public void setApiNo(String apiNo) {
        this.apiNo = apiNo == null ? null : apiNo.trim();
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