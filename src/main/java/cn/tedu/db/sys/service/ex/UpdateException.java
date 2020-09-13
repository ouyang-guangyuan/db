package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 更新操作结束后，返回结果为0时抛出的异常
 */
public class UpdateException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public UpdateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UpdateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
