package com.amazon.exceptions;

public class ActionMappingNotFoudException extends AmazonException{

	public ActionMappingNotFoudException(String message) {
		super(message);
	}

	public ActionMappingNotFoudException(String message, Throwable e) {
		super(message, e);
	}
	

}
