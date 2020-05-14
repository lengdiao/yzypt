package com.ecard.entity;

import java.util.Date;

public class DrDisRelation {
    private Long id;

    private Long drNo;

    private Long disNo;

    private Integer disableFlag;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Long getDisNo() {
        return disNo;
    }

    public void setDisNo(Long disNo) {
        this.disNo = disNo;
    }

    public Integer getDisableFlag() {
        return disableFlag;
    }

    public void setDisableFlag(Integer disableFlag) {
        this.disableFlag = disableFlag;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}