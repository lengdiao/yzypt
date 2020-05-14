package com.ecard.entity;

import java.util.Date;

public class ImageMessage {
    private String FromUserName;
    private String ToUserName;
    private String CreateTime;
    private Image Image;
    private String MsgType;

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public com.ecard.entity.Image getImage() {
        return Image;
    }

    public void setImage(com.ecard.entity.Image image) {
        Image = image;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }
}
