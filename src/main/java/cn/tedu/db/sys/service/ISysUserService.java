package cn.tedu.db.sys.service;

import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.pojo.SysUserDO;
import cn.tedu.db.sys.service.ex.EmptyArgumentException;
import cn.tedu.db.sys.service.ex.InsertException;
import cn.tedu.db.sys.service.ex.NoRecordException;
import cn.tedu.db.sys.service.ex.PageNumberException;
import cn.tedu.db.sys.service.ex.RoleNotFoundException;

/**
 * 用户信息的业务层接口
 */
public interface ISysUserService {
	
	/**
	 * 保存用户信息及用户角色关联信息
	 * @param sysUserDO 用户信息
	 * @param roleIds 角色id数组
	 * @throws EmptyArgumentException
	 * @throws RoleNotFoundException
	 * @throws InsertException
	 */
	void saveSysUser(SysUserDO sysUserDO, Integer[] roleIds) 
			throws EmptyArgumentException, RoleNotFoundException, InsertException;
	
	/**
	 * 基于条件分页查询用户数据
	 * @param username 用户名
	 * @param currentPage 当前页码
	 * @return 当前页对应数据
	 * @throws PageNumberException
	 * @throws NoRecordException
	 */
	PageObjectVO<SysUserDO> findSysUser(String username,Integer currentPage) 
			throws PageNumberException, NoRecordException;

}
