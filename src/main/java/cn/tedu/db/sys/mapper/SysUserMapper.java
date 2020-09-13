package cn.tedu.db.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.db.sys.pojo.SysUserDO;

/**
 * 用户信息的持久层接口
 */
public interface SysUserMapper {
	
	/**
	 *  基于用户id查询所有关联的角色id
	 * @param userId 用户id
	 * @return 角色id的集合
	 */
	List<Integer> listRoleIdByUserId(Integer userId);
	
	/**
	 * 基于用户名查询用户信息
	 * @param username 用户名
	 * @return 用户信息
	 */
	SysUserDO getUserByUsername(String username);
	
	/**
	 * 添加用户信息
	 * @param sysUserDO 用户信息
	 * @return 添加成功的数据条数
	 */
	int insertSysUser(SysUserDO sysUserDO);

	/**
	 * 添加用户角色关联信息
	 * @param userId 用户id
	 * @param roleIds 角色id数组
	 * @return 添加成功的数据条数
	 */
	int insertUserRoles(@Param("userId")Integer userId, 
			@Param("roleIds")Integer[] roleIds);
	
	
	/**
	 * 基于用户名分页查询用户信息
	 * @param username 用户名
	 * @param recordIndex 跳过的数据条数
	 * @param pageSize 查询的数据条数
	 * @return 当前页数据
	 */
	List<SysUserDO> listSysUser(@Param("username")String username,
			@Param("recordIndex")Integer recordIndex,
			@Param("pageSize")Integer pageSize);

	/**
	 * 基于用户名查询总数据条数
	 * @param username 用户名
	 * @return 总数据条数
	 */
	Integer getRowCount(@Param("username")String username);

}
