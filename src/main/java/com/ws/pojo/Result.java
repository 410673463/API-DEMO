package com.ws.pojo;


/**
 * 返回格式的实体类
 * @author zhanghan
 *
 */
public class Result {
	private boolean status;
	private int code;
	private String message;
	private Object data;
	
	public boolean getStatus() {
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setStatus(true);
        return result;
    }

    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        result.setStatus(true);
        return result;
    }

    public static Result failure(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setStatus(false);
        return result;
    }

    public static Result failure(ResultCode resultCode, Object data) {
        Result result = new Result();
        result.setResultCode(resultCode);
        result.setData(data);
        result.setStatus(false);
        return result;
    }

    public void setResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }
}
