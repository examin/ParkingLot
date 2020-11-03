package com.gojek.parkinglot;

import com.gojek.parkinglot.exception.ParkingException;
import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.ParkingSpot;
import com.gojek.parkinglot.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParkingSpotTest {

	private ParkingSpot parkingSpot;
	private Vehicle vehicle;

	/**
	 * Setup before the rest of the tests.
	 */
	@Before
	public void init() {
		parkingSpot = new ParkingSpot(1);
	}

	/**
	 * Test to get Id of the parking spot.
	 */
	@Test
	public void getId() {
		assertEquals(parkingSpot.getId(),1);
	}

	/**
	 * Test to get current vehicle in the parking spot.
	 */
	@Test
	public void getCurrentVehicle() {
		assertNull(parkingSpot.getCurrentVehicle());
	}

	/**
	 * Test to check if parking spot is vacant.
	 */
	@Test
	public void isVacant() {
		assertTrue(parkingSpot.isVacant());
	}

	/**
	 * Test to park vehicle.
	 *
	 * @throws ParkingException when vehicle cannot be parked in the parking spot.
	 */
	@Test
	public void parkVehicle() throws ParkingException {
		vehicle = new Car("KA-01-HH-1234","White");
		parkingSpot.parkVehicle(vehicle);
		assertEquals(parkingSpot.getCurrentVehicle(), vehicle);
		parkingSpot.removeCurrentVehicle();
		assertTrue(parkingSpot.isVacant());
	}

	/**
	 * Test equality of two parking spots.
	 */
	@Test
	public void equals() {
		ParkingSpot duplicateParkingSpot = new ParkingSpot(1);
		assertEquals(parkingSpot,duplicateParkingSpot);
	}

	/**
	 * Test weather returned hashcode.
	 */
	@Test
	public void testHashCode() {
		assertEquals(parkingSpot.hashCode(),1);
	}
}