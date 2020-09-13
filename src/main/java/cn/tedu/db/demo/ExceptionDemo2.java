package cn.tedu.db.demo;

import cn.tedu.db.common.ex.ServiceException;
import cn.tedu.db.sys.service.ex.PageNumberException;

public class ExceptionDemo2 {

	public static void main(String[] args) {
		// 模拟控制器方法
		try {
			methodA();
		}catch(ServiceException e) {
			String detailMessage=e.getMessage();
			System.out.println("message="+detailMessage);
		}
	}
	
	public static void methodA() {
		// 模拟业务层方法
		throw new PageNumberException("当前页码错误");
	}

}
