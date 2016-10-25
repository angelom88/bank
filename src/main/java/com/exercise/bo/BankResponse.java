package com.exercise.bo;

/**
 * Created by User_2 on 10/24/2016.
 */
public class BankResponse {

	private String message;
	private Object data;
	private boolean success;

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

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public static final BankResponse success(Object data, String message) {
		BankResponse bankResponse = new BankResponse();
		bankResponse.setData(data);
		bankResponse.setMessage(message);
		bankResponse.setSuccess(true);

		return bankResponse;
	}

	public static final BankResponse success(String message) {
		BankResponse bankResponse = new BankResponse();
		bankResponse.setData("");
		bankResponse.setMessage(message);
		bankResponse.setSuccess(true);

		return bankResponse;
	}

	public static final BankResponse failed(String message) {
		BankResponse bankResponse = new BankResponse();
		bankResponse.setData("");
		bankResponse.setMessage(message);
		bankResponse.setSuccess(false);

		return bankResponse;
	}
}
