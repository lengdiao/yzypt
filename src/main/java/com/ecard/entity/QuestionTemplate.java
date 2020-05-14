package com.ecard.entity;

import io.swagger.annotations.ApiModelProperty;

public class QuestionTemplate {
    private Long id;

    @ApiModelProperty(value = "模板编号", name = "templateNo")
    private Long templateNo;
    @ApiModelProperty(value = "商品编号", name = "goodsNo")
    private Long goodsNo;
    @ApiModelProperty(value = "问题编号", name = "questionNo")
    private Long questionNo;
    @ApiModelProperty(value = "问卷问题", name = "questionText")
    private String questionText;
    @ApiModelProperty(value = "问题类型（1文本框、2单选、3多选）", name = "questionType")
    private Integer questionType;
    @ApiModelProperty(value = "是否必填（1必填、2非必填）", name = "isNull")
    private Integer isNull;
    @ApiModelProperty(value = "停用标识（0启用、1停用）", name = "disableFlag")
    private Integer disableFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTemplateNo() {
        return templateNo;
    }

    public void setTemplateNo(Long templateNo) {
        this.templateNo = templateNo;
    }

    public Long getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(Long goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Long getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Long questionNo) {
        this.questionNo = questionNo;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText == null ? null : questionText.trim();
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public Integer getIsNull() {
        return isNull;
    }

    public void setIsNull(Integer isNull) {
        this.isNull = isNull;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }
}