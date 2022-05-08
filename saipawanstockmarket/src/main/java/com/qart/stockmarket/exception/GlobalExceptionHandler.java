package com.qart.stockmarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CompanyNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(CompanyNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidCompanyException.class)
	public ResponseEntity<ExceptionResponse> handler(InvalidCompanyException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidStockException.class)
	public ResponseEntity<ExceptionResponse> handler(InvalidStockException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(StockNotFoundException.class)
	public ResponseEntity<ExceptionResponse> handler(StockNotFoundException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidDateException.class)
	public ResponseEntity<ExceptionResponse> handler(InvalidDateException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataExistingException.class)
	public ResponseEntity<ExceptionResponse> handler(DataExistingException ex) {
		ExceptionResponse exception = new ExceptionResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(exception,
				HttpStatus.BAD_REQUEST);
	}
}
