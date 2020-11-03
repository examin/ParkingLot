package com.gojek.parkinglot;

import com.gojek.parkinglot.model.Car;
import com.gojek.parkinglot.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Vehicle
 */
public class VehicleTest {
	private Vehicle vehicle;

	/**
	 * Setup for other tests.
	 */
	@Before
	public void init() {
		vehicle = new Car("KA-01-HH-2701", "WHITE");
	}
	/**
	 * Test for getting the license plate number.
	 */
	@Test
	public void getLicensePlate() {
		Assert.assertEquals(vehicle.getRegNumber(), "KA-01-HH-2701");
	}


}
