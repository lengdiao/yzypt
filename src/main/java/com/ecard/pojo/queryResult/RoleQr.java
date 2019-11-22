package com.ecard.pojo.queryResult;

import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="Role对象",description="角色对象")
public class RoleQr {
	@ApiModelProperty(value="角色编号",name="roleNo")
	private Long roleNo;
	@ApiModelProperty(value="角色名",name="roleName")
	private String roleName;
	@ApiModelProperty(value="权限list",name="authoritys")
	private List<Authoritys> authoritys;
	@ApiModelProperty(value="全部权限list",name="allAuthoritys")
	private List<Authoritys> allAuthoritys;
	public Long getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(Long roleNo) {
		this.roleNo = roleNo;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Authoritys> getAuthoritys() {
		return authoritys;
	}
	public void setAuthoritys(List<Authoritys> authoritys) {
		this.authoritys = authoritys;
	}
	public List<Authoritys> getAllAuthoritys() {
		return allAuthoritys;
	}
	public void setAllAuthoritys(List<Authoritys> allAuthoritys) {
		this.allAuthoritys = allAuthoritys;
	}
	
	
}
