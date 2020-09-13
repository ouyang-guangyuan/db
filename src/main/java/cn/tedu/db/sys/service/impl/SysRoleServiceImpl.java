package cn.tedu.db.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.mapper.SysRoleMapper;
import cn.tedu.db.sys.pojo.SysRoleDO;
import cn.tedu.db.sys.service.ISysMenuService;
import cn.tedu.db.sys.service.ISysRoleService;
import cn.tedu.db.sys.service.ex.DeleteException;
import cn.tedu.db.sys.service.ex.EmptyArgumentException;
import cn.tedu.db.sys.service.ex.EmptyIdException;
import cn.tedu.db.sys.service.ex.InsertException;
import cn.tedu.db.sys.service.ex.MenuNotFoundException;
import cn.tedu.db.sys.service.ex.NoRecordException;
import cn.tedu.db.sys.service.ex.PageNumberException;
import cn.tedu.db.sys.service.ex.RecordNotFoundException;
import cn.tedu.db.sys.service.ex.UpdateException;

@Service
public class SysRoleServiceImpl implements ISysRoleService{

	private Integer pageSize=3;
	
	@Autowired
	SysRoleMapper mapper;
	
	@Autowired
	ISysMenuService menuService;
	
	public List<SysRoleDO> findAllSysRole() 
			throws RecordNotFoundException{
		List<SysRoleDO> list=mapper.listAllSysRole();
		if(list==null || list.size()==0){
			throw new RecordNotFoundException("查询角色信息异常！");
		}
		return list;
	}


	public int findRoleCount(Integer[] roleIds)
			throws EmptyIdException{
		if(roleIds==null || roleIds.length==0){
			throw new EmptyIdException("查询角色数据条数异常！角色id为空");
		}
		int roleCount=mapper.getRoleCount(roleIds);
		return roleCount;
	}
	
	@Override
	public List<Integer> findMenuByRoleId(Integer roleId) 
			throws EmptyIdException, RecordNotFoundException{
		// 判断roleId是否为null或者小于1
		if(roleId==null || roleId<1) {
			// 是：抛出EmptyIdException
			throw new EmptyIdException("查询角色关联菜单id异常！请提供角色id");
		}
		// 调用持久层方法，查询roleId对应的menuIds
		List<Integer> menuIds=mapper.getMenuByRoleId(roleId);
		// 判断返回的集合是否为null或者长度为0
		if(menuIds==null || menuIds.size()==0) {
			// 是：抛出RecordNotFoundException、
			throw new RecordNotFoundException("查询角色关联菜单id异常！未查询到相关记录");
		}
		// 返回查询到的menuIds
		return menuIds;
	}

	@Transactional
	@Override
	public void modifySysRole(SysRoleDO sysRoleDO, Integer[] menuIds) 
			throws EmptyArgumentException, EmptyIdException, MenuNotFoundException,UpdateException, 
			DeleteException, InsertException{
		// 判断sysRoleDO是否为null或者sysRoleDO.name为空
		if(sysRoleDO==null || StringUtils.isEmpty(sysRoleDO.getName())) {
			// 是：抛出EmptyArgumentException
			throw new EmptyArgumentException("更新角色信息异常！角色信息为空");
		}

		// 判断sysRoleDO.id是否为null或者小于1
		if(sysRoleDO.getId()==null || sysRoleDO.getId()<1) {
			// 是：EmptyIdException
			throw new EmptyIdException("更新角色信息异常！角色id为空");
		}

		// 调用sysMenuService的方法查询menuIds对应的数据条数
		int count=menuService.findMenuCount(menuIds);
		// 判断查询到的数据条数是否与menuIds的长度不一致
		if(count!=menuIds.length) {
			// 是：MenuNotFoundException
			throw new MenuNotFoundException("更新角色信息异常！关联菜单可能不存在");
		}

		// 调用持久层方法，更新角色记录
		int row1=mapper.updateSysRole(sysRoleDO);
		// 判断返回值是否为0
		if(row1==0) {
			// 是:UpdateException
			throw new UpdateException("更新角色信息异常！请联系管理员");
		}

		// 调用持久层方法，查询roleId对应的菜单数据的个数
		int roleMenuCount=mapper.getRoleMenuCount(sysRoleDO.getId().intValue());
		// 调用持久层方法，删除roleId对应的菜单记录
		int row2=mapper.deleteRoleMenu(sysRoleDO.getId().intValue());
		// 判断删除操作的返回值与查询到的菜单数据的个数是否不一致
		if(row2!=roleMenuCount) {
			// 是：DeleteException
			throw new DeleteException("更新角色信息异常！删除旧关联记录异常");
		}
	
		// 调用持久层方法，插入新的关联记录
		int row3=mapper.insertRoleMenus(sysRoleDO.getId().intValue(), menuIds);
		// 判断插入操作的返回值与menuIds的长度是否不一致
		if(row3!=menuIds.length) {
			// 是：InsertException
			throw new InsertException("更新角色信息异常！添加新关联记录异常");
		}
	}
	
