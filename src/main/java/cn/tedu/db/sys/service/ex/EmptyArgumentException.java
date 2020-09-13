package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 传入业务层方法的参数为null或空字符串时抛出的异常
 */
public class EmptyArgumentException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public EmptyArgumentException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmptyArgumentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmptyArgumentException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public EmptyArgumentException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public EmptyArgumentException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
}
