package com.ecard.shiro;

import java.util.ArrayList;
import java.util.List;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.ecard.entity.CloudPassInfo;
import com.ecard.dao.CloudPassInfoMapper;
public class AuthRealm extends AuthorizingRealm {
	
	@Autowired
	private CloudPassInfoMapper cloudpassinfoMapper;

	//认证.登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) 
    		throws AuthenticationException {
        UsernamePasswordToken utoken=(UsernamePasswordToken) token;//获取用户输入的token
        String cloudPassNo = utoken.getUsername();
        CloudPassInfo user = 
        		cloudpassinfoMapper.selectByPrimaryKey(Long.parseLong(cloudPassNo));
        return new SimpleAuthenticationInfo(user, user.getPassword(),
        		this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
    }
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
    	CloudPassInfo user=(CloudPassInfo) principal.fromRealm(this.getClass().getName()).iterator().next();//获取session中的用户
        List<String> permissions=new ArrayList<>();
    /*    Set<Role> roles = user.getRoles();
        if(roles.size()>0) {
            for(Role role : roles) {
                Set<Authority> authoritys = role.getAuthoritys();
                if(authoritys.size()>0) {
                    for(Authority authority : authoritys) {
                        permissions.add(authority.getEvntname());
                    }
                }
            }
        }*/
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);//将权限放入shiro中.
        return info;
    }

}
