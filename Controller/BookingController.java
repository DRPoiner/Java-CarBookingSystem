/*
 * BookingController class to handle the car booking process
 *
 * 02/10/2022
 * 
 * Copyright David Poiner - S3579878
 */

package Controller;

import java.util.*;

import Model.Car;
import View.Menu;

public class BookingController {

    // Set instance of ArrayList and use getCars method to return the list of cars
    // in Fleet.csv
    private static ArrayList<Car> carList = FileController.getCars();

    /*
     * Set global private variables of the booking for all methods to access
     */
    private static Car selectedCar;
    private static String pickUpCarDate;
    private static String returnCarDate;
    private static String totalPassengers;
    private static String firstName;
    private static String lastName;
    private static String email;

    /*
     * Set public scanner method for use in other methods below.
     */
    public static String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /*
     * Search for a brand in the car model using the brand passed to the method from
     * the Menu class
     */
    public static void searchBrand(String brand) {

        ArrayList<Car> filteredCars = new ArrayList<Car>();
        boolean validBrand = false;
        boolean validOption = false;
        int count = 1;

        // Convert all Strings to lowercase to ensure the input can match the cars in
        // carList if it is a valid brand
        // This way it does not matter if a user searches for a 'toyota' or a 'Toyota'
        while (validBrand == false) {
            for (Car car : carList) {
                if (car.getBrand().toLowerCase().contains(brand.toLowerCase())) {
                    filteredCars.add(new Car(car.getVehicleID(), car.getBrand(), car.getModel(), car.getType(),
                            car.getYear(), car.getSeats(), car.getColor(), car.getRentalCost(), car.getInsureCost(),
                            car.getServiceFee(), car.getDiscount()));
                    validBrand = true;
                }
            }

            if (validBrand == true) {
                for (Car filteredCar : filteredCars) {
                    System.out.println(count + ")  " + filteredCar.getVehicleID() + " - " + filteredCar.getBrand() + " "
                            + filteredCar.getModel() + " " + filteredCar.getType() + " with " + filteredCar.getSeats()
                            + " seats.");
                    count++;
                }
            }

            if (filteredCars.isEmpty()) {
                System.out.println("Apologies, we do not stock that brand of car.");
                System.out.println("Please try searching for a different brand:"+ '\n');
                brand = readUserInput();
                System.out.println('\n');
            }

        }

        System.out.println(count + ") Return to main menu");

        System.out.println("Please select and option:");
        String stringInput = readUserInput();

        while (validOption == false) {
            stringInput = ValidationController.numberInputValidation(stringInput);

            if (((Integer.parseInt(stringInput)) <= 0) || ((Integer.parseInt(stringInput)) > count)) {
                System.out.println("Please enter a number between 1 and " + count);
                stringInput = readUserInput();
            } else {
                validOption = true;
            }

        }

        if ((Integer.parseInt(stringInput)) <= (filteredCars.size())) {
            validOption = true;
            int numberInput = (Integer.parseInt(stringInput)) - 1;
            selectedCar = filteredCars.get(numberInput);

            selectDates();
        }

        if ((Integer.parseInt(stringInput)) == count) {
            validOption = true;
            Menu.main();
        }

    }

    /*
     * Search for and display cars with a number of seats more than or equal to the
     * amount the user input in Menu class in the Car model, input these found
     * objects into a new ArrayList called filteredCars
     */
    public static void searchSeats(int seats) {

        ArrayList<Car> filteredCars = new ArrayList<Car>();
        boolean validOption = false;
        int count = 1;

        for (Car car : carList) {
            if (car.getSeats() >= seats) {
                filteredCars.add(new Car(car.getVehicleID(), car.getBrand(), car.getModel(), car.getType(),
                        car.getYear(), car.getSeats(), car.getColor(), car.getRentalCost(), car.getInsureCost(),
                        car.getServiceFee(), car.getDiscount()));
            }
        }

        for (Car filteredCar : filteredCars) {
            System.out.println(count + ")  " + filteredCar.getVehicleID() + " - " + filteredCar.getBrand() + " "
                    + filteredCar.getModel() + " " + filteredCar.getType() + " with " + filteredCar.getSeats()
                    + " seats.");
            count++;
        }

        if (filteredCars.isEmpty()) {
            System.out.println("Apologies, we do not have any vehicles to seat " + seats + " passengers.");
        }

        System.out.println(count + ") " + " Return to main menu");

        System.out.println("Please select and option:");
        String stringInput = readUserInput();

        while (validOption == false) {
            stringInput = ValidationController.numberInputValidation(stringInput);

            if (((Integer.parseInt(stringInput)) <= 0) || ((Integer.parseInt(stringInput)) > count)) {
                System.out.println("Please enter a number between 1 and " + count);
                stringInput = readUserInput();
            } else {
                validOption = true;
            }

        }

        if ((Integer.parseInt(stringInput)) <= (filteredCars.size())) {
            int numberInput = (Integer.parseInt(stringInput)) - 1;
            selectedCar = filteredCars.get(numberInput);

            selectDates();
        }

        if ((Integer.parseInt(stringInput)) == count) {
            validOption = true;
            Menu.main();
        }

    }

