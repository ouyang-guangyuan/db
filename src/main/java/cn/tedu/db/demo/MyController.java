package cn.tedu.db.demo;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

public class MyController {
	
	@Autowired
	MyInter inter;
	
	public void methodB() {
		try {
			inter.methodA();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
