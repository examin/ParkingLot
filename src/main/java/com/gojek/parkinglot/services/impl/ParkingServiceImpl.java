package com.gojek.parkinglot.services.impl;

import com.gojek.parkinglot.Constants;
import com.gojek.parkinglot.dao.ParkingDataAccessor;
import com.gojek.parkinglot.dao.impl.ParkingDataAccessorImpl;
import com.gojek.parkinglot.exception.ErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.model.ParkingSpot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.services.ParkingService;

import java.util.*;

public class ParkingServiceImpl implements ParkingService {

	private ParkingDataAccessor dataAccessor;

	/**
	 * Creates a parking lot with given capacity and {@link NearestSlotFirstParkingStrategy}
	 *
	 * @param capacity
	 * @throws ParkingException
	 */
	@Override
	public void createParkingLot(int capacity) throws ParkingException {
		if (capacity <= 0) {
			throw new ParkingException(ErrorCodes.INVALID_VALUE.getMessage().replace("{variable}", "capacity"));
		}
		if (Objects.nonNull(dataAccessor)) {
			throw new ParkingException(ErrorCodes.PARKING_ALREADY_EXIST.getMessage());
		}
		this.dataAccessor = ParkingDataAccessorImpl.getInstance(capacity, new NearestSlotFirstParkingStrategy());
		System.out.println("Created a parking lot with " + capacity + " slots");
	}


	@Override
	public Optional<Integer> park(Vehicle vehicle) throws ParkingException {
		validateParkingLot();
		Optional<Integer> value = Optional.empty();
		try {
			value = Optional.of(dataAccessor.parkVehicle(vehicle));
			if (value.get() == Constants.NOT_AVAILABLE)
				System.out.println("Sorry, parking lot is full");
			else if (value.get() == Constants.VEHICLE_ALREADY_EXIST)
				System.out.println("Sorry, vehicle is already parked.");
			else
				System.out.println("Allocated slot number: " + value.get());
		} catch (Exception e) {
			throw new ParkingException(ErrorCodes.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}


	@Override
	public void leave(int slotNumber) throws ParkingException {
		validateParkingLot();
		try {
			if (dataAccessor.leaveVehicle(slotNumber)) {
				System.out.println("Slot number " + slotNumber + " is free");
			} else {
				System.out.println("Slot number is empty already");
			}
		} catch (Exception e) {
			throw new ParkingException(ErrorCodes.INVALID_VALUE.getMessage().replace("{variable}", "slot_number"), e);
		}
	}

	@Override
	public Map<Integer, ParkingSpot> getParkingStatus() throws ParkingException {
		validateParkingLot();
		Map<Integer, ParkingSpot> slotVehicleMapping;
		try {
			slotVehicleMapping = dataAccessor.getSlotVehicleMapping();
			if (slotVehicleMapping.size() == 0) {
				System.out.println("Sorry, parking lot is empty.");
			} else {
				printParkingLotStatus(slotVehicleMapping);
			}
		} catch (Exception e) {
			throw new ParkingException(ErrorCodes.PROCESSING_ERROR.getMessage(), e);
		}
		return slotVehicleMapping;
	}

	private void printParkingLotStatus(Map<Integer, ParkingSpot> slotVehicleMapping) {
		System.out.println("Slot No.    Registration No    Colour");
		slotVehicleMapping.forEach((slot, parkingSpot) -> {
			System.out.println(slot + "           " + parkingSpot.getCurrentVehicle().getRegNumber() + "      " + parkingSpot.getCurrentVehicle().getColor());
		});
	}

	@Override
	public List<String> getRegNumberForColor(String color) throws ParkingException {
		validateParkingLot();
		List<String> registrationList;
		try {
			registrationList = dataAccessor.getRegNumberForColor(color);
			if (registrationList.size() == 0) {
				System.out.println(Constants.NOT_FOUND_STRING);
			} else {
				System.out.println(String.join(", ", registrationList));
			}
		} catch (Exception e) {
			throw new ParkingException(ErrorCodes.PROCESSING_ERROR.getMessage(), e);
		}
		return registrationList;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(String color) throws ParkingException {
		validateParkingLot();
		List<Integer> slotList;
		try {
			slotList = dataAccessor.getSlotNumbersFromColor(color);
			if (slotList.size() == 0)
				System.out.println(Constants.NOT_FOUND_STRING);
			StringJoiner joiner = new StringJoiner(", ");
			for (Integer slot : slotList) {
				joiner.add(slot + "");
			}
			System.out.println(joiner.toString());
		} catch (Exception e) {
			throw new ParkingException(ErrorCodes.PROCESSING_ERROR.getMessage(), e);
		}
		return slotList;
	}

	@Override
	public int getSlotNoFromRegistrationNo(String registrationNo) throws ParkingException {
		validateParkingLot();
		int value = -1;
		try {
			value = dataAccessor.getSlotNoFromRegistrationNo(registrationNo);
			System.out.println(value != -1 ? value : Constants.NOT_FOUND_STRING);
		} catch (Exception e) {
			throw new ParkingException(ErrorCodes.PROCESSING_ERROR.getMessage(), e);
		}
		return value;
	}

	private void validateParkingLot() throws ParkingException {
		if (dataAccessor == null) {
			throw new ParkingException(ErrorCodes.PARKING_NOT_EXIST_ERROR.getMessage());
		}
	}

	@Override
	public void doCleanup() {
		dataAccessor.doCleanup();
		dataAccessor = null;
	}
}
