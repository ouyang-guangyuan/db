package cn.tedu.db.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.db.common.controller.BaseController;
import cn.tedu.db.common.pojo.JsonResult;
import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.pojo.SysUserDO;
import cn.tedu.db.sys.service.ISysUserService;

@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController{
	
	@Autowired
	ISysUserService service;
	
	@PostMapping("/login")
	public JsonResult<Void> login(String username,String password){
	    //1. 获取Subject对象
	    Subject subject=SecurityUtils.getSubject();
	    //2. 通过Subject提交用户信息，交给Shiro框架进行认证操作
	    //2.1 对用户信息进行封装
	    UsernamePasswordToken token=new UsernamePasswordToken(username, password);
	    //2.2 对用户信息进行身份认证
	    subject.login(token);
	    //3 返回登录结果
	    return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}
	
	@RequestMapping("/saveSysUser")
	public JsonResult<Void> saveSysUser(SysUserDO sysUserDO, Integer[] roleIds){
		service.saveSysUser(sysUserDO,roleIds);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}

	@RequestMapping("/findSysUser")
	public JsonResult<PageObjectVO<SysUserDO>> findSysUser(String username,Integer currentPage){
		PageObjectVO<SysUserDO> data=service.findSysUser(username,currentPage);
		return new JsonResult<PageObjectVO<SysUserDO>>(STATE_SUCCESS,MSG_SUCCESS,data);
	}

}
