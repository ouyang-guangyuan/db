package cn.tedu.db.sys.service.ex;

import cn.tedu.db.common.ex.ServiceException;

/**
 * 使用角色id为查询到对应的角色记录时抛出的异常
 */
public class RoleNotFoundException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public RoleNotFoundException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RoleNotFoundException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
