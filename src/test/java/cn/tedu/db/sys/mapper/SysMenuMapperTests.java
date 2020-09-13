package cn.tedu.db.sys.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.db.sys.pojo.MenuNodeVO;
import cn.tedu.db.sys.pojo.SysMenuDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysMenuMapperTests {
	
	@Autowired
	SysMenuMapper mapper;
	
	@Test
	public void listPermissions() {
	    Integer[] menuIds= {8,45,46,47,48};
	    List<String> list=mapper.listPermissions(menuIds);
	    for(String permission:list) {
	        System.err.println("permission="+permission);
	    }
	}
	
	@Test
	public void getMenuCount(){
		Integer[] menuIds={8,24,25,26};
		int count =mapper.getMenuCount(menuIds);
		System.err.println("count="+count);
	}
	
	@Test
	public void updateSysMenu() {
		SysMenuDO sysMenuDO=new SysMenuDO(null, null,
				"tester123", "tester123",5, "测试", 
				"测试URL111", 1, 8, "...", 45, "user:abc", null);
		int row=mapper.updateSysMenu(sysMenuDO);
		System.err.println("row="+row);
	}
	
	@Test
	public void listMenuNode() {
		List<MenuNodeVO> list=mapper.listMenuNode();
		for(MenuNodeVO vo:list) {
			System.err.println(vo);
		}
	}
	
	@Test
	public void getSysMenu() {
		SysMenuDO sysMenuDO=mapper.getSysMenu(126);
		System.err.println(sysMenuDO);
	}
	
	@Test
	public void insertSysMenu() {
		SysMenuDO sysMenuDO=new SysMenuDO(null, null,
				"tester", "tester", 188, "测试", 
				"测试URL", 2, 6, "...", 48, "user:test", null);
		int row=mapper.insertSysMenu(sysMenuDO);
		System.err.println("row="+row);
	}
	
	
	@Test
	public void getSubMenuCount() {
		int count=mapper.getSubMenuCount(7);
		System.err.println("count="+count);
	}
	
	@Test
	public void deleteSysMenu() {
		int row=mapper.deleteSysMenu(127);
		System.err.println("row="+row);
	}
	
	@Test
	public void getRoleMenuCount() {
		int count=mapper.getRoleMenuCount(127);
		System.err.println("count="+count);
	}
	
	@Test
	public void deleteRoleMenu() {
		int row=mapper.deleteRoleMenu(127);
		System.err.println("row="+row);
	}
	
	@Test
	public void listSysMenu() {
		List<SysMenuDO> list=mapper.listSysMenu();
		for(SysMenuDO sysMenuDO:list) {
			System.err.println(sysMenuDO);
		}
	}

}
