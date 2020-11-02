package com.gojek.parkinglot.dao;

import com.gojek.parkinglot.model.ParkingSpot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.List;
import java.util.Map;

public interface ParkingDataAccessor {

	int parkVehicle(Vehicle vehicle);

	boolean leaveVehicle(int slotNumber);

	Map<Integer, ParkingSpot> getSlotVehicleMapping();

	List<String> getRegNumberForColor(String color);

	List<Integer> getSlotNumbersFromColor(String colour);

	int getSlotNoFromRegistrationNo(String registrationNo);

	void doCleanup();
}