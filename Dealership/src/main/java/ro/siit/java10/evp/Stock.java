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

    private void setVehicle(Vehicle toSet){

        if (toSet == null){
            throw new IllegalArgumentException("Not a valid vehicle");
        }

        this.vehicle = toSet.clone();
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

    public void setAmount(int amount){

        if (amount < 0)
            throw new IllegalArgumentException("Can't have negative stock");

        this.amount = amount;
    }

    public void decreaseAmount(){

        if (amount < 0)
            throw new IllegalStateException("Can't have a negative number of vehicles");

        amount--;
    }

    public Stock clone(){

        Stock underConstruction = new Stock(vehicle.clone(), price);

        underConstruction.setAmount(amount);

        return (underConstruction);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Float.compare(stock.price, price) == 0 &&
                amount == stock.amount &&
                Objects.equals(vehicle, stock.vehicle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(vehicle, price, amount);
    }
}
