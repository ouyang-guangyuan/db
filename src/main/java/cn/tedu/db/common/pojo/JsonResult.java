package cn.tedu.db.common.pojo;

/**
 * 用于封装服务器给浏览器的响应信息
 * @param <T> 返回的数据的类型
 */
public class JsonResult<T> {
	
	private Integer state;
	private String message;
	private T data;
	
	public JsonResult() {
	}

	public JsonResult(Integer state, String message) {
		this.state = state;
		this.message = message;
	}

	public JsonResult(Integer state, String message, T data) {
		this.state = state;
		this.message = message;
		this.data = data;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}

}
