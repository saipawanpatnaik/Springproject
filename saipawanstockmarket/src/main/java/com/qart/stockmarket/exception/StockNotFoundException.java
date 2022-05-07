package com.qart.stockmarket.exception;

public class StockNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6826458006323347174L;

	public StockNotFoundException(String message) {
		super(message);
	}

}
