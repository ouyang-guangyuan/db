package cn.tedu.db.sys.service.impl;

import java.util.List;
import java.util.UUID;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.mapper.SysUserMapper;
import cn.tedu.db.sys.pojo.SysUserDO;
import cn.tedu.db.sys.service.ISysRoleService;
import cn.tedu.db.sys.service.ISysUserService;
import cn.tedu.db.sys.service.ex.EmptyArgumentException;
import cn.tedu.db.sys.service.ex.InsertException;
import cn.tedu.db.sys.service.ex.NoRecordException;
import cn.tedu.db.sys.service.ex.PageNumberException;
import cn.tedu.db.sys.service.ex.RoleNotFoundException;

@Service
public class SysUserServiceImpl implements ISysUserService {
	
	@Autowired
	SysUserMapper mapper;
	
	private int pageSize=3;
	
	@Autowired
	ISysRoleService roleService;
	
	@Transactional
	public void saveSysUser(SysUserDO sysUserDO, Integer[] roleIds) 
			throws EmptyArgumentException, RoleNotFoundException, InsertException{
		// 判断sysUserDO是否为null
		if(sysUserDO==null) {
			// 是：EmptyArgumentException
			throw new EmptyArgumentException("添加用户异常！用户信息为空");
		}

		// 判断sysUserDO.username是否为null
		if(StringUtils.isEmpty(sysUserDO.getUsername())) {
			// 是：EmptyArgumentException
			throw new EmptyArgumentException("添加用户异常！用户名为空");
		}

		// 判断sysUserDO.password是否为null
		if(StringUtils.isEmpty(sysUserDO.getPassword())) {
			// 是：EmptyArgumentException
			throw new EmptyArgumentException("添加用户异常！密码为空");
		}

		// 调用roleService方法，查询roleIds对应数据条数
		int roleCount=roleService.findRoleCount(roleIds);
		// 判断返回值和roleIds的长度是否不一致
		if(roleCount!=roleIds.length) {
			// 是：RoleNotFoundException
			throw new RoleNotFoundException("添加用户异常！关联的角色可能不存在");
		}

		// 生成盐值-UUID
		String salt=UUID.randomUUID().toString();
		// 对密码进行加密-MD5 使用Shrio提供的加密API
		SimpleHash sh=new SimpleHash("MD5", sysUserDO.getPassword(), salt,5);
		// 为sysUserDO设置salt和password
		sysUserDO.setPassword(sh.toHex());
		sysUserDO.setSalt(salt);
		// 设置当前用户默认为启用状态
		sysUserDO.setValid(1);
		
		// 调用持久层方法，插入用户数据
		int row1=0;
		try {
			row1=mapper.insertSysUser(sysUserDO);
		}catch(DuplicateKeyException e) {
			// 使用自定义异常进行封装
			throw new InsertException("添加用户异常！用户名已存在",e);
		}
				
		// 判断返回值是否不为1，或者sysUserDO.id为null
		if(row1!=1 || sysUserDO.getId()==null) {
			// 是：InsertException
			throw new InsertException("添加用户异常！用户添加失败");
		}
	
		// 调用持久层方法，插入用户角色关联数据
		int row2=mapper.insertUserRoles(sysUserDO.getId(), roleIds);
		// 判断返回值是否与roleIds的长度不一致
		if(row2!=roleIds.length) {
			// 是：InsertException
			throw new InsertException("添加用户异常！用户角色关联信息添加失败");
		}
	}

	public PageObjectVO<SysUserDO> findSysUser(String username,Integer currentPage) 
			throws PageNumberException, NoRecordException{
		// 判断currentPage是否为null或者小于1
		if(currentPage==null || currentPage<1) {
			// 是：PageNumberException
			throw new PageNumberException("查询用户信息异常！请提供正确的页码");
		}

		// 调用持久层方法，查询rowCount
		int rowCount=mapper.getRowCount(username);
		// 判断rowCount是否为0
		if(rowCount==0) {
			// 是：NoRecordException
			throw new NoRecordException("查询用户信息异常！总数据条数为0");
		}

		// 计算生成recordIndex
		int recordIndex=(currentPage-1)*pageSize;
		// 调用持久层方法，查询分页数据
		List<SysUserDO> list=mapper.listSysUser(username, recordIndex, pageSize);
		// 创建PageObjectVO对象
		PageObjectVO<SysUserDO> vo=new PageObjectVO<SysUserDO>();
		// 向PageObjectVO对象中填充数据
		vo.setCurrentPage(currentPage);
		vo.setPageCount((rowCount-1)/pageSize+1);
		vo.setPageRecords(list);
		vo.setPageSize(pageSize);
		vo.setRowCount(rowCount);
		// 返回PageObjectVO对象
		return vo;
	}

}
