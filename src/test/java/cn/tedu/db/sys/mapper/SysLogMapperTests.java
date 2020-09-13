package cn.tedu.db.sys.mapper;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.db.sys.pojo.SysLogDO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysLogMapperTests {

	@Autowired
	SysLogMapper mapper;
	
	@Test
	public void insertSysLog() {
		SysLogDO sysLogDO=new SysLogDO(null, "test", 
				"测试", "test()", "abc", 666l, "0.0.0.0", new Date());
		int row=mapper.insertSysLog(sysLogDO);
		System.err.println("row="+row);
	}
	
	@Test
	public void deleteSysLog() {
		Integer[] ids= {9,10};
		int row=mapper.deleteSysLog(ids);
		System.err.println("row="+row);
	}
	
	@Test
	public void getRowCount() {
		int count=mapper.getRowCount("tom");
		System.err.println("count="+count);
	}
	
	@Test
	public void listSysLog() {
		List<SysLogDO> list=mapper.listSysLog(null, 0, 10);
		for(SysLogDO item:list) {
			System.err.println(item);
		}
	}
	
}
