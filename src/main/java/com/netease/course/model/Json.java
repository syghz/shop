package com.netease.course.model;

public class Json {
	private int code;
	private String message;
	private boolean result;
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
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public Json(int code, String message, boolean result) {
		this.code = code;
		this.message = message;
		this.result = result;
	}
}
