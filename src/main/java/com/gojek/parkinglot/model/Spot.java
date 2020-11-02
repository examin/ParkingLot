package com.gojek.parkinglot.model;

import com.gojek.parkinglot.exception.ParkingException;

/**
 * An interface for a parking spot that provides space for vehicles to be parked.
 */
public interface Spot {

	/**
	 * Get the id of the parking spot.
	 *
	 * @return the id of the parking spot.
	 */
	int getId();

	/**
	 * Get the Vehicle that is currently parked in this parking spot.
	 *
	 * @return the Vehicle that is currently parked in this parking spot.
	 */
	Vehicle getCurrentVehicle();

	/**
	 * Get whether the parking spot is vacant.
	 *
	 * @return true if the parking spot is vacant.
	 */
	boolean isVacant();

	/**
	 * Park a vehicle in this parking spot.
	 *
	 * @param vehicle the vehicle to park.
	 * @throws ParkingException if the vehicle is unable to park in this parking spot.
	 */
	void parkVehicle(Vehicle vehicle) throws ParkingException;

	/**
	 * Remove the currently parked vehicle from this parking spot.
	 */
	void removeCurrentVehicle();
}
