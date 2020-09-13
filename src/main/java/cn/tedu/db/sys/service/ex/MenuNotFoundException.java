package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 使用menuId未查询到对应记录时抛出的异常
 */
public class MenuNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public MenuNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MenuNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public MenuNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public MenuNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public MenuNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
}
