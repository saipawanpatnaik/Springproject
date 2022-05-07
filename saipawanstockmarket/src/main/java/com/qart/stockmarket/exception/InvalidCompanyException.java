package com.qart.stockmarket.exception;

public class InvalidCompanyException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5767042651963989131L;

	public InvalidCompanyException(String message)	{
		super(message);
	}

}
