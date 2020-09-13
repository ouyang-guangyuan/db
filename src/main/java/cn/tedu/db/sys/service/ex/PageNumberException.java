package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 分页查询中，页码为null或者小于1时抛出的异常
 */
public class PageNumberException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public PageNumberException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PageNumberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public PageNumberException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public PageNumberException(String message) {
		super(message);
	}

	public PageNumberException(Throwable cause) {
		super(cause);
	}
	
	

}
