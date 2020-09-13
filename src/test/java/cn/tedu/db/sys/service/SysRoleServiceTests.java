package cn.tedu.db.sys.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.pojo.SysRoleDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleServiceTests {
	
	@Autowired
	ISysRoleService service;
	
	@Test
	public void findAllSysRole() {
		List<SysRoleDO> list=service.findAllSysRole();
		for(SysRoleDO item:list) {
			System.err.println(item);
		}
	}
	
	@Test
	public void findRoleCount() {
		Integer[] roleIds= {53,54,55,88};
		int count=service.findRoleCount(roleIds);
		System.err.println("count="+count);
	}
	
	@Test
	public void findMenuByRoleId() {
		List<Integer> menuIds=service.findMenuByRoleId(47);
		for(Integer i:menuIds) {
			System.err.println("menuId="+i);
		}
	}
	
	@Test
	public void modifySysRole() {
		SysRoleDO sysRoleDO=new SysRoleDO(null, null, 
				"tester", "tester1", 1L, "Jerry2", "modify test");
		Integer[] menuIds= {45,46,47};
		service.modifySysRole(sysRoleDO, menuIds);
	}
	
	
	@Test
	public void saveSysRole() {
		SysRoleDO sysRoleDO=new SysRoleDO(null, null, 
				"tester", "tester", null, "测试角色22", "测试234");
		Integer[] menuIds= {8,24,25};
		service.saveSysRole(sysRoleDO, menuIds);
	}
	

	@Test
	public void removeSysRole(){
		service.removeSysRole(72);
	}
	
	@Test
	public void findSysRole() {
		PageObjectVO<SysRoleDO> vo=service.findSysRole(null, 2);
		System.err.println(vo);
	}

}
