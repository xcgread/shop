package com.xuzhihao.shop.common.enmu;

import com.xuzhihao.shop.common.api.IErrorCode;

/**
 * 错误码封装
 * 
 * @author Administrator
 *
 */
public enum ResultCode implements IErrorCode {
	SUCCESS(200, "操作成功"), FAILED(500, "操作失败"),
	/* APP */
	UNAUTHORIZED(401, "暂未登录或token已经过期"), VALIDATE_FAILED(404, "参数检验失败"), FORBIDDEN(403, "没有相关权限"),
	SUBREPEAT(406, "重复提交"),
	/* SOAP */
	UNREGISTERED(601, "函数注册错误"), MSGFORMATERROR(602, "报文格式错误");

	private long code;
	private String message;

	private ResultCode(long code, String message) {
		this.code = code;
		this.message = message;
	}

	public long getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
