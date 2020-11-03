package com.gojek.parkinglot.services.impl;

import com.gojek.parkinglot.services.ParkingStrategy;

import java.util.TreeSet;

/**
 * Implementation of {@link ParkingStrategy}
 * Returns first empty parking slot available
 */
public class NearestSlotFirstParkingStrategy implements ParkingStrategy {

	private final TreeSet<Integer> freeSlots;

	public NearestSlotFirstParkingStrategy() {
		this.freeSlots = new TreeSet<Integer>();
	}

	@Override
	public void addEmptySlot(int slotNumber) {
		freeSlots.add(slotNumber);
	}

	@Override
	public int getEmptySlot() {
		return freeSlots.size() > 0 ? freeSlots.first() : 0;
	}

	@Override
	public void removeEmptySlot(int slotNumber) {
		freeSlots.remove(slotNumber);
	}

	@Override
	public boolean isSlotAvailable(int slotNumber) {
		return freeSlots.contains(slotNumber);
	}
}