	@Transactional
	public void saveSysRole(SysRoleDO sysRoleDO, Integer[] menuIds) throws 
	EmptyArgumentException, MenuNotFoundException, 
	InsertException{
		// 判断sysRoleDO是否为null或者sysRoleDO.name是否为空
		if(sysRoleDO==null || StringUtils.isEmpty(sysRoleDO.getName())) {
			// 是：抛出EmptyArgumentException
			throw new EmptyArgumentException("添加角色异常！角色数据为空");
		}

		// 调用ISysMenuService方法，查询menuIds对应的数据条数
		int menuCount=menuService.findMenuCount(menuIds);
		// 判断数据条数与menuIds数组长度是否不一致
		if(menuCount!=menuIds.length) {
			// 是：抛出MenuNotFoundException
			throw new MenuNotFoundException("添加角色异常！关联菜单项可能不存在");
		}

		// 调用持久层方法，插入新的角色记录
		int row1=mapper.insertSysRole(sysRoleDO);
		// 判断返回值是否为0 或 sysRoleDO.id是否为null
		if(row1==0 || sysRoleDO.getId()==null) {
			// 是：抛出InsertException
			throw new InsertException("添加角色异常！请联系管理员");
		}
	
		// 调用持久层方法，插入角色菜单关联数据
		int row2=mapper.insertRoleMenus(sysRoleDO.getId().intValue(), menuIds);
		// 判断返回值是否不等于menuIds的长度
		if(row2!=menuIds.length) {
			// 是：抛出InsertException
			throw new InsertException("添加角色菜单数据异常！请联系管理员");
		}
	}
	
	@Transactional
	@Override
	public void removeSysRole(Integer roleId) throws 
			EmptyIdException , DeleteException{
		// 判断roleId是否为null或小于1
		if(roleId==null || roleId<1) {
			// 是：抛出EmptyIdException
			throw new EmptyIdException("删除角色记录异常！请选择要删除的记录");
		}

		// 调用持久层方法删除角色记录
		int row1=mapper.deleteSysRole(roleId);
		// 判断返回值是否为0
		if(row1==0) {
			// 是：抛出DeleteException
			throw new DeleteException("删除角色记录异常！请联系管理员");
		}

		// 调用持久层方法，查询角色菜单记录条数
		int roleMenuCount=mapper.getRoleMenuCount(roleId);
		// 判断记录条数是否不为0
		if(roleMenuCount!=0) {// 是：
			// 调用持久层方法，删除角色菜单记录
			int row2=mapper.deleteRoleMenu(roleId);
			// 判断返回值是否不等于查询到的角色菜单记录条数
			if(row2!=roleMenuCount) {
				// 是：抛出DeleteException
				throw new DeleteException("删除角色记录异常：删除角色菜单记录异常");
			}
		}

		// 调用持久层方法，查询用户角色记录条数
		int userRoleCount=mapper.getUserRoleCount(roleId);
		// 判断记录条数是否不为0
		if(userRoleCount!=0) {// 是：
			// 调用持久层方法，删除用户角色记录
			int row3=mapper.deleteUserRole(roleId);
			// 判断返回值是否不等于查询到的用户角色记录条数
			if(row3!=userRoleCount) {
				// 是：抛出DeleteException
				throw new DeleteException("删除角色记录异常：删除用户角色记录异常");
			}
		}
	}

	public PageObjectVO<SysRoleDO>	findSysRole(String name,Integer currentPage) 
			throws PageNumberException, NoRecordException{
		// 判断页面是否为null或小于1
		if(currentPage==null || currentPage<1){
			// 是：抛出PageNumberException
			throw new PageNumberException("查询角色异常！当前页码错误");
		}

		// 调用持久层查询rowCount
		int rowCount=mapper.getRowCount(name);
		// 判断rowCount是否为0
		if(rowCount==0) {
			// 是：抛出NoRecordException
			throw new NoRecordException("查询角色异常！总数据条数为0");
		}

		// 计算recordIndex
		int recordIndex=(currentPage-1)*pageSize;
		// 调用持久层方法，执行查询
		List<SysRoleDO> list=mapper.listSysRole(name, recordIndex, pageSize);
		
		// 创建PageObjectVO对象
		PageObjectVO<SysRoleDO> vo=new PageObjectVO<SysRoleDO>();
		// 向PageObjectVO对象填充数据
		vo.setRowCount(rowCount);
		vo.setCurrentPage(currentPage);
		vo.setPageSize(pageSize);
		vo.setPageCount((rowCount-1)/pageSize+1);
		vo.setPageRecords(list);
		// 返回PageObjectVO对象
		return vo;
	}
}
