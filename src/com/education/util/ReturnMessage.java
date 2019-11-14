package com.education.util;

public class ReturnMessage<T>{
	private String MESSAGE = "false";
	private  int code;
	private T data;
	
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String mESSAGE) {
		this.MESSAGE = mESSAGE;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public T getData() {
		try {
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			setMESSAGE("false");
			setCode(MessageCode.ERROR_WRONG);
			return data;
		}
	}
	public void setData(T data) {
		try {
			this.data =data;
			setMESSAGE("true");
			setCode(MessageCode.SUCCESS);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			MESSAGE = "false";
			setCode(MessageCode.ERROR_WRONG);
		}
	}
}