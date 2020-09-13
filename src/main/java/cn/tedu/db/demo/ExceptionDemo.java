package cn.tedu.db.demo;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ExceptionDemo {

	public static void main(String[] args) {
		methodA();
	}
	
	public static void methodA() {
		methodB();
	}
	
	public static void methodB() {
		methodC();
	}

	public static void methodC() {
		methodD();
	}
	
	public static void methodD() {
		String ageStr="12";
		int age= Integer.parseInt(ageStr);
		System.out.println("age="+age);
	}

}
