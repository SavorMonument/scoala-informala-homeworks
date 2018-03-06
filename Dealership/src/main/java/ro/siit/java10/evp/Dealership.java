import java.util.*;

public class Dealership {

    private String name;
    private String location;
    private ArrayList<Stock> in_stock = new ArrayList<>();
    private ArrayList<Invoice> invoices = new ArrayList<>();

    public Dealership(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public void addVehicle(Vehicle vehicle, float price, int number_of_vehicles){

        in_stock.add(new Stock(vehicle, price, number_of_vehicles));
    }

    public void sellVehicle(Vehicle to_sell, Client buyer){


    }

    public void sellVehicleGreenBonus(Vehicle to_sell, Client buyer){

    }

}
