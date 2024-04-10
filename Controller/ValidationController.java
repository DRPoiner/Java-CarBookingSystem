/*
 * ValidationController class to handle all input validation from BookingController
 *
 * 02/10/2022
 * 
 * Copyright David Poiner - S3579878
 */

package Controller;

import java.time.LocalDate;
import java.time.format.*;
import java.util.*;

public class ValidationController {

    /*
     * Set a private String to save the pick up date for use in validReturnDate
     * method
     */
    private static String carPickUpDate;

    /*
     * Scanner method to read user input
     */
    public static String readUserInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /*
     * Method to validate the user input is a number using Regex in a while loop
     * Prompts user to try again is Regex conditions are not met
     */
    public static String numberInputValidation(String input) {

        boolean validNumber = false;

        while (validNumber == false) {
            if (input.matches("[1-9][0-9]*")) {
                validNumber = true;
            }
            if ((validNumber == false) || (input.isEmpty())) {
                System.out.println("Please enter a valid number:");
                input = readUserInput();
            }
        }

        return input;

    }

    /*
     * Method to validate the user input is a letter or series of letters using
     * Regex in a while loop
     * Prompts user to try again is Regex conditions are not met
     */
    public static String letterInputValidation(String input) {

        boolean validLetters = false;

        while (validLetters == false) {
            if (input.matches("^[a-zA-Z]*$")) {
                validLetters = true;
            }
            if ((validLetters == false) || (input.isEmpty())) {
                System.out.println("Please only enter letters:");
                input = readUserInput();
            }
        }

        return input;

    }

    /*
     * Method to validate the user input is a Y or N to agree or disagree using
     * Regex in a while loop
     * Prompts user to try again is Regex conditions are not met
     * Any case is accepted
     */
    public static String yesNoValidation(String input) {

        boolean validYesNo = false;

        while (validYesNo == false) {

            if (input.matches("^(?:Y|y|N|n)$")) {
                validYesNo = true;
            }
            if ((validYesNo == false) || (input.isEmpty())) {
                System.out.println("Please enter either a 'Y' or 'N':");
                input = readUserInput();
            }

        }

        return input;

    }

    /*
     * Method to validate that the email is in X@XXX.XX (.XX.XX.XX.XX) using Regex
     * in a while loop
     * Method will accept most email formats from 1@1.com to
     * S3579878@student.rmit.edu.au
     * Prompts user to try again is Regex conditions are not met
     */
    public static String emailFormatValidation(String input) {

        boolean validEmail = false;

        while (validEmail == false) {
            if (input.matches(
                    "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}")) {
                validEmail = true;
            }
            if ((validEmail == false) || (input.isEmpty())) {
                System.out.println("Your email must be in the format of X@XXX.com");
                input = readUserInput();
            }
        }

        return input;

    }

    /*
     * Method to validate the input date is in the valid dd/MM/yyyy format
     * Prompts user to try again is Regex conditions are not met
     */
    public static String dateFormatValidation(String date) {

        boolean validDateFormat = false;

        while (validDateFormat == false) {
            try {
                LocalDate.parse(date,
                        DateTimeFormatter.ofPattern("dd/MM/uuuu")
                                .withResolverStyle(ResolverStyle.STRICT));
                validDateFormat = true;
            } catch (DateTimeParseException e) {
                System.out.println("Please enter a date in the format of dd/mm/yyyy");
                date = readUserInput();
            }
        }

        return date;
    }

    /*
     * Method to validate the pick up date is not in the past from todays date
     * Prompts user to try again is Regex conditions are not met
     */
    public static String validPickUpDate(String date) {

        boolean validPickUpDateInput = false;

        date = dateFormatValidation(date);

        while (validPickUpDateInput == false) {
            validPickUpDateInput = pickUpDateValidation(date);
            if (validPickUpDateInput == false) {
                System.out.println("Please enter a date in the future (dd/mm/yyyy):");
                date = readUserInput();
            }
        }

        carPickUpDate = date;
        return date;

    }

    /*
     * Method to convert the pick up date from string format to LocalDate format of
     * dd/MM/yyyy
     * Then compares this date to LocalDate today to ensure the pick up date is not
     * in the past
     */
    public static boolean pickUpDateValidation(String date) {

        LocalDate today = LocalDate.now();
        LocalDate pickUpCarLocalFormat = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (pickUpCarLocalFormat.isAfter(today)) {
            return true;

        } else {
            return false;
        }

    }

    /*
     * Method to validate the return date is after the pickup date
     * Prompts user to try again is Regex conditions are not met
     */
    public static String validReturnDate(String pickUpCarDate, String returnDate) {

        boolean validReturnDateInput = false;

        returnDate = dateFormatValidation(returnDate);

        while (validReturnDateInput == false) {
            validReturnDateInput = returnDateValidation(pickUpCarDate, returnDate);
            if (validReturnDateInput == false) {
                System.out.println("Please enter a date after the pickup date (dd/mm/yyyy):");
                returnDate = readUserInput();
            }
        }

        return returnDate;

    }

    /*
     * Method to convert the return date from string format to LocalDate format of
     * dd/MM/yyyy
     * Then compares return date to the pickup date to ensure the return date after
     * the pickup date
     */
    public static boolean returnDateValidation(String pickUpCarDate, String returnCarDate) {

        LocalDate pickUpCarLocalFormat = LocalDate.parse(carPickUpDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate returnCarLocalFormat = LocalDate.parse(returnCarDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        if (returnCarLocalFormat.isAfter(pickUpCarLocalFormat)) {
            return true;
        } else {
            return false;
        }

    }

    /*
     * Boolean validation to ensure the selected car has enough seats from the
     * UserDetails() method in BookingController class
     * Prompts user to try again if they enter 0 passengers
     */
    public static boolean seatValidation(int selectedCarSeats, int totalPassengers) {

        boolean catchZero = false;

        while (catchZero == false) {

            if (selectedCarSeats <= 0) {
                System.out.println("Please enter a number more then zero (the driver will count as a passenger):");
                String input = readUserInput();
                selectedCarSeats = Integer.parseInt(input);
            }
            if (selectedCarSeats > 0) {
                catchZero = true;
            }

        }

        if (totalPassengers >= selectedCarSeats) {
            return false;
        } else {
            return true;
        }

    }

    /*
     * Method to confirm a user wants to abandon their booking or deny to make
     * payment in Booking Controller
     */
    public static String confirmQuit() {

        String confirmExit = "";

        System.out.println('\n' + "Are you sure? (Y/N)");
        String userConfirm = readUserInput();
        userConfirm = yesNoValidation(userConfirm).toLowerCase();

        if (userConfirm.equals("y")) {
            confirmExit = "y";
        }

        if (userConfirm.equals("n")) {
            confirmExit = "n";
        }

        return confirmExit;
    }

}
