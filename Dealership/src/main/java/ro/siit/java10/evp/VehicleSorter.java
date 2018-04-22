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

        List<Vehicle> sortedPriceList = new ArrayList<>();
        List<Stock> stockCopy = new ArrayList<>();

        for (Stock instance : stocks) {
            stockCopy.add(instance.clone());
        }

        quickSort(stockCopy, 0, stockCopy.size() - 1, new Comparator<Stock>() {
            @Override
            public int compare(Stock o1, Stock o2) {
                return (int) (o1.getPrice() - o2.getPrice());
            }
        });

        for (Stock instance : stockCopy){
            sortedPriceList.add(instance.getVehicle());
        }

        return sortedPriceList;
    }

    public List<Vehicle> getSortedHorsepowerList() {

        List<Vehicle> sortedHorsepowerList = getStockVehicleList();

         quickSort(sortedHorsepowerList, 0, sortedHorsepowerList.size() - 1, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.getMotor().getHorsepower() - o2.getMotor().getHorsepower();
            }
        });

        return sortedHorsepowerList;
    }

    public List<Vehicle> getSortedRangePerChargeList(){

        List<Vehicle> sortedRangePerChargeArray = getStockVehicleList();

        quickSort(sortedRangePerChargeArray, 0, sortedRangePerChargeArray.size() - 1, new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                return o1.getRangePerCharge_Km() - o2.getRangePerCharge_Km();
            }
        });

        return sortedRangePerChargeArray;
    }

    private <T> void quickSort(List<T> list, int left, int right, Comparator<T> comp){

        if (left < right){

            int pivot = getPivot(list, left, right, comp);

            quickSort(list, left, pivot - 1, comp);
            quickSort(list, pivot + 1, right, comp);
        }
    }

    private <T> int getPivot(List<T> list, int left, int right, Comparator<T> comp){

        T pivot = list.get(right);

        int i = left - 1;

        for(int j = left; j < right; j++){

            if (comp.compare(list.get(j), pivot) < 0){
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, right);
        return i + 1;
    }

    private <T> void swap(List<T> list, int i, int j){

        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
