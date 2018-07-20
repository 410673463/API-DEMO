package com.ws.pojo;


/**
 * 返回格式的实体类
 * @author zhanghan
 *
 */
public class ResponseData {
	private boolean status;
	private int code;
	private String message;
	private String data;
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
}
