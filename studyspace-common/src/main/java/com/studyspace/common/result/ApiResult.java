package com.studyspace.common.result;
import java.io.Serializable;

public class ApiResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 成功标志
	 */
	private boolean success = true;

	/**
	 * 返回处理消息
	 */
	private String message = "操作成功！";

	/**
	 * 返回代码
	 */
	private Integer code = 0;

	/**
	 * 返回数据对象 data
	 */
	private T data;

	/**
	 * 时间戳
	 */
	private long timestamp = System.currentTimeMillis();

	public ApiResult() {
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * 成功返回（自定义消息）
	 */
	public ApiResult<T> success(String message) {
		this.message = message;
		this.code = 200;
		this.success = true;
		return this;
	}

	/**
	 * 成功返回（带数据）
	 */
	public ApiResult<T> success(T data) {
		this.data = data;
		this.code = 200;
		this.success = true;
		return this;
	}

	/**
	 * 成功返回（带数据和消息）
	 */
	public ApiResult<T> success(T data, String message) {
		this.data = data;
		this.message = message;
		this.code = 200;
		this.success = true;
		return this;
	}

	/**
	 * 默认成功返回
	 */
	public static ApiResult<Object> ok() {
		ApiResult<Object> r = new ApiResult<>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage("成功");
		return r;
	}

	/**
	 * 自定义消息的成功返回
	 */
	public static ApiResult<Object> ok(String msg) {
		ApiResult<Object> r = new ApiResult<>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		return r;
	}

	/**
	 * 带数据的成功返回
	 */
	public static ApiResult<Object> ok(Object data) {
		ApiResult<Object> r = new ApiResult<>();
		r.setSuccess(true);
		r.setCode(200);
		r.setData(data);
		return r;
	}

	/**
	 * 带数据和消息的成功返回
	 */
	public static ApiResult<Object> ok(Object data, String msg) {
		ApiResult<Object> r = new ApiResult<>();
		r.setSuccess(true);
		r.setCode(200);
		r.setMessage(msg);
		r.setData(data);
		return r;
	}

	/**
	 * 登录成功返回
	 */
	public static ApiResult<Object> loginSuccess(Object userData) {
		return ok(userData, "登录成功");
	}

	/**
	 * 注册成功返回
	 */
	public static ApiResult<Object> registerSuccess() {
		return ok("注册成功");
	}

	/**
	 * 默认错误返回
	 */
	public static ApiResult<Object> error(String msg) {
		return error(500, msg);
	}

	/**
	 * 自定义错误码的错误返回
	 */
	public static ApiResult<Object> error(int code, String msg) {
		ApiResult<Object> r = new ApiResult<>();
		r.setCode(code);
		r.setMessage(msg);
		r.setSuccess(false);
		return r;
	}

	/**
	 * 登录失败返回
	 */
	public static ApiResult<Object> loginError(String msg) {
		return error(401, msg);
	}

	/**
	 * 注册失败返回
	 */
	public static ApiResult<Object> registerError(String msg) {
		return error(400, msg);
	}

	/**
	 * 服务器错误返回
	 */
	public ApiResult<T> error500(String message) {
		this.message = message;
		this.code = 500;
		this.success = false;
		return this;
	}

	/**
	 * 无权限访问返回结果
	 */
	public static ApiResult<Object> noauth(String msg) {
		return error(403, msg);
	}

	/**
	 * 参数错误返回
	 */
	public static ApiResult<Object> paramError(String msg) {
		return error(400, msg);
	}
}