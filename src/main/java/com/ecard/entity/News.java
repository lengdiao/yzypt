package com.ecard.entity;

import java.util.List;

public class News {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String ArticleCount;
    private List<Articles> Articles;

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(String articleCount) {
        ArticleCount = articleCount;
    }

    public List<com.ecard.entity.Articles> getArticles() {
        return Articles;
    }

    public void setArticles(List<com.ecard.entity.Articles> articles) {
        Articles = articles;
    }
}
