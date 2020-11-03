package com.gojek.parkinglot.model;

import com.gojek.parkinglot.exception.ErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;

/**
 * ParkingSpot is space in parkingLot to park vehicles
 */
public class ParkingSpot implements Spot {
	private final int id;
	private Vehicle currentVehicle;

	/**
	 * Constructor for creating a new ParkingSpot.
	 *
	 * @param id the identifier of the parking spot.
	 */
	public ParkingSpot(int id) {
		this.id = id;
		this.currentVehicle = null;
	}

	/**
	 * Constructor for creating a new ParkingSpot.
	 *
	 * @param id the identifier of the parking spot.
	 */
	public ParkingSpot(int id, Vehicle vehicle) {
		this.id = id;
		this.currentVehicle = vehicle;
	}

	/**
	 * Get the id of this parking spot.
	 *
	 * @return the id of this parking spot.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Get the vehicle that occupies the parking spot.
	 *
	 * @return the Vehicle that is currently parked in this parking spot.
	 */
	public Vehicle getCurrentVehicle() {
		return currentVehicle;
	}

	/**
	 * Get whether the parking spot is vacant.
	 *
	 * @return true if no Vehicle is parked in this parking spot.
	 */
	public boolean isVacant() {
		return currentVehicle == null;
	}

	/**
	 * Park a vehicle in this parking spot.
	 *
	 * @param vehicle the vehicle to park.
	 * @throws ParkingException when vehicle is not allowed to park here.
	 */
	public void parkVehicle(Vehicle vehicle) throws ParkingException {
		if (!canPark(vehicle))
			throw new ParkingException(ErrorCodes.PROCESSING_ERROR.getMessage());
		currentVehicle = vehicle;
	}

	/**
	 * Remove the currently parked vehicle from this parking spot,
	 * if it is present in this parking spot.
	 */
	public void removeCurrentVehicle() {
		currentVehicle = null;
	}

	/**
	 * Can a vehicle park here?
	 *
	 * @param vehicle a vehicle.
	 * @return true if the vehicle can park here.
	 */
	private boolean canPark(Vehicle vehicle) {
		return isVacant();
	}

	/**
	 * Is the given object identical to this ParkingSpot?
	 *
	 * @param obj an object.
	 * @return true if the given object is identical to this ParkingSpot.
	 */
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof ParkingSpot)
				&& (((ParkingSpot) obj).id == this.id);
	}

	/**
	 * Get the hashCode of this parking spot.
	 *
	 * @return the hashCode of this parking spot.
	 */
	@Override
	public int hashCode() {
		return id;
	}
}
