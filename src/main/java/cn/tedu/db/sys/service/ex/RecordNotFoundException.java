package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 查询到的数据集合为null或者长度为0时抛出的异常
 */
public class RecordNotFoundException extends ServiceException {
	
	private static final long serialVersionUID = 1L;

	public RecordNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecordNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RecordNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RecordNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RecordNotFoundException(Throwable cause) {
		super(cause);
	}
	
}


