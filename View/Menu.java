/*
 * Menu class to handle all user views
 *
 * 02/10/2022
 * 
 * Copyright David Poiner - S3579878
 */

package View;

import Controller.*;

import java.util.Scanner;

public class Menu {

	private static String banner = new String(new char[50]).replace('\u0000', '-');

	/*
	 * Set Scanner class for repeated use int all code below
	 */
	public static String readUserInput() {
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	/*
	 * Set clearConsole class to tidy up the terminal after each new menu is loaded
	 */
	public static void clearConsole() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	/*
	 * Load main menu of Car booking program
	 */
	public static void main() {
		boolean exit = false;

		do {
			mainMenu();
			String stringInput = readUserInput();
			if (stringInput.isEmpty()) {
				System.out.println("Please make a selection:");
				continue;
			}

			char input = stringInput.charAt(0);

			switch (input) {
				case '1':
					brandSearch();
					break;
				case '2':
					typeSearch();
					break;
				case '3':
					passengerSearch();
					break;
				case '4':
					exit = true;
					System.out.println('\n' + "Goodbye!");
					System.exit(0);
					break;
				default:
					System.out.println("Please select a valid menu option.");
					break;
			}
		} while (!exit);
	}

	/*
	 * Main Menu display/format
	 */

	public static void mainMenu() {
		clearConsole();

		System.out.println("Welcome to MyCar!");
		System.out.println(banner + "\n" + "> Select from main menu" + "\n" + banner);
		System.out.printf("   %s%n", "1) Search by brand");
		System.out.printf("   %s%n", "2) Browse by vehicle type");
		System.out.printf("   %s%n", "3) Filter by number of passengers");
		System.out.printf("   %s%n", "4) Exit" + "\n");

		System.out.print("Please select an option: ");

	}

	/*
	 * Brand Menu Display
	 */
	public static void brandMenu() {
		clearConsole();

		System.out.println("Type 'back' to return to main menu." + "\n" + banner + "\n");
		System.out.println("Please provide a brand:");
	}

	/*
	 * Prompt user for brand name to search in Car model
	 */

	public static void brandSearch() {

		brandMenu();
		String stringInput = readUserInput();
		stringInput = ValidationController.letterInputValidation(stringInput);

		if (stringInput.isEmpty()) {
			System.out.println("Please provide a brand or type 'back' to return to the main menu:");
		}
		if (stringInput.equals("back")) {
			main();
		} else {
			brandResults(stringInput);
		}

	}

	/*
	 * Prompt user to select an option from the available brands
	 */

	public static void brandResults(String brand) {
		clearConsole();
		System.out.println("> Select option from list of Brands" + "\n" + banner + "\n");

		BookingController.searchBrand(brand);
	}

	/*
	 * Search car type display
	 */
	public static void typeMenu() {
		clearConsole();

		System.out.println(banner + "\n" + "> Browse by type of vehicle" + "\n" + banner);
		System.out.printf("   %s%n", "1) Sedan");
		System.out.printf("   %s%n", "2) Hatchback");
		System.out.printf("   %s%n", "3) SUV");
		System.out.printf("   %s%n", "4) Return to main menu" + "\n");

		System.out.print("Please Select:");

	}

	/*
	 * Prompt user to search for a car type in the Car model
	 */

	public static void typeSearch() {
		boolean exit = false;
		do {
			typeMenu();
			String stringInput = readUserInput();

			if (stringInput.isEmpty()) {
				System.out.println("Please make a selection:");
				continue;
			}

			char input = stringInput.charAt(0);

			switch (input) {
				case '1':
					TypeResults("Sedan");
					break;
				case '2':
					TypeResults("Hatch");
					break;
				case '3':
					TypeResults("SUV");
					break;
				case '4':
					exit = true;
					main();
					break;
				default:
					System.out.println("Please select a valid menu option.");
					break;
			}
		} while (!exit);
	}

	/*
	 * Print Car getType results for user
	 */

	public static void TypeResults(String Type) {
		clearConsole();
		System.out.println("> Select option from list of " + Type + "s" + "\n" + banner + "\n");
		BookingController.searchType(Type);

	}

	/*
	 * Search for cars based on passengers display
	 */

	public static void passengerMenu() {
		clearConsole();

		System.out.println("Type 'back' to return to main menu." + "\n" + banner + "\n");
		System.out.println("Please provide the number of passengers:");
	}

	/*
	 * Prompt user to enter the total number of passengers to search for cars
	 */
	public static void passengerSearch() {

		passengerMenu();
		String stringInput = readUserInput();

		if (stringInput.isEmpty()) {
			System.out
					.println("Please provide a valid number of passengers or type 'back' to return to the main menu:");
		}
		if (stringInput == "back") {
			main();
		}

		passengerResults(Integer.parseInt(stringInput));

	}

	/*
	 * Display results for cars with the seats the user needs.
	 */
	public static void passengerResults(int seats) {
		clearConsole();
		System.out.println("> Select option from list matching cars" + "\n" + banner + "\n");
		BookingController.searchSeats(seats);

		System.out.println("Please select:");
		String stringInput = readUserInput();

		if (stringInput.isEmpty()) {
			System.out.println("Please provide a valid entry or type 'back' to return to the main menu:");
		}
		if (stringInput == "back") {
			main();
		}
	}

	/*
	 * Menu for Dateisplay in booking process
	 */
	public static void dateMenu() {
		clearConsole();
		System.out.println("> Proivide dates for your booking:" + "\n" + banner + "\n");
	}

	/*
	 * Menu for vehicle Details in booking process
	 */
	public static void detailsMenu() {
		clearConsole();
		System.out.println("> Booking Details" + "\n" + banner + "\n");
	}

	/*
	 * Menu for personal Details in booking process
	 */
	public static void personalMenu() {
		clearConsole();
		System.out.println("> Provide personal information:" + "\n" + banner + "\n");
	}

	/*
	 * Menu for booking summary in booking process
	 */
	public static void receiptMenu() {
		clearConsole();
		System.out.println(">  Congratulations! Your vehicle is booked. A receipt has been sent to your email." + "\n");
		System.out.println("   We will soon be in touch before your pick-up date." + "\n" + banner + "\n");

	}

	/*
	 * validation that car booked suits users needs
	 */
	public static void seatCheckMenu() {
		clearConsole();
		System.out.println(
				"Apologies, but this car does not have enough seats for the total passengers indicated in your booking."
						+ "\n" + banner + "\n");

	}

}
