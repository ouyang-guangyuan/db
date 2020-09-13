package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 分页查询中，查询到的总记录数为0时抛出的异常
 */
public class NoRecordException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public NoRecordException() {
		super();
	}

	public NoRecordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public NoRecordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public NoRecordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public NoRecordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
