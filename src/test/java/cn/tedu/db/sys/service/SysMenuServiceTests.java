package cn.tedu.db.sys.service;

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
public class SysMenuServiceTests {
	
	@Autowired
	ISysMenuService service;
	
	@Test
	public void findMenuCount() {
		Integer[] menuIds= {};
		int count=service.findMenuCount(menuIds);
		System.err.println("count="+count);
	}
	
	
	
	@Test
	public void findSysMenuById() {
		SysMenuDO sysMenuDO = service.findSysMenuById(null);
		System.err.println(sysMenuDO);
	}
	
	@Test
	public void modifySysMenu() {
		SysMenuDO sysMenuDO=new SysMenuDO(null, null,
				"tester123", "tester123",193, "测试", 
				"测试URL111", 1, 8, "...", 45, "user:abc", null);
//		SysMenuDO sysMenuDO=new SysMenuDO();
		service.modifySysMenu(sysMenuDO);
	}
	
	
	
	@Test
	public void findMenuNode() {
		List<MenuNodeVO> list=service.findMenuNode();
		for(MenuNodeVO vo:list) {
			System.err.println(vo);
		}
	}
	
	@Test
	public void saveSysMenu() {
		SysMenuDO sysMenuDO=new SysMenuDO(null, null,
				"tester", "tester", null, "测试", 
				"测试URL", 2, 6, "...", 8, "user:test", null);
		service.saveSysMenu(sysMenuDO);
	}
	
	
	@Test
	public void removeSysMenu() {
		try {
			service.removeSysMenu(127);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void findSysMenu() {
		List<SysMenuDO> list=service.findSysMenu();
		for(SysMenuDO sysMenuDO:list) {
			System.err.println(sysMenuDO);
		}
	}
	

}
