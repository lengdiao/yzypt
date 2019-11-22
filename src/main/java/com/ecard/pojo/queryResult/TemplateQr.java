package com.ecard.pojo.queryResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "TemplateQr", description = "模板对象")
public class TemplateQr {
    @ApiModelProperty(value = "模板编号（编辑药品时传medOrderNo）", name = "templateId")
    private Long templateId;
    @ApiModelProperty(value = "医生编号", name = "drNo")
    private Long drNo;
    @ApiModelProperty(value = "模板名称", name = "templateName")
    private String templateName;
    @ApiModelProperty(value = "模板类型（按照药品大分类\\r\\n1:西药\\r\\n2：中成药\\r\\n3:草药4:模板）", name = "templateType")
    private Integer templateType;
    @ApiModelProperty(value = "药品集合", name = "list")
    private List<TemplateDrugSetQr> list;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public List<TemplateDrugSetQr> getList() {
        return list;
    }

    public void setList(List<TemplateDrugSetQr> list) {
        this.list = list;
    }
}
