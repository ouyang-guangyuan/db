package cn.tedu.db.sys.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.db.sys.pojo.SysLogDO;
import cn.tedu.db.sys.pojo.SysUserDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserMapperTests {

	@Autowired
	SysUserMapper mapper;
	
	@Test
	public void listRoleIdByUserId() {
	    List<Integer> list=mapper.listRoleIdByUserId(17);
	    for(Integer roleId:list) {
	        System.err.println("roleId="+roleId);
	    }
	}
	
	@Test
	public void getUserByUsername() {
	    SysUserDO user=mapper.getUserByUsername("jerry");
	    System.err.println(user);
	}
	
	@Test
	public void insertSysUser() {
		SysUserDO user=new SysUserDO(null, null, "tester", "tester", 
				null, "testname", "123", "testsale", "a@tedu.cn", "1234567", 1, 1, null);
		int row=mapper.insertSysUser(user);
		System.err.println("row="+row);
	}
	
	@Test
	public void insertUserRoles() {
		Integer userId=26;
		Integer[] roleIds= {53,54,55};
		int row=mapper.insertUserRoles(userId, roleIds);
		System.err.println("row="+row);
	}
	
	
	@Test
	public void listSysUser() {
		List<SysUserDO> list=mapper.listSysUser("user", 0, 3);
		for(SysUserDO sysUserDO:list) {
			System.err.println(sysUserDO);
		}
	}
	
	@Test
	public void getRowCount() {
		Integer rowCount=mapper.getRowCount("user");
		System.err.println("count="+rowCount);
	}
	
}
