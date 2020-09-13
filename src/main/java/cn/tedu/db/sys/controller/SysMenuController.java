package cn.tedu.db.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.db.common.controller.BaseController;
import cn.tedu.db.common.pojo.JsonResult;
import cn.tedu.db.sys.pojo.MenuNodeVO;
import cn.tedu.db.sys.pojo.SysMenuDO;
import cn.tedu.db.sys.service.ISysMenuService;

@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController{

	@Autowired
	ISysMenuService service;
	
	@RequestMapping("/findSysMenuById")
	public JsonResult<SysMenuDO> findSysMenuById(Integer menuId){
		SysMenuDO data=service.findSysMenuById(menuId);
		return new JsonResult<SysMenuDO>(STATE_SUCCESS,MSG_SUCCESS,data);
	}

	@PostMapping("/modifySysMenu")
	public JsonResult<Void> modifySysMenu(SysMenuDO sysMenuDO){
		service.modifySysMenu(sysMenuDO);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}
	
	@RequestMapping("/findMenuNode")
	public JsonResult<List<MenuNodeVO>> findMenuNode(){
		List<MenuNodeVO> data=service.findMenuNode();
		return new JsonResult<List<MenuNodeVO>>(STATE_SUCCESS,MSG_SUCCESS,data);
	}

	@RequestMapping("/saveSysMenu")
	public JsonResult<Void> saveSysMenu(SysMenuDO sysMenuDO){
		service.saveSysMenu(sysMenuDO);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}
	
	
	@PostMapping("/delete")
	public JsonResult<Void> removeSysMenu(Integer menuId){
		service.removeSysMenu(menuId);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}

	@RequestMapping("/findSysMenu")
	public JsonResult<List<SysMenuDO>> findSysMenu(){
		List<SysMenuDO> data=service.findSysMenu();
		return new JsonResult<List<SysMenuDO>>(STATE_SUCCESS,MSG_SUCCESS,data);
	}

}
