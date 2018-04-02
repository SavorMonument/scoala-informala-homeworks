package ro.siit.java10.evp;

import java.security.InvalidParameterException;
import java.util.*;

public class Dealership {

    private String name;
    private String location;
    private ArrayList<Stock> stock = new ArrayList<Stock>();
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    public Dealership(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void addVehicle(Vehicle vehicle, float price){

        for (Stock instance : stock){
            if (instance.getVehicle().equals(vehicle) && (instance.getPrice() == price)){

                instance.setAmount(instance.getAmount());
                return;
            }
        }

        stock.add(new Stock(vehicle, price));
    }

    public void decreaseStock(int hash){

        for (Stock instance : stock){
            if (instance.getVehicle().hashCode() == hash){

                instance.decreaseAmount();
                return;
            }
        }
    }

    public void removeVehicle(int hash){

        for(int i = 0; i < stock.size(); i++){

            if (stock.get(i).getVehicle().hashCode() == hash){
                stock.remove(i);
                return;
            }
        }
    }

    public void setStockNumber(int hash, int stockAmmount){

        for (Stock instance : stock){
            if (instance.getVehicle().hashCode() == hash){

                instance.setAmount(stockAmmount);
                return;
            }
        }
    }

    public float getVehiclePrice(int hash){

        for (Stock instance : stock) {

            if (instance.getVehicle().hashCode() == hash){
                return instance.getPrice();
            }
        }

        throw new InvalidParameterException("no vehicle with this ID");
    }

    public int getVehicleAvailability(int hash){

        for (Stock instance : stock){

            if (instance.getVehicle().hashCode() == hash)
                return instance.getAmount();
        }

        return 0;
    }

    public VehicleSorter getVehicleSorter(){

        ArrayList<Stock> stockCopy = new ArrayList<Stock>();

        for (Stock instance : stock){
            stockCopy.add(instance.clone());
        }


        return (new VehicleSorter(stockCopy));
    }

    public void addInvoice(Invoice toAdd){

        if (toAdd == null)
            throw new IllegalArgumentException("No null invoices");

        invoices.add(toAdd);
    }

    public Dealership nameAndLocClone(){

        return new Dealership(this.getName(), this.getLocation());
    }

    @Override
    public String toString() {
        return  "name: " + name +
                " location: " + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealership that = (Dealership) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, location);
    }
}
