package com.atguigu.gmall.common.bean;

import lombok.Data;

/**
 * 返回数据
 */
@Data
public class ResponseVo<T> {
	private static final long serialVersionUID = 1L;
	private Integer code;
	private String msg;
	private T data;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public static ResponseVo fail() {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setCode(1);
		responseVo.setMsg("服务器错误！！！");
		return responseVo;
	}

	public static ResponseVo fail(String msg) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setCode(1);
		responseVo.setMsg(msg);
		return responseVo;
	}

	public static <T> ResponseVo<T> ok(T data) {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setCode(0);
		responseVo.setData(data);
		return responseVo;
	}

	public static ResponseVo ok() {
		ResponseVo responseVo = new ResponseVo();
		responseVo.setCode(0);
		responseVo.setMsg("操作成功！");
		return responseVo;
	}

}
