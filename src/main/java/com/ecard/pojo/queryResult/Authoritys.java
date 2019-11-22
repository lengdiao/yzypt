package com.ecard.pojo.queryResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Authoritys对象", description = "权限对象")
public class Authoritys {
	@ApiModelProperty(value = "权限编号", name = "authorityNo")
	private Long authorityNo;
	@ApiModelProperty(value = "权限名字", name = "authorityName")
	private String authorityName;
	@ApiModelProperty(value = "图片", name = "img")
	private String img;
	@ApiModelProperty(value = "地址", name = "url")
	private String url;

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getAuthorityNo() {
		return authorityNo;
	}

	public void setAuthorityNo(Long authorityNo) {
		this.authorityNo = authorityNo;
	}

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

}
