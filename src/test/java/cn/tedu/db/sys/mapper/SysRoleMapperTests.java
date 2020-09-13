package cn.tedu.db.sys.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.db.sys.pojo.SysRoleDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysRoleMapperTests {
	
	@Autowired
	SysRoleMapper mapper;
	
	@Test
	public void listMenuIdByRoleId() {
	    Integer[] roleIds= {48,49};
	    List<Integer> list=mapper.listMenuIdByRoleId(roleIds);
	    for(Integer menuId:list) {
	        System.err.println("menuId="+menuId);
	    }
	}
	
	@Test
	public void listAllSysRole() {
		List<SysRoleDO> list=mapper.listAllSysRole();
		for(SysRoleDO sysRoleDO:list) {
			System.err.println(sysRoleDO);
		}
	}
	
	@Test
	public void getRoleCount() {
		Integer[] roleIds= {53,54,55,57};
		int count=mapper.getRoleCount(roleIds);
		System.err.println("count="+count);
	}
	
	@Test
	public void getMenuByRoleId() {
		List<Integer> list=mapper.getMenuByRoleId(1);
		for(Integer i:list) {
			System.err.println("menuId="+i);
		}
	}
	
	@Test
	public void updateSysRole() {
		SysRoleDO sysRoleDO=new SysRoleDO(null, null, 
				"Json", "Json", 1L, "Json", "update test");
		int row=mapper.updateSysRole(sysRoleDO);
		System.err.println("row="+row);
	}
	
	
	@Test
	public void insertSysRole() {
		SysRoleDO sysRoleDO=new SysRoleDO(null, null, 
				"tester", "tester", null, "测试角色11", "测试123");
		int row=mapper.insertSysRole(sysRoleDO);
		System.err.println("row="+row);
		System.err.println("id="+sysRoleDO.getId());
	}
	
	@Test
	public void insertRoleMenus() {
		Integer roleId=52;
		Integer[] menuIds= {8,24,25};
		int row=mapper.insertRoleMenus(roleId, menuIds);
		System.err.println("row="+row);
	}
	
	
	
	@Test
	public void getRoleMenuCount() {
		int count=mapper.getRoleMenuCount(71);
		System.err.println("count="+count);
	}
	
	@Test
	public void getUserRoleCount() {
		int count=mapper.getUserRoleCount(71);
		System.err.println("count="+count);
	}
	
	@Test
	public void deleteSysRole() {
		int row=mapper.deleteSysRole(71);
		System.err.println("row="+row);
	}
	
	@Test
	public void deleteRoleMenu() {
		int row=mapper.deleteRoleMenu(71);
		System.err.println("row="+row);
	}
	
	@Test
	public void deleteUserRole() {
		int row=mapper.deleteUserRole(71);
		System.err.println("row="+row);
	}
	
	
	@Test
	public void listSysRole() {
		List<SysRoleDO> list=mapper.listSysRole("运维经理", 0, 3);
		for(SysRoleDO sysRoleDO:list) {
			System.err.println(sysRoleDO);
		}
	}
	
	@Test
	public void getRowCount() {
		int count=mapper.getRowCount("运维");
		System.err.println("count="+count);
	}

}
