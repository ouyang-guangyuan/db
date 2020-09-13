package cn.tedu.db.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.db.sys.pojo.MenuNodeVO;
import cn.tedu.db.sys.pojo.SysMenuDO;

/**
 * 菜单模块对应的持久层接口
 */
public interface SysMenuMapper {
	
	/**
	 * 基于菜单id查询权限信息
	 * @param menuIds 菜单id
	 * @return 权限信息数组
	 */
	List<String> listPermissions(@Param("menuIds")Integer[] menuIds);
	
	/**
	 * 基于菜单id查询菜单数量
	 * @param menuIds 菜单id的数组
	 * @return 对应菜单记录数量
	 */
	int getMenuCount(@Param("menuIds")Integer[] menuIds);
	
	/**
	 * 更新菜单信息
	 * @param sysMenuDO 新的菜单信息
	 * @return 更新成功的数据条数
	 */
	int updateSysMenu(SysMenuDO sysMenuDO);
	
	/**
	 * 查询全部菜单节点信息
	 * @return 全部菜单节点信息
	 */
	List<MenuNodeVO> listMenuNode();
	
	/**
	 * 基于菜单id查询菜单信息
	 * @param id 菜单id
	 * @return 菜单信息 或 null
	 */
	SysMenuDO getSysMenu(Integer id);

	/**
	 * 添加菜单数据
	 * @param sysMenuDO 菜单数据
	 * @return 添加成功的数据条数
	 */
	int insertSysMenu(SysMenuDO sysMenuDO);
	
	/**
	 * 基于id获取子菜单个数
	 * @param menuId 菜单id
	 * @return 子菜单个数
	 */
	int getSubMenuCount(Integer menuId);

	/**
	 * 基于id删除子菜单记录
	 * @param menuId 菜单id
	 * @return 被删除数据条数
	 */
	int deleteSysMenu(Integer menuId);

	/**
	 * 基于菜单id查询角色菜单表中对应数据条数
	 * @param menuId 菜单id
	 * @return 对应数据条数
	 */
	int getRoleMenuCount(Integer menuId);

	/**
	 * 基于菜单id删除角色菜单表中数据
	 * @param menuId 菜单id
	 * @return 被删除数据条数
	 */
	int deleteRoleMenu(Integer menuId);
	
	/**
	 * 查询全部菜单信息
	 * @return 全部菜单信息
	 */
	List<SysMenuDO> listSysMenu();

}
