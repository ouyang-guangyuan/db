package cn.tedu.db.sys.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.db.common.pojo.PageObjectVO;
import cn.tedu.db.sys.mapper.SysLogMapper;
import cn.tedu.db.sys.pojo.SysLogDO;
import cn.tedu.db.sys.service.ISysLogService;
import cn.tedu.db.sys.service.ex.DeleteException;
import cn.tedu.db.sys.service.ex.EmptyIdException;
import cn.tedu.db.sys.service.ex.NoRecordException;
import cn.tedu.db.sys.service.ex.PageNumberException;

@Service
public class SysLogServiceImpl implements ISysLogService {
	
	@Autowired
	SysLogMapper mapper;
	private Integer pageSize=2;// 指定每页的数据条数
	
	public void removeSysLog(Integer[] ids)throws EmptyIdException,DeleteException{
		// 判断ids是否为null或者长度为0
		if(ids==null || ids.length==0) {
			// 是：抛出EmptyIdException
			throw new EmptyIdException("请至少选择1条记录");
		}

		// 调用持久层方法，删除数据
		int row=0;
		try {
			row=mapper.deleteSysLog(ids);
		} catch (Exception e) {
			e.printStackTrace();// 给后台排bug使用的
			throw new DeleteException("删除操作出现异常，请联系管理员！");
		}
		// 判断受影响的行数是否为0
		if(row==0) {
			// 是：抛出DeleteException
			throw new DeleteException("删除失败，请联系管理员！");
		}
	}

	@Override
	public PageObjectVO<SysLogDO> findSysLog(String username, Integer currentPage)
			throws PageNumberException, NoRecordException {
		// 判断currentPage是否为null或<1
		if(currentPage==null || currentPage<1) {
			// 是：throw new PageNumberException
			throw new PageNumberException("当前页码错误");
		}

		// 调用持久层方法getRowCount，查询总数据条数
		int rowCount=mapper.getRowCount(username);
		// 判断rowCount是否为0
		if(rowCount==0) {
			// 是：throw new NoRecordException
			throw new NoRecordException("未查询到相关数据");
		}

		// 计算recordIndex=(currentPage-1)*pageSize
		int recordIndex=(currentPage-1)*pageSize;
		// 调用持久层方法listSysLog，查询当前页数据
		List<SysLogDO> pageRecords=mapper.listSysLog(username, recordIndex, pageSize);
		// 创建PageObjectVO对象
		PageObjectVO<SysLogDO> vo=new PageObjectVO<SysLogDO>();
		// 向PageObjectVO对象中添加数据
		vo.setRowCount(rowCount);
		vo.setPageCount((rowCount-1)/pageSize+1);
		vo.setCurrentPage(currentPage);
		vo.setPageSize(pageSize);
		vo.setPageRecords(pageRecords);
		// 返回PageObjectVO
		return vo;
	}

}



