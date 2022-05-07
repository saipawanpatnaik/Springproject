package com.qart.stockmarket.exception;

public class CompanyNotFoundException extends RuntimeException{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1385497459020630717L;

	public CompanyNotFoundException(String message) {
		super(message);
	}

}