package com.ibgregorio.capbank.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer httpStatus;
	private String msg;
	private Long timeStamp;
	
	public StandardError(Integer httpStatus, String msg, Long timeStamp) {
		super();
		this.httpStatus = httpStatus;
		this.msg = msg;
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return httpStatus;
	}

	public void setStatus(Integer httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
}
