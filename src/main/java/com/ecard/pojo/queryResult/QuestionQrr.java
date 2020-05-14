package com.ecard.pojo.queryResult;

import com.ecard.entity.QuestionOptions;
import com.ecard.entity.QuestionTemplate;

import java.util.List;

public class QuestionQrr {
    private List<QuestionOptions> questionOptions ;
    private List<QuestionTemplate> questionTemplates;

    public List<QuestionOptions> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOptions> questionOptions) {
        this.questionOptions = questionOptions;
    }

    public List<QuestionTemplate> getQuestionTemplates() {
        return questionTemplates;
    }

    public void setQuestionTemplates(List<QuestionTemplate> questionTemplates) {
        this.questionTemplates = questionTemplates;
    }
}