    /*
     * Search for car types in the Car model, input these found objects into a new
     * ArrayList called filteredCars
     * Print the filteredCars ArrayList to the user for selection.
     * Pass the selectedCar to the next stepo in booking (input dates)
     */
    public static void searchType(String Type) {

        ArrayList<Car> filteredCars = new ArrayList<Car>();
        boolean validOption = false;
        int count = 1;

        for (Car car : carList) {
            if (car.getType().equals(Type)) {
                filteredCars.add(new Car(car.getVehicleID(), car.getBrand(), car.getModel(), car.getType(),
                        car.getYear(), car.getSeats(), car.getColor(), car.getRentalCost(), car.getInsureCost(),
                        car.getServiceFee(), car.getDiscount()));
            }
        }

        for (Car filteredCar : filteredCars) {
            System.out.println(count + ")  " + filteredCar.getVehicleID() + " - " + filteredCar.getBrand() + " "
                    + filteredCar.getModel() + " " + filteredCar.getType() + " with " + filteredCar.getSeats()
                    + " seats.");
            count++;
        }
        System.out.println(count + ") " + " Return to main menu");

        System.out.println("Please select and option:");
        String stringInput = readUserInput();

        while (validOption == false) {
            stringInput = ValidationController.numberInputValidation(stringInput);

            if (((Integer.parseInt(stringInput)) <= 0) || ((Integer.parseInt(stringInput)) > count)) {
                System.out.println("Please enter a number between 1 and " + count);
                stringInput = readUserInput();
            } else {
                validOption = true;
            }

        }

        if ((Integer.parseInt(stringInput)) <= (filteredCars.size())) {
            int numberInput = (Integer.parseInt(stringInput)) - 1;
            selectedCar = filteredCars.get(numberInput);

            selectDates();
        }

        if ((Integer.parseInt(stringInput)) == count) {
            validOption = true;
            Menu.main();
        }

    }

    /*
     * Input the dates the user wants the car for.
     * Send the input dated to ValidationController to check input is valid
     * If valid, pass the selectedCar, pickup and return Date to the bookingDetails
     * method
     */
    public static void selectDates() {

        Menu.dateMenu();

        System.out.println("Please provide a pickup date (dd/mm/yyyy):");
        pickUpCarDate = readUserInput();
        pickUpCarDate = ValidationController.validPickUpDate(pickUpCarDate);

        System.out.println('\n' + "Please provide a return date (dd/mm/yyyy):");
        returnCarDate = readUserInput();
        returnCarDate = ValidationController.validReturnDate(pickUpCarDate, returnCarDate);

        bookingDetails();

    }

    /*
     * Booking details calculated the totals and printes a menu to the user based on
     * whether the selectedCar has a discount listed in the object.
     */
    public static void bookingDetails() {

        Menu.detailsMenu();

        boolean confirmBooking = false;
        String confirmQuit = "";
        int daysBetweenDates = CalculationController.compareDates(pickUpCarDate, returnCarDate);
        double rentalPrice = CalculationController.calcRental(selectedCar.getRentalCost(), daysBetweenDates);
        double totalInsure = CalculationController.calcInsurance(selectedCar.getInsureCost(), daysBetweenDates);
        double totalPrice = CalculationController.calcTotal(rentalPrice, totalInsure);
        double discountPrice = CalculationController.calcDiscount(selectedCar.getDiscount(), totalPrice);

        System.out.println("Vehicle: " + selectedCar.getVehicleID() + '\n' +
                "Brand: " + selectedCar.getBrand() + '\n' +
                "Model: " + selectedCar.getModel() + '\n' +
                "Type of Vehicle: " + selectedCar.getType() + '\n' +
                "Year of Manufacture: " + selectedCar.getYear() + '\n' +
                "No of Seats: " + selectedCar.getSeats() + '\n' +
                "Color: " + selectedCar.getColor() + '\n' +
                "Rental: " + rentalPrice + "(" + selectedCar.getRentalCost() + " * " + daysBetweenDates + " days)");

        if (selectedCar.getDiscount().equals("N/A")) {
            System.out.println("Discounted Price: " + totalPrice + " (Discount is not applicable)");
        } else {
            System.out.println(
                    "Discounted Price: " + discountPrice + " ( " + totalPrice + " - %" + selectedCar.getDiscount()
                            + " discount)");
        }

        System.out.println("Insurance: " + totalInsure + "(" + selectedCar.getInsureCost() + " * " + daysBetweenDates
                + " days)"
                + '\n' +
                "Service Fee: " + selectedCar.getServiceFee() + '\n' +
                "Discounted Price: " + selectedCar.getDiscount() + '\n');

        while (confirmBooking == false) {

            System.out.println('\n' + "Would you like to reserve the vehicle (Y/N)?");
            String stringInput = readUserInput();
            stringInput = ValidationController.yesNoValidation(stringInput);

            if (stringInput.equals("y")) {
                confirmBooking = true;
                try {
                    userDetails(totalPrice);
                } catch (InterruptedException e) {
                    confirmBooking = false;
                }
            }

            if (stringInput.equals("n")) {
                confirmQuit = ValidationController.confirmQuit();

                if (confirmQuit.equals("y")) {
                    confirmBooking = true;
                    Menu.main();
                }
                if (confirmQuit.equals("n")) {
                    confirmBooking = false;
                }

            }
        }
    }

