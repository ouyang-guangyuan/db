package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 删除业务中，传入的id数组为null或者长度为0时抛出的异常
 * 或者传入的id值为null或id值小于1时抛出的异常
 */
public class EmptyIdException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public EmptyIdException() {
		super();
	}

	public EmptyIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EmptyIdException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmptyIdException(String message) {
		super(message);
	}

	public EmptyIdException(Throwable cause) {
		super(cause);
	}

	
}
