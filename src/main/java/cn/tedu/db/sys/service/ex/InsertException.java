package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 添加操作时，返回的成功条数为0时抛出的异常
 */
public class InsertException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InsertException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsertException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InsertException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
