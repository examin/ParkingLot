package com.gojek.parkinglot.services;

import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.model.ParkingSpot;
import com.gojek.parkinglot.model.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ParkingService {

	void createParkingLot(int capacity) throws ParkingException;

	Optional<Integer> park(Vehicle vehicle) throws ParkingException;

	void leave(int slotNumber) throws ParkingException;

	Map<Integer, ParkingSpot> getParkingStatus() throws ParkingException;

	List<String> getRegNumberForColor(String color) throws ParkingException;

	List<Integer> getSlotNumbersFromColor(String color) throws ParkingException;

	int getSlotNoFromRegistrationNo(String registrationNo) throws ParkingException;

	void doCleanup();
}
