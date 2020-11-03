package com.gojek.parkinglot.model;

public enum ParkingLotCommands {

	CREATE("create_parking_lot", 1),
	PARK("park", 2),
	LEAVE("leave", 1),
	STATUS("status", 0),
	FETCH_CAR_FROM_COLOR("registration_numbers_for_cars_with_colour", 1),
	FETCH_SLOT_FROM_COLOR("slot_numbers_for_cars_with_colour", 1),
	FETCH_SLOT_FROM_REG_NO("slot_number_for_registration_number", 1),
	EXIT("exit", 0);

	private final String name;
	private final int paramCount;

	ParkingLotCommands(String name, int paramCount) {
		this.name = name;
		this.paramCount = paramCount;
	}

	public static ParkingLotCommands fromString(String text) {
		for (ParkingLotCommands command : ParkingLotCommands.values()) {
			if (text.equals(command.getName())) {
				return command;
			}
		}
		throw new IllegalArgumentException("Unknown Command: " + text);
	}

	public static boolean validate(ParkingLotCommands command, String[] commandLine) {
		if (command.getParamCount() >= commandLine.length) {
			System.out.println("Insufficient parameters provided");
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public int getParamCount() {
		return paramCount;
	}
}
