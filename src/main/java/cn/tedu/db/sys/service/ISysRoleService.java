package cn.tedu.db.sys.service;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;

import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.pojo.SysRoleDO;
import cn.tedu.db.sys.service.ex.DeleteException;
import cn.tedu.db.sys.service.ex.EmptyArgumentException;
import cn.tedu.db.sys.service.ex.EmptyIdException;
import cn.tedu.db.sys.service.ex.InsertException;
import cn.tedu.db.sys.service.ex.MenuNotFoundException;
import cn.tedu.db.sys.service.ex.NoRecordException;
import cn.tedu.db.sys.service.ex.PageNumberException;
import cn.tedu.db.sys.service.ex.RecordNotFoundException;
import cn.tedu.db.sys.service.ex.UpdateException;

/**
 * 角色模块的持久层接口
 */
public interface ISysRoleService {
	
	/**
	 *  查询所有角色的id和name
	 * @return
	 * @throws RecordNotFoundException
	 */
	@RequiresPermissions("sys:role:view")
	List<SysRoleDO> findAllSysRole() 
			throws RecordNotFoundException;

	/**
	 * 根据角色id查询对应数据条数
	 * @param roleIds 角色id数组
	 * @return 对应数据条数
	 * @throws EmptyIdException
	 */
	@RequiresPermissions("sys:role:view")
	int findRoleCount(Integer[] roleIds)
			throws EmptyIdException;
	
	
	/**
	 * 基于角色id查询对应的菜单id
	 * @param roleId 角色id
	 * @return 菜单id的集合
	 * @throws EmptyIdException
	 * @throws RecordNotFoundException
	 */
	@RequiresPermissions("sys:role:view")
	List<Integer> findMenuByRoleId(Integer roleId) 
			throws EmptyIdException, RecordNotFoundException;

	/**
	 * 更新角色记录
	 * @param sysRoleDO 新的角色记录
	 * @param menuIds 新的菜单id的数组
	 * @throws EmptyArgumentException
	 * @throws EmptyIdException
	 * @throws MenuNotFoundException
	 * @throws UpdateException
	 * @throws DeleteException
	 * @throws InsertException
	 */
	@RequiresPermissions("sys:role:update")
	void modifySysRole(SysRoleDO sysRoleDO, Integer[] menuIds) 
			throws EmptyArgumentException, EmptyIdException, 
			MenuNotFoundException, UpdateException, 
			DeleteException, InsertException;
	
	/**
	 * 添加角色记录及角色菜单表关联记录
	 * @param sysRoleDO 新的角色记录
	 * @param menuIds 关联的菜单id
	 * @throws EmptyArgumentException
	 * @throws MenuNotFoundException
	 * @throws InsertException
	 */
	@RequiresPermissions("sys:role:insert")
	void saveSysRole(SysRoleDO sysRoleDO, Integer[] menuIds) throws 
	EmptyArgumentException, MenuNotFoundException, 
	InsertException;
	
	/**
	 *   基于条件删除角色数据，以及
	 *   角色菜单表中的关联数据 和 
	 *   用户角色表中的关联数据
	 * @param roleId 角色ID
	 * @throws EmptyIdException
	 * @throws DeleteException
	 */
	@RequiresPermissions("sys:role:delete")
	void removeSysRole(Integer roleId) throws EmptyIdException
					, DeleteException;
	
	/**
	 * 基于条件分页查询角色数据
	 * @param name 角色名称
	 * @param currentPage 当前页码 
	 * @return 当前页角色数据
	 * @throws PageNumberException
	 * @throws NoRecordException
	 */
	@RequiresPermissions("sys:role:view")
	PageObjectVO<SysRoleDO>	findSysRole(String name,Integer currentPage) throws 
	PageNumberException, NoRecordException;

}
