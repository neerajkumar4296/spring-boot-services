package com.example.demo.customexception;


public class CustomAppException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomAppException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	
	public CustomAppException(String httpStatus, Throwable reason) {
		super(httpStatus, reason);
	}
	
	
	
	

}
