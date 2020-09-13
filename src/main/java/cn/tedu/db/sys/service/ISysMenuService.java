package cn.tedu.db.sys.service;

import java.util.List;

import org.springframework.aop.ThrowsAdvice;

import cn.tedu.db.sys.pojo.MenuNodeVO;
import cn.tedu.db.sys.pojo.SysMenuDO;
import cn.tedu.db.sys.service.ex.DeleteException;
import cn.tedu.db.sys.service.ex.EmptyArgumentException;
import cn.tedu.db.sys.service.ex.EmptyIdException;
import cn.tedu.db.sys.service.ex.HasSubMenuException;
import cn.tedu.db.sys.service.ex.InsertException;
import cn.tedu.db.sys.service.ex.ParentNodeNotFoundException;
import cn.tedu.db.sys.service.ex.RecordNotFoundException;
import cn.tedu.db.sys.service.ex.UpdateException;

public interface ISysMenuService {
	
	/**
	 * 基于菜单id查询对应菜单数据个数
	 * @param menuIds 菜单id数组
	 * @return 存在的菜单数据个数
	 * @throws EmptyIdException
	 */
	int findMenuCount(Integer[] menuIds) throws 
	EmptyIdException;
	
	/**
	 * 基于菜单ID查询菜单记录
	 * @param menuId 菜单ID
	 * @return 菜单记录及父菜单名称
	 * @throws EmptyIdException
	 * @throws RecordNotFoundException
	 */
	SysMenuDO findSysMenuById(Integer menuId) 
			throws EmptyIdException, RecordNotFoundException;

	/**
	 * 更新菜单记录
	 * @param sysMenuDO 新的菜单信息
	 * @throws EmptyArgumentException
	 * @throws UpdateException
	 */
	void modifySysMenu(SysMenuDO sysMenuDO) 
			throws EmptyArgumentException, UpdateException;
	
	/**
	 * 查询全部菜单节点信息
	 * @return 全部菜单节点信息
	 * @throws RecordNotFoundException
	 */
	List<MenuNodeVO> findMenuNode() throws RecordNotFoundException;

	/**
	 * 保存菜单记录
	 * @param sysMenuDO 菜单记录
	 * @throws EmptyArgumentException
	 * @throws ParentNodeNotFoundException
	 * @throws InsertException
	 */
	void saveSysMenu(SysMenuDO sysMenuDO) throws EmptyArgumentException, 
							ParentNodeNotFoundException, InsertException;
	
	/**
	 * 基于id删除菜单项
	 * 和菜单角色表中对应的记录
	 * 
	 * @param menuId 菜单id
	 * @throws EmptyIdException
	 * @throws HasSubMenuException
	 * @throws DeleteException
	 */
	void removeSysMenu(Integer menuId)
			throws EmptyIdException,HasSubMenuException,DeleteException;
	
	/**
	 * 查询所有菜单记录
	 * @return 所有菜单记录
	 * @throws RecordNotFoundException
	 */
	List<SysMenuDO> findSysMenu() 
			throws RecordNotFoundException;

}
