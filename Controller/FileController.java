/*
 * FileController class to read the FLeet.csv file and input values in an ArrayList.
 *
 * 02/10/2022
 * 
 * Copyright David Poiner - S3579878
 */

package Controller;

import java.io.*;
import java.util.ArrayList;

import Model.Car;

public class FileController {

    /*
     * Set private ArrayList for Fleet.csv carlist
     */
    private static ArrayList<Car> carList = new ArrayList<Car>();

    /*
     * Method to return the car list when called from BookingController
     */
    public static ArrayList<Car> getCars() {
        return carList;
    }

    /*
     * Method to read the csv file using buffered reader and enter the details into the car model.
     */
    public static void readFile() throws IOException {

         String fileIn = "Fleet.csv";
        String line = null;
        boolean firstLine = false;

        FileReader fileReader = new FileReader(fileIn);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null) {

            if (!firstLine) {
                firstLine = true;
                continue;
            }

            String[] index = line.split(",");
            String vehicleID = index[0];
            String brand = index[1];
            String model = index[2];
            String type = index[3];
            int year = Integer.parseInt(index[4]);
            int seats = Integer.parseInt(index[5]);
            String color = index[6];
            int rentalCost = Integer.parseInt(index[7]);
            int insureCost = Integer.parseInt(index[8]);
            int serviceFee = Integer.parseInt(index[9]);
            String discount = index[10];

            carList.add(
                    new Car(vehicleID, brand, model, type, year, seats, color, rentalCost, insureCost, serviceFee,
                            discount));
        }
        bufferedReader.close();

    }

}
