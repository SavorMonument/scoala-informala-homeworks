package ro.siit.java10.evp;

import java.util.*;

public class VehicleSorter {

    private List<Stock> stocks;

    public VehicleSorter(List<Stock> stock) {

        this.stocks = stock;
    }

    public  List<Vehicle> getAllVehicleList(){

        List<Vehicle> allVehicles = new ArrayList<>();

        for(Stock instance : stocks){
                allVehicles.add(instance.getVehicle().clone());
        }

        return allVehicles;
    }

    public  List<Vehicle> getStockVehicleList(){

        List<Vehicle> stockVehicles = new ArrayList<>();

        for(Stock instance : stocks){
            if (instance.getAmount() > 0)
                stockVehicles.add(instance.getVehicle().clone());
        }

        return stockVehicles;
    }

    public List<Vehicle> getFastChargingList() {

        List<Vehicle> fastChargingList = new ArrayList<Vehicle>();

        for (Stock  instance : stocks){
            if (instance.getVehicle().hasFastCharging()){
                fastChargingList.add(instance.getVehicle().clone());
            }
        }

        return  (fastChargingList);
    }

    public List<Vehicle> getSortedPriceList() {

        List<Stock> stocksCopy = new ArrayList<>();
        List<Vehicle> sortedPriceList = new ArrayList<>();

        for (Stock instance : stocks) {
            stocksCopy.add(instance.clone());
        }

        stocksCopy.sort(new Comparator<Stock>() {
            @Override
            public int compare(Stock o1, Stock o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });

        for (Stock instance : stocksCopy) {
            sortedPriceList.add(instance.getVehicle());
        }

        return sortedPriceList;
    }

    public List<Vehicle> getSortedHorsepowerList() {

        List<Vehicle> sortedHorsepowerList = new ArrayList<>();

        for (Stock instance : stocks) {
            sortedHorsepowerList.add(instance.getVehicle().clone());
        }

        sortedHorsepowerList.sort(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.getMotor().getHorsepower() - o2.getMotor().getHorsepower();
            }
        });

        return sortedHorsepowerList;
    }

    public List<Vehicle> getSortedRangePerChargeList(){

        List<Vehicle> sortedRangePerChargeList = new ArrayList<>();

        for (Stock instance : stocks) {
            sortedRangePerChargeList.add(instance.getVehicle().clone());
        }

        sortedRangePerChargeList.sort(new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.getRangePerCharge_Km() - o2.getRangePerCharge_Km();
            }
        });

        return sortedRangePerChargeList;
    }
}
