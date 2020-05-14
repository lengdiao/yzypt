package com.ecard.pojo.queryResult;

import com.ecard.entity.*;

import java.util.List;

public class DrugSetGoodsQrs {
    private DrugSet drugSet;
    private Goods goods;
    private Activity activity;
    private List<QuestionTemplate> questionTemplates;
    private List<QuestionOptions> questionOptions;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public DrugSet getDrugSet() {
        return drugSet;
    }

    public void setDrugSet(DrugSet drugSet) {
        this.drugSet = drugSet;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<QuestionTemplate> getQuestionTemplates() {
        return questionTemplates;
    }

    public void setQuestionTemplates(List<QuestionTemplate> questionTemplates) {
        this.questionTemplates = questionTemplates;
    }

    public List<QuestionOptions> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<QuestionOptions> questionOptions) {
        this.questionOptions = questionOptions;
    }
}
