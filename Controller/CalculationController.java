/*
 * CalcuController class to handle the car booking process
 *
 * 02/10/2022
 * 
 * Copyright David Poiner - S3579878
 */

package Controller;

import java.text.*;
import java.util.*;

public class CalculationController {

    /*
     * This method calculates the days between pickup and return date
     * Set pickup and return time to 12 noon
     * Uses import java.text.SimpleDateFormat in calculation
     * Returns the number of days between dates to be used to calculate price
     */
    public static int compareDates(String pickupDate, String returnDate) {

        String pickupDateTime = pickupDate + " 12:00:00";
        String returnDateTime = returnDate + " 12:00:00";

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        try {
            Date date1 = format.parse(pickupDateTime);
            Date date2 = format.parse(returnDateTime);

            long timeDifference = date2.getTime() - date1.getTime();

            long daysDifference = (timeDifference / (1000 * 60 * 60 * 24)) % 365;
            int daysBetween = (int) daysDifference;

            return daysBetween;

        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        return 0;

    }

    /*
     * Calculate the total rental price on days and price per day
     */
    public static double calcRental(int perDay, int totalDays) {
        double cost = (perDay * totalDays);
        return cost;
    }

    /*
     * Calculate the total insurance cost on days and price per day
     */
    public static double calcInsurance(double insureCost, int totalDays) {
        double totalCost = (insureCost * totalDays);
        return totalCost;
    }

    /*
     * Calculate the total cost of the booking inclkuding insurance
     */
    public static double calcTotal(double rentalPrice, double totalInsure) {
        double totalPrice = rentalPrice + totalInsure;
        return totalPrice;

    }

    /*
     * Calculate the discount if a discount is listed against the selectedCar
     */
    public static double calcDiscount(String discount, double totalPrice) {

        int intDiscount = Integer.parseInt(discount);
        double totalDiscount = (totalPrice * intDiscount) / 100;
        double returnPrice = totalPrice - totalDiscount;
        return returnPrice;
    }

}
