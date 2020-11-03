package com.gojek.parkinglot.dao.impl;

import com.gojek.parkinglot.Constants;
import com.gojek.parkinglot.dao.ParkingDataAccessor;
import com.gojek.parkinglot.exception.ErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.model.ParkingSpot;
import com.gojek.parkinglot.model.Vehicle;
import com.gojek.parkinglot.services.ParkingStrategy;

import java.util.*;

public class ParkingDataAccessorImpl implements ParkingDataAccessor {

	private static ParkingDataAccessorImpl dataAccessorInstance;
	private int parkingCapacity;
	private ParkingStrategy parkingStrategy;
	private Map<Integer, ParkingSpot> slotVehicleMapping;
	private Map<Integer, String> slotRegNumberMapping;

	private ParkingDataAccessorImpl(int capacity, ParkingStrategy parkingStrategy) throws ParkingException {
		if (Objects.isNull(parkingStrategy)) {
			throw new ParkingException(ErrorCodes.PROCESSING_ERROR.getMessage());
		}
		this.parkingCapacity = capacity;
		this.parkingStrategy = parkingStrategy;
		this.slotVehicleMapping = new HashMap<>();
		this.slotRegNumberMapping = new HashMap<>();
		for (int i = 1; i <= capacity; i++) {
			parkingStrategy.addEmptySlot(i);
		}
	}

	public static ParkingDataAccessorImpl getInstance(int capacity, ParkingStrategy parkingStrategy)
			throws ParkingException {
		if (dataAccessorInstance == null) {
			synchronized (ParkingDataAccessorImpl.class) {
				if (dataAccessorInstance == null) {
					dataAccessorInstance = new ParkingDataAccessorImpl(capacity, parkingStrategy);
				}
			}
		}
		return dataAccessorInstance;
	}

	@Override
	public int parkVehicle(Vehicle vehicle) {
		int availableSlot;
		if (parkingStrategy.getEmptySlot() == 0) {
			return Constants.NOT_AVAILABLE;
		}
		if (slotRegNumberMapping.containsValue(vehicle.getRegNumber())) {
			return Constants.VEHICLE_ALREADY_EXIST;
		}
		availableSlot = parkingStrategy.getEmptySlot();
		slotVehicleMapping.put(availableSlot, new ParkingSpot(availableSlot, vehicle));
		slotRegNumberMapping.put(availableSlot,vehicle.getRegNumber());
		parkingStrategy.removeEmptySlot(availableSlot);

		return availableSlot;
	}

	@Override
	public boolean leaveVehicle(int slotNumber) {
		// Slot already empty
		if (!slotVehicleMapping.containsKey(slotNumber))
			return false;
		parkingStrategy.addEmptySlot(slotNumber);
		slotVehicleMapping.remove(slotNumber);
		return true;
	}

	@Override
	public Map<Integer, ParkingSpot> getSlotVehicleMapping() {
		return slotVehicleMapping;
	}

	@Override
	public List<String> getRegNumberForColor(String color) {
		List<String> statusList = new ArrayList<>();
		slotVehicleMapping.values().stream().forEach(spot -> {
			if (color.equalsIgnoreCase(spot.getCurrentVehicle().getColor())) {
				statusList.add(spot.getCurrentVehicle().getRegNumber());
			}
		});
		return statusList;
	}

	@Override
	public List<Integer> getSlotNumbersFromColor(String colour) {
		List<Integer> slotList = new ArrayList<>();
		for (Map.Entry<Integer, ParkingSpot> entry : slotVehicleMapping.entrySet()) {
			Integer slotNumber = entry.getKey();
			ParkingSpot parkingSpot = entry.getValue();
			if (colour.equalsIgnoreCase(parkingSpot.getCurrentVehicle().getColor())) {
				slotList.add(slotNumber);
			}
		}
		return slotList;
	}

	@Override
	public int getSlotNoFromRegistrationNo(String registrationNo) {
		int slotNumber = Constants.NOT_FOUND;
		for (Map.Entry<Integer, ParkingSpot> parkingEntry : slotVehicleMapping.entrySet()) {
			if (registrationNo.equalsIgnoreCase(parkingEntry.getValue().getCurrentVehicle().getRegNumber())) {
				slotNumber = parkingEntry.getKey();
				break;
			}
		}
		return slotNumber;
	}

	@Override
	public void doCleanup() {
		this.parkingCapacity = 0;
		this.parkingStrategy = null;
		slotVehicleMapping = null;
		dataAccessorInstance = null;
	}
}
