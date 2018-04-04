package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VehicleSorterTest {

    List<Stock> stocks;
    VehicleSorter vehicleSorter;

    @Before
    public void setUp(){
        stocks = new ArrayList<>();

        stocks.add(new Stock(new Vehicle("Zoro"), 15.0f));
        stocks.add(new Stock(new Vehicle("Vara"), 16.0f));
        stocks.add(new Stock(new Vehicle("Miri"), 13.0f));

        vehicleSorter = new VehicleSorter(stocks);
    }

    @After
    public void tearDown(){

        stocks = null;
        vehicleSorter = null;
    }

    @Test
    public void getAllVehicleList(){

        ArrayList<Vehicle> result;
        ArrayList<Vehicle> expected = new ArrayList<>();

        stocks.get(0).decreaseAmount();

        for(Stock instance : stocks){
            expected.add(instance.getVehicle());
        }

        result = (ArrayList<Vehicle>) vehicleSorter.getAllVehicleList();

        assert (expected.equals(result));
    }

    @Test
    public void getStockVehicleList(){

        ArrayList<Vehicle> result = (ArrayList<Vehicle>) vehicleSorter.getStockVehicleList();
        ArrayList<Vehicle> expected = new ArrayList<>();

        for(Stock instance : stocks){
            expected.add(instance.getVehicle());
        }

        assert (expected.equals(result));
    }

    @Test
    public void getFastChargingList() {

        ArrayList<Vehicle> expected = new ArrayList<>();

        stocks.get(1).getVehicle().setFastCharging(true);
        stocks.get(2).getVehicle().setFastCharging(true);
        expected.add(stocks.get(1).getVehicle().clone());
        expected.add(stocks.get(2).getVehicle().clone());

        ArrayList<Vehicle> result = (ArrayList<Vehicle>) vehicleSorter.getFastChargingList();


        assert(expected.equals(result));
    }

    @Test
    public void getSortedPrice() {

        ArrayList<Vehicle> expected = new ArrayList<>();
        ArrayList<Vehicle> result;

        expected.add(stocks.get(2).getVehicle().clone());
        expected.add(stocks.get(0).getVehicle().clone());
        expected.add(stocks.get(1).getVehicle().clone());

        result = (ArrayList<Vehicle>) vehicleSorter.getSortedPriceList();

        assert (expected.equals(result));
    }

    @Test
    public void getSortedHorsepowerList(){

        ArrayList<Vehicle> expected = new ArrayList<>();
        ArrayList<Vehicle> result;

        stocks.get(0).getVehicle().setMotor(new Motor("", "", 15));
        stocks.get(1).getVehicle().setMotor(new Motor("", "", 8));
        stocks.get(2).getVehicle().setMotor(new Motor("", "", 18));
        expected.add(stocks.get(1).getVehicle());
        expected.add(stocks.get(0).getVehicle());
        expected.add(stocks.get(2).getVehicle());
        result = (ArrayList<Vehicle>) vehicleSorter.getSortedHorsepowerList();

        assert(expected.equals(result));
    }

    @Test
    public void getSortedRangePerCharge(){

        ArrayList<Vehicle> expected = new ArrayList<>();
        ArrayList<Vehicle> result;

        stocks.get(0).getVehicle().setEnergyConsumption_KWperKm(20);
        stocks.get(0).getVehicle().setBattery(new Battery("", "", 100));
        stocks.get(1).getVehicle().setEnergyConsumption_KWperKm(20);
        stocks.get(1).getVehicle().setBattery(new Battery("", "", 300));
        stocks.get(2).getVehicle().setEnergyConsumption_KWperKm(20);
        stocks.get(2).getVehicle().setBattery(new Battery("", "", 200));
        expected.add(stocks.get(0).getVehicle());
        expected.add(stocks.get(2).getVehicle());
        expected.add(stocks.get(1).getVehicle());
        result = (ArrayList<Vehicle>) vehicleSorter.getSortedRangePerChargeList();

        assert (expected.equals(result));
    }
}