package cn.tedu.db.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.tedu.db.sys.pojo.SysLogDO;

public interface SysLogMapper {
	
	/**
	 * 插入日志记录
	 * @param sysLogDO 日志记录
	 * @return 插入成功的条数
	 */
	int insertSysLog(SysLogDO sysLogDO);
	
	/**
	 * 基于条件删除日志记录
	 * 用于动态SQL的参数，必须添加@Param注解
	 * @param ids 要删除的日志的id
	 * @return 删除的数据的条数
	 */
	int deleteSysLog(@Param("ids")Integer[] ids);
	
	/**
	 *   基于条件查询日志记录条数
	 *   如果username为null或空字符串，则返回所有日志记录条数
	 *   如果一个参数要被用于动态SQL，则有2种方式：
	 * 1. 参数前加@Param注解   （推荐）
	 * 2. 在动态SQL中使用默认生成的变量名"_parameter" (不好记)
	 * @param username 用户名
	 * @return 日志条数
	 */
	int getRowCount(@Param("username")String username);

	/**
	 * 基于条件查询一页的日志数据
	 * @param username 用户名
	 * @param recordIndex 起始数据下标
	 * @param pageSize 数据条数
	 * @return 该页日志数据
	 */
	List<SysLogDO> listSysLog(@Param("username")String username,
							@Param("recordIndex")int recordIndex,
							@Param("pageSize")int pageSize);

}
