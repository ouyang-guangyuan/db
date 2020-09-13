package cn.tedu.db.demo;

import java.io.IOException;

import cn.tedu.db.sys.service.ex.PageNumberException;

public class MyImpl implements MyInter {

	@Override
	public void methodA() throws IOException{
		throw new PageNumberException("页面错误");
	}

}
