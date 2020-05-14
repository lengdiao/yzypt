package com.ecard.pojo.queryResult;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

public class GoodsQr {
    @ApiModelProperty(value = "药品编号（新增为空）", name = "drugSetNo")
    private Long drugSetNo;
    @ApiModelProperty(value = "HIS系统内药品编码", name = "hisDrugCode")
    private String hisDrugCode;
    @ApiModelProperty(value = "通用名称", name = "commonName")
    private String commonName;
    @ApiModelProperty(value = "商品名", name = "itemName")
    private String itemName;
    @ApiModelProperty(value = "制造商", name = "manufactory")
    private String manufactory;
    @ApiModelProperty(value = "药物大分类", name = "category")
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
    @ApiModelProperty(value = "规格（按体重）", name = "dosege")
    private BigDecimal dosege;
    @ApiModelProperty(value = "成份单位", name = "dosegeUnit")
    private String dosegeUnit;
    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;
    @ApiModelProperty(value = "拼音码", name = "keyword1")
    private String keyword1;
    @ApiModelProperty(value = "销售单位自费价", name = "saleGenPrice")
    private BigDecimal saleGenPrice;
    @ApiModelProperty(value = "批准文号", name = "approvalNumber")
    private String approvalNumber;
    @ApiModelProperty(value = "有效期", name = "validity")
    private String validity;
    @ApiModelProperty(value = "Api编码", name = "apiNo")
    private String apiNo;
    @ApiModelProperty(value = "停用标志", name = "disableFlag")
    private Integer disableFlag;
    @ApiModelProperty(value = "药品描述", name = "describe")
    private String describe;
    @ApiModelProperty(value = "患者主诉", name = "subjective")
    private String subjective;
    @ApiModelProperty(value = "诊断内容", name = "diagContent")
    private String diagContent;
    @ApiModelProperty(value = "给药途径", name = "wayNo")
    private String wayNo;
    @ApiModelProperty(value = "给药频率", name = "usageNo")
    private String usageNo;
    @ApiModelProperty(value = "剂型", name = "form")
    private String form;
    @ApiModelProperty(value = "材质", name = "material")
    private String material;
    @ApiModelProperty(value = "1:有问卷2:没有问卷", name = "goodsStatus")
    private int goodsStatus;
    @ApiModelProperty(value = "诊断内容中文", name = "diagContentName")
    private String diagContentName;
    @ApiModelProperty(value = "1药品2商品3共同", name = "version")
    private int version;

    private int isUpload;

    private int isRecipe;

    private int status;

    @ApiModelProperty(value = "是否有挂号费（0：有1：没有）", name = "isRegistration")
    private int isRegistration;

    public int getIsRegistration() {
        return isRegistration;
    }

    public void setIsRegistration(int isRegistration) {
        this.isRegistration = isRegistration;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    private List<String> departmentName;

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


    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDiagContentName() {
        return diagContentName;
    }

    public void setDiagContentName(String diagContentName) {
        this.diagContentName = diagContentName;
    }

    public int getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(int goodsStatus) {
        this.goodsStatus = goodsStatus;
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

    public String getManufactory() {
        return manufactory;
    }

    public void setManufactory(String manufactory) {
        this.manufactory = manufactory;
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

    public String getDoseUnit() {
        return doseUnit;
    }

    public void setDoseUnit(String doseUnit) {
        this.doseUnit = doseUnit;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
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
        this.approvalNumber = approvalNumber;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getApiNo() {
        return apiNo;
    }

    public void setApiNo(String apiNo) {
        this.apiNo = apiNo;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getSubjective() {
        return subjective;
    }

    public void setSubjective(String subjective) {
        this.subjective = subjective;
    }

    public String getDiagContent() {
        return diagContent;
    }

    public void setDiagContent(String diagContent) {
        this.diagContent = diagContent;
    }

    public String getWayNo() {
        return wayNo;
    }

    public void setWayNo(String wayNo) {
        this.wayNo = wayNo;
    }

    public String getUsageNo() {
        return usageNo;
    }

    public void setUsageNo(String usageNo) {
        this.usageNo = usageNo;
    }
}
