package cn.tedu.db.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.db.common.controller.BaseController;
import cn.tedu.db.common.pojo.JsonResult;
import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.pojo.SysLogDO;
import cn.tedu.db.sys.service.ISysLogService;

@RestController
@RequestMapping("/log")
public class SysLogController extends BaseController {
	
	@Autowired
	ISysLogService service;
	
	@PostMapping("/delete")
	public JsonResult<Void> removeSysLog(Integer[] ids){
		service.removeSysLog(ids);
		return new JsonResult<Void>(STATE_SUCCESS,MSG_SUCCESS);
	}

	@GetMapping("/findSysLog")
	public JsonResult<PageObjectVO<SysLogDO>> findSysLog(String username,Integer currentPage){
		// 调用业务层方法，获取日志数据
		PageObjectVO<SysLogDO> vo=service.findSysLog(username, currentPage);
		// 返回日志数据
//		JsonResult<PageObjectVO> jr=new JsonResult<PageObjectVO>();
//		jr.setState(20);
//		jr.setMessage("OK");
//		jr.setData(vo);
		return new JsonResult<PageObjectVO<SysLogDO>>(STATE_SUCCESS, MSG_SUCCESS, vo);
	}
	
}




