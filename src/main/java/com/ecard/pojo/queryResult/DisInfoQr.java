package com.ecard.pojo.queryResult;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="DisInfo对象",description="代表对象")
public class DisInfoQr {
	@ApiModelProperty(value="代表云通行编号",name="cloudPassNo")
	private Long cloudPassNo;
	@ApiModelProperty(value="代表编号",name="disNo")
	private Long disNo;
	@ApiModelProperty(value="名称",name="name")
	private String name;
	@ApiModelProperty(value="证件号",name="idNo")
	private String idNo;
	@ApiModelProperty(value="手机号",name="phone")
	private String phone;
	@ApiModelProperty(value="联系人姓名",name="disName")
	private String disName;
	@ApiModelProperty(value="代表所属区域",name="address")
	private String address;
	@ApiModelProperty(value="邮箱可不填",name="email")
	private String email;
	@ApiModelProperty(value="账号种类1个人2公司",name="cloudPassKind")
	private String cloudPassKind;	
	@ApiModelProperty(value="上级代表云通行证账号",name="disLeader")
	private Long disLeader;	
	@ApiModelProperty(value="上级代表名称",name="disLeaderName")
	private String disLeaderName;
	@ApiModelProperty(value="上级代表等级名称",name="disLevelName")
	private String disLevelName;
	@ApiModelProperty(value="代表等级1，首席2.执行",name="disLevel")
	private String disLevel;		
	@ApiModelProperty(value="启停标识0启1停",name="disableFlag")
	private Integer disableFlag;
	@ApiModelProperty(value="创建时间",name="createTime")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date createTime;


	public Long getCloudPassNo() {
		return cloudPassNo;
	}
	public void setCloudPassNo(Long cloudPassNo) {
		this.cloudPassNo = cloudPassNo;
	}
	public Long getDisNo() {
		return disNo;
	}
	public void setDisNo(Long disNo) {
		this.disNo = disNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDisName() {
		return disName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCloudPassKind() {
		return cloudPassKind;
	}
	public void setCloudPassKind(String cloudPassKind) {
		this.cloudPassKind = cloudPassKind;
	}
	public Long getDisLeader() {
		return disLeader;
	}
	public void setDisLeader(Long disLeader) {
		this.disLeader = disLeader;
	}
	public String getDisLeaderName() {
		return disLeaderName;
	}
	public void setDisLeaderName(String disLeaderName) {
		this.disLeaderName = disLeaderName;
	}
	public String getDisLevelName() {
		return disLevelName;
	}
	public void setDisLevelName(String disLevelName) {
		this.disLevelName = disLevelName;
	}
	public String getDisLevel() {
		return disLevel;
	}
	public void setDisLevel(String disLevel) {
		this.disLevel = disLevel;
	}
	public Integer getDisableFlag() {
		return disableFlag;
	}
	public void setDisableFlag(Integer disableFlag) {
		this.disableFlag = disableFlag;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
}
