package com.qart.stockmarket.exception;

public class InvalidStockException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7798230836045624890L;

	public InvalidStockException(String message) {
		super(message);
	}

}
