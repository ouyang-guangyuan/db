package cn.tedu.db.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import cn.tedu.db.sys.mapper.SysMenuMapper;
import cn.tedu.db.sys.mapper.SysRoleMapper;
import cn.tedu.db.sys.mapper.SysUserMapper;
import cn.tedu.db.sys.pojo.SysUserDO;

public class ShiroUserRealm extends AuthorizingRealm{
	
	@Autowired
	SysUserMapper userMapper;
	@Autowired
	SysRoleMapper roleMapper;
	@Autowired
	SysMenuMapper menuMapper;
	
	

	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
        HashedCredentialsMatcher cMatcher=
        new HashedCredentialsMatcher();
        //设置加密算法
        cMatcher.setHashAlgorithmName("MD5");
        //设置加密次数
        cMatcher.setHashIterations(5);
        super.setCredentialsMatcher(cMatcher);
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
	    //1. 获取用户名(用户页面输入)
        UsernamePasswordToken upToken=(UsernamePasswordToken)token;
        String username=upToken.getUsername();
        //2. 基于用户名查询用户信息
        SysUserDO user=userMapper.getUserByUsername(username);
        //3. 判断用户是否存在
        if(user==null) {
            throw new UnknownAccountException();
        }
        //4.判断用户是否被禁用
        if(user.getValid()==0) {
            throw new LockedAccountException();
        }
        //5.封装用户信息
        ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());
        SimpleAuthenticationInfo info=
                new SimpleAuthenticationInfo(
                                user, // principal身份
                                user.getPassword(), // hashedCredentials 加密后的密码
                                credentialsSalt, // credentialsSalt 盐值 
                                getName()); //realmName
        //6.返回封装结果
        // 返回值会传递给认证管理器
        return info;
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
	    //1.获取登录用户信息，例如用户id
	    SysUserDO user=(SysUserDO)principals.getPrimaryPrincipal();
	    Integer userId=user.getId();
	    //2.基于用户id获取用户拥有的角色(sys_user_roles)
	    List<Integer> roleIds=userMapper.listRoleIdByUserId(userId);
	    if(roleIds==null||roleIds.size()==0) { // 用户未绑定角色
	        throw new AuthorizationException();
	    }
	    //3.基于角色id获取菜单id(sys_role_menus)
	    Integer[] array={};
	    List<Integer> menuIds=
	            roleMapper.listMenuIdByRoleId(roleIds.toArray(array));
	    if(menuIds==null||menuIds.size()==0) {// 未绑定菜单
	        throw new AuthorizationException();
	    }
	    //4.基于菜单id获取权限标识(sys_menus)
	    List<String> permissions=
	            menuMapper.listPermissions(menuIds.toArray(array));
	    //5.对权限标识信息进行封装并返回
	    Set<String> set=new HashSet<String>();
	    for(String per:permissions){
	        if(!StringUtils.isEmpty(per)){
	            set.add(per);
	        }
	    }
	    SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
	    info.setStringPermissions(set);
	    return info;//返回给授权管理器
	}

}
