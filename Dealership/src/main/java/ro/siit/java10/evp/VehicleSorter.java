package ro.siit.java10.evp;

import java.util.*;
import ro.siit.java10.evp.Dealership.VehicleData;

public class VehicleSorter {

    private List<VehicleData> vehicleDataList;

    public VehicleSorter(List<VehicleData> vehicleDataList) {

        this.vehicleDataList = vehicleDataList;
    }

    public  List<VehicleData> getAllVehicleList(){

        return vehicleDataList;
    }

    public List<VehicleData> getStockVehicleList(){

        List<VehicleData> stockVehicles = new ArrayList<>();

        for(VehicleData instance : vehicleDataList){
            if (instance.stock > 0)
                stockVehicles.add(instance);
        }

        return stockVehicles;
    }

    public List<VehicleData> getFastChargingList() {

        List<VehicleData> fastChargingList = new ArrayList<>();

        for (VehicleData  instance : vehicleDataList){
            if (instance.fastCharging){
                fastChargingList.add(instance);
            }
        }

        return  (fastChargingList);
    }

    public List<VehicleData> getSortedPriceList() {

        quickSort(vehicleDataList, 0, vehicleDataList.size() - 1, new Comparator<VehicleData>() {
            @Override
            public int compare(VehicleData o1, VehicleData o2) {

                return (int) (o1.price - o2.price);
            }
        });

        return vehicleDataList;
    }

    public List<VehicleData> getSortedHorsepowerList() {

         quickSort(vehicleDataList, 0, vehicleDataList.size() - 1, new Comparator<VehicleData>() {
            @Override
            public int compare(VehicleData o1, VehicleData o2) {
                return o1.motor.getHorsepower() - o2.motor.getHorsepower();
            }
        });

        return vehicleDataList;
    }

    public List<VehicleData> getSortedRangePerChargeList(){

        quickSort(vehicleDataList, 0, vehicleDataList.size() - 1, new Comparator<VehicleData>() {
            @Override
            public int compare(VehicleData o1, VehicleData o2) {
                return o1.rangePerCharge_Km - o2.rangePerCharge_Km;
            }
        });

        return vehicleDataList;
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
