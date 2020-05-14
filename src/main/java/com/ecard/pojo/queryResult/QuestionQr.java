package com.ecard.pojo.queryResult;

import com.ecard.entity.QuestionOptions;
import com.ecard.entity.QuestionTemplate;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class QuestionQr {

    private  QuestionTemplate questionTemplate;
    private List<QuestionOptions> questionOptions ;

    public QuestionTemplate getQuestionTemplate() {
        return questionTemplate;
    }

    public void setQuestionTemplate(QuestionTemplate questionTemplate) {
        this.questionTemplate = questionTemplate;
    }

    public List<QuestionOptions> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOptions> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
