package com.gojek.parkinglot.exception;

public class ParkingException extends RuntimeException {

	private String errorCode = null;    // System defined error codes

	private Object[] errorParameters = null;    // Parameters for error code/message

	public ParkingException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ParkingException(String message) {
		super(message);
	}

}
