package com.ecard.entity;

public class UserQuestion {
    private Long id;

    private Long ptNo;

    private Long templateNo;

    private Long questionNo;

    private String options;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPtNo() {
        return ptNo;
    }

    public void setPtNo(Long ptNo) {
        this.ptNo = ptNo;
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
}