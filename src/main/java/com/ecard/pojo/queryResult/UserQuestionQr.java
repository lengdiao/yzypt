package com.ecard.pojo.queryResult;

import com.ecard.entity.UserQuestion;

import java.util.List;

public class UserQuestionQr {
    private List<UserQuestion> userQuestion;

    public List<UserQuestion> getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(List<UserQuestion> userQuestion) {
        this.userQuestion = userQuestion;
    }
}
