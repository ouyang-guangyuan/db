package cn.tedu.db.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.db.sys.pojo.SysRoleDO;

/**
 * 角色模块对应的持久层接口
 */
public interface SysRoleMapper {
	
	/**
	 * 基于角色id查询所有的菜单id
	 * @param roleIds 角色id数组
	 * @return 菜单id的集合
	 */
	List<Integer> listMenuIdByRoleId(@Param("roleIds")Integer[] roleIds);
	
	/**
	 * 查询所有角色的id和name
	 * @return 所有角色数据
	 */
	List<SysRoleDO> listAllSysRole();

	/**
	 * 基于角色id查询对应数据条数
	 * @param roleIds 角色id数组
	 * @return 实际存在的数据条数
	 */
	int getRoleCount(@Param("roleIds")Integer[] roleIds);
	
	
	/**
	 *  基于角色id查询关联的菜单id
	 * @param roleId 角色id
	 * @return 菜单id的集合
	 */
	List<Integer> getMenuByRoleId(Integer roleId);

	/**
	 * 更新菜单记录
	 * @param sysRoleDO 新的菜单记录
	 * @return 受影响的数据条数
	 */
	int updateSysRole(SysRoleDO sysRoleDO);
	
	/**
	 * 插入新的角色记录
	 * @param sysRoleDO 角色记录
	 * @return 受影响的行数
	 */
	int insertSysRole(SysRoleDO sysRoleDO);

	/**
	 * 插入角色菜单记录
	 * @param role_id 角色id
	 * @param menuIds 菜单id的数组
	 * @return 受影响的行数
	 */
	int insertRoleMenus(@Param("roleId")Integer roleId, 
			@Param("menuIds")Integer[] menuIds);
	
	
	/**
	 * 基于条件删除角色记录
	 * @param roleId
	 * @return 受影响行数
	 */
	int deleteSysRole(Integer roleId);

	/**
	 * 基于条件删除角色菜单表中关联记录
	 * @param roleId
	 * @return 受影响行数
	 */
	int deleteRoleMenu(Integer roleId);

	/**
	 * 基于条件删除用户角色表中关联记录
	 * @param roleId
	 * @return 受影响行数
	 */
	int deleteUserRole(Integer roleId);

	/**
	 * 基于条件查询角色菜单表中关联记录条数
	 * @param roleId
	 * @return 关联记录条数
	 */
	int getRoleMenuCount(Integer roleId);

	/**
	 * 基于条件查询用户角色表中关联记录条数
	 * @param roleId
	 * @return 关联记录条数
	 */
	int getUserRoleCount(Integer roleId);
	
	/**
	 * 基于条件分页查询角色记录
	 * @param name 角色名称
	 * @param recordIndex 跳过数据条数
	 * @param pageSize 查询数据条数
	 * @return 对应页数据
	 */
	List<SysRoleDO> listSysRole(@Param("name")String name,
			@Param("recordIndex")Integer recordIndex,
			@Param("pageSize")Integer pageSize);

	/**
	 * 基于条件查询角色记录条数
	 * @param name 角色名称
	 * @return 角色记录条数
	 */
	Integer getRowCount(@Param("name")String name);

}
