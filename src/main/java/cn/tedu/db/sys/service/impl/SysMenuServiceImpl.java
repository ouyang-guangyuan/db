package cn.tedu.db.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.tedu.db.common.ex.ServiceException;
import cn.tedu.db.sys.mapper.SysMenuMapper;
import cn.tedu.db.sys.pojo.MenuNodeVO;
import cn.tedu.db.sys.pojo.SysMenuDO;
import cn.tedu.db.sys.service.ISysMenuService;
import cn.tedu.db.sys.service.ex.DeleteException;
import cn.tedu.db.sys.service.ex.EmptyArgumentException;
import cn.tedu.db.sys.service.ex.EmptyIdException;
import cn.tedu.db.sys.service.ex.HasSubMenuException;
import cn.tedu.db.sys.service.ex.InsertException;
import cn.tedu.db.sys.service.ex.ParentNodeNotFoundException;
import cn.tedu.db.sys.service.ex.RecordNotFoundException;
import cn.tedu.db.sys.service.ex.UpdateException;

@Service
public class SysMenuServiceImpl implements ISysMenuService {
	
	@Autowired
	SysMenuMapper mapper;
	
	public int findMenuCount(Integer[] menuIds) throws 
	EmptyIdException{
		// 判断menuIds是否为null或者长度为0
		if(menuIds==null || menuIds.length==0) {
			// 是：抛出EmptyIdException
			throw new EmptyIdException("查询菜单数据条数异常！id数组为空");
		}
		// 调用持久层方法，查询菜单数据个数
		int count=mapper.getMenuCount(menuIds);
		// 返回查询到的结果
		return count;
	}
	
	public SysMenuDO findSysMenuById(Integer menuId) 
			throws EmptyIdException, RecordNotFoundException{
		// 判断id是否为null或小于1
		if(menuId==null || menuId<1) {
			// 是：抛出EmptyIdException
			throw new EmptyIdException("查询菜单记录失败！菜单id不正确");
		}
		// 调用持久层方法查询记录
		SysMenuDO sysMenuDO=mapper.getSysMenu(menuId);
		// 判断返回结果是否为null
		if(sysMenuDO==null) {
			// 是: 抛出RecordNotFoundException
			throw new RecordNotFoundException("查询菜单记录失败！未找到相关记录");
		}
		// 返回查询结果
		return sysMenuDO;
	}

	public void modifySysMenu(SysMenuDO sysMenuDO) 
			throws EmptyArgumentException, UpdateException{
		// 判断sysMenuDO是否为null，或sysMenuDO.name是否为空
		if(sysMenuDO==null || StringUtils.isEmpty(sysMenuDO.getName())) {
			// 是：抛出EmptyArgumentException
			throw new EmptyArgumentException("更新菜单记录失败！新数据为空");
		}
		// 调用持久层方法执行更新操作
		int row=mapper.updateSysMenu(sysMenuDO);
		// 判断返回结果是否不为1
		if(row!=1) {
			// 是：抛出UpdateException
			throw new UpdateException("更新菜单记录失败！请联系管理员");
		}
	}
	
	public List<MenuNodeVO> findMenuNode()throws RecordNotFoundException{
		List<MenuNodeVO> list=mapper.listMenuNode();
		if(list==null || list.size()==0){
			throw new RecordNotFoundException("查询全部菜单节点信息异常！");
		}
		return list;
	}

	public void saveSysMenu(SysMenuDO sysMenuDO) 
			throws EmptyArgumentException, ParentNodeNotFoundException, InsertException{
		// 判断sysMenuDO是否为null
		if(sysMenuDO==null) {
			// 是：抛出EmptyArgumentException
			throw new EmptyArgumentException("添加菜单异常！菜单数据为空");
		}

		// 使用parentId查询对应的菜单信息
		SysMenuDO parentMenu=mapper.getSysMenu(sysMenuDO.getParentId());
		// 判断菜单信息是否为null
		if(parentMenu==null) {
			// 是：抛出ParentNodeNotFoundException
			throw new ParentNodeNotFoundException("添加菜单异常！父菜单不存在");
		}

		// 调用持久层方法，添加菜单记录
		int row=mapper.insertSysMenu(sysMenuDO);
		// 判断返回的成功数据条数是否为0
		if(row==0) {
			// 是：抛出InsertException
			throw new InsertException("添加菜单异常！请联系管理员");
		}
	}
	
	@Override
	@Transactional
	public void removeSysMenu(Integer menuId) throws EmptyIdException,HasSubMenuException,DeleteException {
		// 判断menuId是否为null 或小于1
		if(menuId==null ||  menuId<1) {
			// 是：抛出EmptyIdException
			throw new EmptyIdException("删除异常：请选择要删除的菜单项");
		}
		
		// 查询menuId对应的子菜单项的个数
		int subMenuCount=0;
		try {
			subMenuCount=mapper.getSubMenuCount(menuId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("查询子菜单数出现异常！",e);
		}
		
		// 判断个数是否大于0
		if(subMenuCount>0) {
			// 是：抛出HasSubMenuException
			throw new HasSubMenuException("删除失败：请先删除子菜单项");
		}

		// 删除菜单项
		int row1=mapper.deleteSysMenu(menuId);
		// 判断删除数据条数是否为0
		if(row1==0) {
			// 是：抛出DeleteException
			throw new DeleteException("删除菜单异常！请联系管理员");
		}
		
		// 为了演示效果，手动抛出异常
//		if(menuId==127) {
//			throw new RuntimeException("我们自己抛的异常");
//		}

		// 查询菜单项对应的角色菜单记录条数
		int roleMenuCount=mapper.getRoleMenuCount(menuId);
		// 判断记录条数是否为0
		if(roleMenuCount==0) {
			// 是： return
			return;
		}

		// 删除角色菜单记录
		int row2=mapper.deleteRoleMenu(menuId);
		// 判断删除数据条数是否为0
		if(row2==0) {
			// 是：抛出DeleteException
			throw new DeleteException("删除角色菜单异常！请联系管理员");
		}
	}


	public List<SysMenuDO> findSysMenu() 
			throws RecordNotFoundException{
		// 调用持久层方法查询菜单数据
		List<SysMenuDO> list=mapper.listSysMenu();
		// 判断查询到的结果是否为null或size=0
		if(list==null || list.size()==0) {
			// 是：抛出 RecordNotFoundException
			throw new RecordNotFoundException("未查询到任何菜单记录");
		}
		// 返回查询到的list
		return list;
	}

}
