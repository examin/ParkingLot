# Parking Lot : 

This repository contains an implementation of a parking lot. The system allows cars to be parked and removed from the parking lot.

#### Parking Slot Allocation Stategy :
The customer will be allocated a parking slot which is nearest to the entry.



## Getting Started

Here are the instructions for you to set the project up and run in your local machine for testing purposes.

### System Requirements

You will probably need to run these scripts on a Linux box and require:

			
	- Java 8
	- Gradle
	- Junit 4.12
    
*  `create_parking_lot n` - Given n number of slots, create a parking lot
*  `park "CAR REGISTRATION NUMBER" "Car Color"` - Parks a vehicle with given registration number and color in the nearest empty slot possible. If there are no more empty slots available, it shows a message "Sorry, parking lot is full".
*  `status` - Prints the slot number, registration number and color of the parked vehicles.
*  `leave 'SLOT NUMBER'` - release slot at slot number
*  There are few query functions to retrieve slot number from registration number of car, get registration numbers of cars with particular color etc.
* `exit` exit software

### To install all dependencies, compile and run tests:

Run the command bin/setup to install the build the project (Java, Gradle assumed to be pre-installed on the system)

			
		$ bin/setup


### To run the program and launch the shell:


		$ bin/parking_lot




