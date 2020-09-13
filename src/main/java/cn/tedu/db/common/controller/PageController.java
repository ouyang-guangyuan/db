package cn.tedu.db.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("/")
	public String findStarterPage(){
		return "starter";
	}
	
	@RequestMapping("/login_page")
	public String findloginPage(){
		return "login";
	}
	
	/**
	 * 统一处理用户对系统子页面的请求
	 * @param subPage 子页面名称
	 * @return 子页面路径
	 */
	@RequestMapping("/sys/{subPage}")
	public String getSubPage(@PathVariable("subPage")String subPage){
		return "sys/"+subPage;
	}
	
	@RequestMapping("/common/page") 
	public String getPage(){
		return "common/page";
	}

}
