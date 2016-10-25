package com.exercise.bo;

/**
 * Created by User_2 on 10/25/2016.
 */
public class BusinessException extends RuntimeException {

	String msg;

	public BusinessException() {
		super();
	}

	public BusinessException(String msg) {
		super(msg);

	}
}
