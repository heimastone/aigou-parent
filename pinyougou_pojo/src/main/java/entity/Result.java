package entity;

import java.io.Serializable;

public class Result implements Serializable{
	
	private boolean success;
	public Result(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	private String  message;
	
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

}
