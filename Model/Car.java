/*
 * Car model to sort and store all Car objects from the Fleet.csv file input in FileController
 *
 * 16/09/2022
 * 
 * Copyright David Poiner - S3579878
 */

package Model;

public class Car {

    private String vehicleID;
    private String brand;
    private String model;
    private String type;
    private int year;
    private int seats;
    private String color;
    private int rentalCost;
    private int insureCost;
    private int serviceFee;
    private String discount;

    public Car(String vehicleID, String brand, String model, String type, int year, int seats, String color,
            int rentalCost, int insureCost, int serviceFee, String discount) {
        this.vehicleID = vehicleID;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.year = year;
        this.seats = seats;
        this.color = color;
        this.rentalCost = rentalCost;
        this.insureCost = insureCost;
        this.serviceFee = serviceFee;
        this.discount = discount;

    }

    public String getVehicleID() {
        return vehicleID;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public int getYear() {
        return year;
    }

    public int getSeats() {
        return seats;
    }

    public String getColor() {
        return color;
    }

    public int getRentalCost() {
        return rentalCost;
    }

    public int getInsureCost() {
        return insureCost;
    }

    public int getServiceFee() {
        return serviceFee;
    }

    public String getDiscount() {
        return discount;
    }

}
