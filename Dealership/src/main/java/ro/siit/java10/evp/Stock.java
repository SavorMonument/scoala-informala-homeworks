package ro.siit.java10.evp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Stock implements Cloneable{

    private Vehicle vehicle;
    private float price;
    private int amount = 1;

    public Stock(Vehicle vehicle, float price) {

        setVehicle(vehicle);
        setPrice(price);
    }

    public Vehicle getVehicle() {

        return vehicle;
    }

    private void setVehicle(Vehicle vehicle){

        if (vehicle == null){
            throw new IllegalArgumentException("Not a valid vehicle");
        }

        this.vehicle = new Vehicle(vehicle);
    }

    public void setPrice(float price){

        if (price < 0){
            throw new IllegalArgumentException("Price can't be negative");
        }

        this.price = price;
    }

    public float getPrice() {

        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount){

        setAmount(this.amount + amount);
    }

    public void decreaseAmount(){

        setAmount(amount - 1);
    }

    public void setAmount(int amount){

        if (amount < 0)
            throw new IllegalArgumentException("Can't have negative stock");

        this.amount = amount;
    }
}
