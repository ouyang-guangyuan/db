package cn.tedu.db.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.db.common.controller.BaseController;
import cn.tedu.db.common.pojo.JsonResult;
import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.pojo.SysRoleDO;
import cn.tedu.db.sys.service.ISysRoleService;

@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController{
	
	@Autowired
	ISysRoleService service;
	
	@RequestMapping("/findAll")
	public JsonResult<List<SysRoleDO>> findAllSysRole(){
		List<SysRoleDO> data=service.findAllSysRole();
		return new JsonResult<List<SysRoleDO>>(STATE_SUCCESS,MSG_SUCCESS,data);
	}
	
	@RequestMapping("/findMenu")
	public JsonResult<List<Integer>> findMenuByRoleId(Integer roleId){
		List<Integer> data=service.findMenuByRoleId(roleId);
		return new JsonResult<List<Integer>>(STATE_SUCCESS,MSG_SUCCESS,data);
	}

	@RequestMapping("/modifySysRole")
	public JsonResult<Void> modifySysRole(SysRoleDO sysRoleDO, Integer[] menuIds){
		service.modifySysRole(sysRoleDO,menuIds);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}
	
	@PostMapping("/saveSysRole")
	public JsonResult<Void> saveSysRole(SysRoleDO sysRoleDO, Integer[] menuIds){
		service.saveSysRole(sysRoleDO,menuIds);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}
	
	@PostMapping("/delete")
	public JsonResult<Void> removeSysRole(Integer roleId){
		service.removeSysRole(roleId);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}

	@RequestMapping("/findSysRole")
	public JsonResult<PageObjectVO<SysRoleDO>> findSysRole(String name, Integer currentPage){
		PageObjectVO<SysRoleDO> vo=service.findSysRole(name,currentPage);
		return new JsonResult<PageObjectVO<SysRoleDO>>(STATE_SUCCESS,MSG_SUCCESS,vo);
	}
}