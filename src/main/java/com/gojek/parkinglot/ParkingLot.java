package com.gojek.parkinglot;

import com.gojek.parkinglot.exception.ErrorCodes;
import com.gojek.parkinglot.exception.ParkingException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParkingLot {

	public static void main(String[] args) {
		Scanner scanner = null;
		String line;
		final InputProcessor inputProcessor = new InputProcessor();
		try {
			if (args.length > 0) {
				String fileName = args[0];
				File inputFile = new File(fileName);
				scanner = new Scanner(inputFile);
			} else {
				scanner = new Scanner(System.in);
			}
		} catch (FileNotFoundException ex) {
			throw new ParkingException(ErrorCodes.INVALID_FILE.getMessage());
		}
		while (scanner.hasNextLine()) {
			line = scanner.nextLine().trim();
			if (line.isEmpty()) {
				continue;
			}
			String[] commandLine = line.split(" ");
			try {
				inputProcessor.execute(commandLine);
			} catch (Exception exception) {
				System.out.println(exception.getLocalizedMessage());
			}
		}
	}
}
