package com.ecard.entity;

import io.swagger.annotations.ApiModelProperty;

public class QuestionOptions {
    private Long id;
    @ApiModelProperty(value = "问卷模板编号", name = "templateNo")
    private Long templateNo;
    @ApiModelProperty(value = "问卷问题编号", name = "questionNo")
    private Long questionNo;
    @ApiModelProperty(value = "问题选项", name = "options")
    private String options;
    @ApiModelProperty(value = "停用标识（0：停用、1：启用）", name = "disableFlag")
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

    public Long getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(Long questionNo) {
        this.questionNo = questionNo;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options == null ? null : options.trim();
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }
}