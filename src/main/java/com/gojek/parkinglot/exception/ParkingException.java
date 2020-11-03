package com.gojek.parkinglot.exception;

public class ParkingException extends RuntimeException {

	private final String errorCode = null;    // System defined error codes

	private final Object[] errorParameters = null;    // Parameters for error code/message

	public ParkingException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public ParkingException(String message) {
		super(message);
	}

}
