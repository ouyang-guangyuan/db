package cn.tedu.db.common.controller;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.db.common.ex.ServiceException;
import cn.tedu.db.common.pojo.JsonResult;
import cn.tedu.db.sys.service.ex.DeleteException;
import cn.tedu.db.sys.service.ex.EmptyArgumentException;
import cn.tedu.db.sys.service.ex.EmptyIdException;
import cn.tedu.db.sys.service.ex.HasSubMenuException;
import cn.tedu.db.sys.service.ex.InsertException;
import cn.tedu.db.sys.service.ex.MenuNotFoundException;
import cn.tedu.db.sys.service.ex.NoRecordException;
import cn.tedu.db.sys.service.ex.PageNumberException;
import cn.tedu.db.sys.service.ex.ParentNodeNotFoundException;
import cn.tedu.db.sys.service.ex.RecordNotFoundException;
import cn.tedu.db.sys.service.ex.RoleNotFoundException;
import cn.tedu.db.sys.service.ex.UpdateException;

/**
 * 所有控制器的父类
 * 封装统一异常处理的逻辑
 */
public abstract class BaseController {
	
	public static final int STATE_SUCCESS=20; // 正常响应时的状态码
	public static final String MSG_SUCCESS="OK"; //正常响应时的消息
	
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public JsonResult<Void> doHandleShiroException(ShiroException e) {
	    JsonResult<Void> r=new JsonResult<Void>();
	    r.setState(0);
	    // 注意：现实开发中，这里的错误提示信息要尽量模糊
	    // 不要给攻击者任何可以判断账户状态的信息
	    if(e instanceof UnknownAccountException) {
	        r.setMessage("账户不存在");
	    }else if(e instanceof LockedAccountException) {
	        r.setMessage("账户已被禁用");
	    }else if(e instanceof IncorrectCredentialsException) {
	        r.setMessage("密码不正确");
	    }else if(e instanceof AuthorizationException) {
	        r.setMessage("没有此操作权限");
	    }else {
	        r.setMessage("系统维护中");
	    }
	    e.printStackTrace();
	    return r;
	}
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public JsonResult<Void> handleException(Throwable e){
		JsonResult<Void> jr=new JsonResult<Void>();
		
		if(e instanceof PageNumberException) {
			jr.setState(4001);//代表页码异常
			jr.setMessage(e.getMessage());
		}else if(e instanceof NoRecordException) {
			jr.setState(4002);
			jr.setMessage(e.getMessage());
		}else if(e instanceof EmptyIdException) {
			jr.setState(3001);
			jr.setMessage(e.getMessage());
		}else if(e instanceof DeleteException) {
			jr.setState(3002);
			jr.setMessage(e.getMessage());
		}else if(e instanceof RecordNotFoundException) {
			jr.setState(4003);
			jr.setMessage(e.getMessage());
		}else if(e instanceof HasSubMenuException) {
			jr.setState(4004);
			jr.setMessage(e.getMessage());
		}else if(e instanceof EmptyArgumentException) {
			jr.setState(4005);
			jr.setMessage(e.getMessage());
		}else if(e instanceof ParentNodeNotFoundException) {
			jr.setState(4006);
			jr.setMessage(e.getMessage());
		}else if(e instanceof InsertException) {
			jr.setState(4007);
			jr.setMessage(e.getMessage());
		}else if(e instanceof UpdateException) {
			jr.setState(5001);
			jr.setMessage(e.getMessage());
		}else if(e instanceof MenuNotFoundException) {
			jr.setState(4008);
			jr.setMessage(e.getMessage());
		}else if(e instanceof RoleNotFoundException) {
			jr.setState(4009);
			jr.setMessage(e.getMessage());
		}
		
		return jr;
	}

}
