package com.ecard.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class DrInfo {
    private Long drNo;

    private Long cloudPassNo;

    private String hospital;

    private Long divNo;

    private String province;

    private String chiefNo;

    private Integer age;

    private String address;

    private String practiceProfile;

    private String signature;

    private String introducer;

    private String company;

    private String title;

    private String drTitleCert;

    private String drPracticeRegCert;

    private String consultingHour;

    private Long leaderDrNo;

    private Integer disableFlag;

    private String createUser;

    private Date createTime;

    private String updateUser;

    private Date updateTime;

    private Integer version;

    private int type;

    private String city;

    private int platform;

    private String headImg;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getDrNo() {
        return drNo;
    }

    public void setDrNo(Long drNo) {
        this.drNo = drNo;
    }

    public Long getCloudPassNo() {
        return cloudPassNo;
    }

    public void setCloudPassNo(Long cloudPassNo) {
        this.cloudPassNo = cloudPassNo;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital == null ? null : hospital.trim();
    }

    public Long getDivNo() {
        return divNo;
    }

    public void setDivNo(Long divNo) {
        this.divNo = divNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getChiefNo() {
        return chiefNo;
    }

    public void setChiefNo(String chiefNo) {
        this.chiefNo = chiefNo == null ? null : chiefNo.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPracticeProfile() {
        return practiceProfile;
    }

    public void setPracticeProfile(String practiceProfile) {
        this.practiceProfile = practiceProfile == null ? null : practiceProfile.trim();
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getIntroducer() {
        return introducer;
    }

    public void setIntroducer(String introducer) {
        this.introducer = introducer == null ? null : introducer.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDrTitleCert() {
        return drTitleCert;
    }

    public void setDrTitleCert(String drTitleCert) {
        this.drTitleCert = drTitleCert == null ? null : drTitleCert.trim();
    }

    public String getDrPracticeRegCert() {
        return drPracticeRegCert;
    }

    public void setDrPracticeRegCert(String drPracticeRegCert) {
        this.drPracticeRegCert = drPracticeRegCert == null ? null : drPracticeRegCert.trim();
    }

    public String getConsultingHour() {
        return consultingHour;
    }

    public void setConsultingHour(String consultingHour) {
        this.consultingHour = consultingHour == null ? null : consultingHour.trim();
    }

    public Long getLeaderDrNo() {
        return leaderDrNo;
    }

    public void setLeaderDrNo(Long leaderDrNo) {
        this.leaderDrNo = leaderDrNo;
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