    /*
     * Gather user details for the booking.
     * Pass all info to the receipt if user confirms booking.
     * All run validation check if the user inputs more passengers then total car
     * seats in the selected car.
     */

    public static void userDetails(double totalPrice) throws InterruptedException {

        boolean finaliseBooking = false;
        boolean validCarSeats = false;
        String confirmQuit = "";

        Menu.personalMenu();

        System.out.println('\n' + "Please provide your given name:");
        firstName = readUserInput();
        firstName = ValidationController.letterInputValidation(firstName);

        System.out.println('\n' + "Please provide yor surname:");
        lastName = readUserInput();
        lastName = ValidationController.letterInputValidation(lastName);

        System.out.println('\n' + "Please provide your email:");
        email = readUserInput();
        email = ValidationController.emailFormatValidation(email);

        System.out.println('\n' + "Please provide the total number of passengers:");
        totalPassengers = readUserInput();
        totalPassengers = ValidationController.numberInputValidation(totalPassengers);

        while (validCarSeats == false) {
            validCarSeats = ValidationController.seatValidation(selectedCar.getSeats(),
                    Integer.parseInt(totalPassengers));

            if (validCarSeats == false) {

                Menu.seatCheckMenu();

                System.out.println("1) Search for a suitable car using the passenger number you entered.");
                System.out.println('\n' + "2) Return to main menu.");
                System.out.println('\n' + "3) Proceed with booking." + '\n');

                System.out.println("Please choose a option from the menu above:");
                String input = readUserInput();

                if (input.equals("1")) {
                    validCarSeats = true;
                    finaliseBooking = true;
                    Menu.passengerResults(Integer.parseInt(totalPassengers));
                }

                if (input.equals("2")) {
                    validCarSeats = true;
                    finaliseBooking = true;
                    Menu.main();
                }

                if (input.equals("3")) {
                    validCarSeats = true;

                }

            }

        }

        while (finaliseBooking == false) {

            System.out.println('\n' + "Confirm booking and pay (Y/N)");
            String stringInput = readUserInput();
            stringInput = ValidationController.yesNoValidation(stringInput);

            if (stringInput.equals("y")) {
                finaliseBooking = true;
                receipt(totalPrice);
            }

            if (stringInput.equals("n")) {
                confirmQuit = ValidationController.confirmQuit();

                if (confirmQuit.equals("y"))
                    finaliseBooking = true;
                Menu.mainMenu();
                {

                }
                if (confirmQuit.equals("n")) {
                    finaliseBooking = false;
                }

            }
        }

    }

    /*
     * Print the booking receipt for the user.
     */
    public static void receipt(double totalPrice) throws InterruptedException {

        boolean restartOrQuit = false;

        Menu.receiptMenu();

        System.out.printf("Name: " + firstName + " " + lastName + '\n' +
                "Email: " + email + '\n' +
                "Your Vehicle: " + selectedCar.getVehicleID() + " - " + selectedCar.getBrand() + " "
                + selectedCar.getModel() + " " + selectedCar.getType() + " with " + selectedCar.getSeats() + " seats."
                + '\n' +
                "Number of Passengers: " + totalPassengers + '\n' +
                "Pickup Date: " + pickUpCarDate + '\n' +
                "Return Date: " + returnCarDate + '\n' +
                "Total Payment: " + totalPrice + '\n');

        while (restartOrQuit == false) {
            System.out.println('\n' + "1) Restart your booking ");
            System.out.println("2) Quit the program ");

            String stringInput = readUserInput();
            stringInput = ValidationController.numberInputValidation(stringInput);

            if (stringInput.equals("1")) {
                restartOrQuit = true;
                Menu.mainMenu();
            }

            if (stringInput.equals("2")) {
                restartOrQuit = true;
                System.out.println('\n' + "Thank you for renting with MyCar!");
                Thread.sleep(2000);
                Menu.clearConsole();
                System.exit(0);
            }

        }

    }

}
