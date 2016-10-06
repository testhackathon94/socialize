package com.amazon.exceptions;

public class AmazonException extends Exception{

	public AmazonException(String message) {
		super(message);
	}

	public AmazonException(String message, Throwable e) {
		super(message, e);
	}
	
}
