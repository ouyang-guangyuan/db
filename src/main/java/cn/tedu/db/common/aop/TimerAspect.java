package cn.tedu.db.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAspect {
	
	@Around("execution(* cn.tedu.db.sys.service.impl.*.*(..))")
	public Object a(ProceedingJoinPoint pjp) throws Throwable {
		// 目标方法执行前计时
		long st=System.currentTimeMillis();
		
		// 切面注入位置的目标方法被调用
		Object result = pjp.proceed();
		
		// 目标方法执行后计时
		long et=System.currentTimeMillis();
		
		System.err.println(pjp.getSignature()+"耗时："+(et-st)+"ms");
		
		return result;
	}

}
