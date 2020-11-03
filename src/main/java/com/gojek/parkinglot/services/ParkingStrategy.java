package com.gojek.parkinglot.services;

/**
 * Interface defining vehicle parking strategy
 */
public interface ParkingStrategy {

	void addEmptySlot(int slotNumber);

	int getEmptySlot();

	void removeEmptySlot(int slotNumber);

	boolean isSlotAvailable(int slotNumber);
}
