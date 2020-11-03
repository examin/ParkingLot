package com.gojek.parkinglot;

import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.ParkingLotCommands;
import com.gojek.parkinglot.services.ParkingService;
import com.gojek.parkinglot.services.impl.ParkingServiceImpl;

public class InputProcessor {

	private final ParkingService parkingService;

	public InputProcessor() {
		parkingService = new ParkingServiceImpl();
	}

	void execute(String[] commandLine) throws ParkingException {
		String commandKey = commandLine[0];

		ParkingLotCommands commands = ParkingLotCommands.fromString(commandKey);
		if (!ParkingLotCommands.validate(commands, commandLine)) {
			return;
		}

		switch (commands) {
			case CREATE:
				int capacity = Integer.parseInt(commandLine[1]);
				parkingService.createParkingLot(capacity);
				break;
			case PARK:
				parkingService.park(new Car(commandLine[1], commandLine[2]));
				break;
			case LEAVE:
				int slotNumber = Integer.parseInt(commandLine[1]);
				parkingService.leave(slotNumber);
				break;
			case STATUS:
				parkingService.getParkingStatus();
				break;
			case FETCH_CAR_FROM_COLOR:
				parkingService.getRegNumberForColor(commandLine[1]);
				break;
			case FETCH_SLOT_FROM_COLOR:
				parkingService.getSlotNumbersFromColor(commandLine[1]);
				break;
			case FETCH_SLOT_FROM_REG_NO:
				parkingService.getSlotNoFromRegistrationNo(commandLine[1]);
				break;
			case EXIT:
				System.exit(0);
				break;
			default:
				break;
		}
	}
}