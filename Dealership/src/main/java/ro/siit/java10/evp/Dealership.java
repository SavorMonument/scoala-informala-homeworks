package ro.siit.java10.evp;
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

    public void addVehicle(Vehicle vehicle, float price){

        stock.add(new Stock(vehicle, price));
    }

    public ArrayList<Vehicle> inStockList(){

        ArrayList<Vehicle> instock = new ArrayList<Vehicle>();

        for(Stock instance: stock){
            instock.add(instance.getVehicle());
        }

        return instock;
    }

    public ArrayList<Vehicle> fastChargingList(){

        ArrayList<Vehicle> fast_charging = new ArrayList<Vehicle>();

        for(Stock instance: stock){
            if (instance.getVehicle().has_fast_charging())
                fast_charging.add(instance.getVehicle());
        }

        return fast_charging;
    }

    public void sellVehicle(Vehicle to_sell, Client buyer){


    }

    public void sellVehicleGreenBonus(Vehicle to_sell, Client buyer){

    }

}
