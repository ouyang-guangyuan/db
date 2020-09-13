package cn.tedu.db.common.aop;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.tedu.db.common.util.IPUtils;
import cn.tedu.db.sys.mapper.SysLogMapper;
import cn.tedu.db.sys.pojo.SysLogDO;
import cn.tedu.db.sys.pojo.SysUserDO;

@Aspect
@Component
public class SysLogAspect {
	
	@Autowired
	SysLogMapper mapper;
	
	@Around("execution(* cn.tedu.db.sys.service.impl.*.*(..))")
	public Object advice(ProceedingJoinPoint pjp) throws Throwable{
		// 调用目标方法前计时
		long st=System.currentTimeMillis();
		// 调用目标方法，获得result
		Object result=pjp.proceed();
		// 调用目标方法后计时
		long et=System.currentTimeMillis();
		// 计算耗时 time
		long time=et-st;
		try {
			// 调用saveSysLog方法
			// 保存日志过程中可能出现异常，但是该异常不属于用户核心业务的异常
			// 所以不能抛给方法调用者，必须在这里捕获并输出异常信息
			saveSysLog(time, pjp);
		}catch(Exception e) {
			e.printStackTrace();
		}
		// 返回result
		return result;
	}

	public void saveSysLog(Long time,ProceedingJoinPoint pjp) throws JsonProcessingException{
		// 获取SysLogDO对象所需的数据
		// username 从Session中拿，后期通过shiro来实现
		// 获取request对象
//		HttpServletRequest request = 
//				((ServletRequestAttributes) RequestContextHolder
//						.getRequestAttributes()).getRequest();
//		HttpSession session=request.getSession();
//		String username="visitor";// 通用的用户名
//		if(session.getAttribute("username")!=null) {
//			username=session.getAttribute("username").toString();
//		}
		String username="visitor"; // 通用的用户名
		Subject subject=SecurityUtils.getSubject();
		if(((SysUserDO)subject.getPrincipal()).getUsername()!=null) {
			username=((SysUserDO)subject.getPrincipal()).getUsername();
		};
		// operation 从方法上的指定注解中拿 TODO
		String operation="TODO"; // 后续实现
		// method 包名.类名.方法名 从pjp中获取
		String className=pjp.getTarget().getClass().getName(); // 包名.类名
		String method=className+"."+pjp.getSignature().getName(); 
		// params 从pjp中获取
		Object[] paramsObj=pjp.getArgs();
		// 将Ojbect类型的参数中的数据生成对应的字符串表示
		String params=new ObjectMapper().writeValueAsString(paramsObj);
		// time作为参数传进来了
		// ip 从request中获取 - 利用IPUtils工具类获取
		String ip=IPUtils.getIpAddr();
		// createdTime 直接创建Date()对象
		Date createdTime=new Date();
		// 创建SysLogDO对象 - new 对象
		SysLogDO sysLogDO=new SysLogDO(null, username, operation, method, params, time, ip, createdTime);
		// 调用持久层方法，将SysLogDO对象保存到数据库 mapper.insertSysLog();
		mapper.insertSysLog(sysLogDO);
	}

}
