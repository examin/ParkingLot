package com.gojek.parkinglot;

import com.gojek.parkinglot.ParkingLot;
import com.gojek.parkinglot.exception.ParkingException;
import org.junit.Test;

public class ParkingLotTest {

	@Test
	public void testParkingLot_WithValidFile() {
		String[] args = new String[1];
		args[0] = "src/test/resources/testInput.txt";
		ParkingLot.main(args);
	}

	@Test(expected = ParkingException.class)
	public void testParkingLot_WithMissingFile() {
		String[] args = new String[1];
		args[0] = "src/test/resources/noFile";
		try {
			ParkingLot.main(args);
		} catch (ParkingException e) {
			System.out.println(e.getLocalizedMessage());
			throw e;
		}

	}
}
