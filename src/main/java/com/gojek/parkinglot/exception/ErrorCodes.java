package com.gojek.parkinglot.exception;

public enum ErrorCodes {
	PARKING_ALREADY_EXIST("Sorry, Parking lot already exists"),
	PARKING_NOT_EXIST_ERROR("Sorry, Car Parking Does not Exist"),
	INVALID_VALUE("%d value is incorrect"),
	INVALID_FILE("Invalid File"),
	PROCESSING_ERROR("Processing Error "),
	INVALID_REQUEST("Invalid Request");

	private final String message;

	ErrorCodes(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}