package com.ecard.utils;

import com.ecard.dao.RoleMapper;
import com.ecard.dao.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ecard.entity.UserRole;
/**
 * 新增用户，角色授权工具类
 * @author 蔡锐敏
 */
@Component
public class RoleUtil {
	
	private static UserRoleMapper userRoleMapper;
	private static RoleMapper roleMapper;
	
	@Autowired  
    public void setUserRoleMapper(UserRoleMapper userRoleMapper) {  
		RoleUtil.userRoleMapper = userRoleMapper;  
    }
	@Autowired  
    public void setRoleMapper(RoleMapper roleMapper) {  
		RoleUtil.roleMapper = roleMapper;  
    }
	
	/**
	 * 授权  角色与权限
	 * @param cloudPassNo
	 * @return
	 */
	public static void accredit(Long cloudPassNo,String roleName) {
		try {
			UserRole user = userRoleMapper.selectByCloudPassNo(cloudPassNo);
			if(user==null){
				//创建userRole
				UserRole userRole = new UserRole();
				userRole.setCloudPassNo(cloudPassNo);
				Long roleNo = roleMapper.selectbyRoleName(roleName);	//查询医生的roleNo
				userRole.setRoleNo(roleNo);
				userRoleMapper.insert(userRole);
			}else{
				Long roleNo = roleMapper.selectbyRoleName(roleName);	//查询医生的roleNo
				user.setRoleNo(roleNo);
				userRoleMapper.updateByPrimaryKeySelective(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}

