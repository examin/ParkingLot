package com.gojek.parkinglot.util;

public final class TestUtil {

	public static String CREATE_COMMAND = "create_parking_lot 6";
	public static String INVALID_CREATE_COMMAND = "create_parking_lot";
	public static String PARK_COMMAND = "park KA-01-HH-1234 White";
	public static String LEAVE_COMMAND = "leave 4";
	public static String FETCH_CAR_FROM_COLOR_COMMAND = "registration_numbers_for_cars_with_colour White";
	public static String FETCH_SLOT_FROM_COLOR_COMMAND = "slot_numbers_for_cars_with_colour White";
	public static String FETCH_SLOT_FROM_REG_NO_COMMAND = "slot_number_for_registration_number KA-01-HH-3141";
	public static String STATUS_COMMAND = "status";
	public static String EXIT_COMMAND = "exit";

	private TestUtil() {
	}

}
