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

        List<Vehicle> result;
        List<Vehicle> expected = new ArrayList<>();

        stocks.get(0).decreaseAmount();

        for(Stock instance : stocks){
            expected.add(instance.getVehicle());
        }

        result = vehicleSorter.getAllVehicleList();

        assert (expected.equals(result));
    }

    @Test
    public void getStockVehicleList(){

        List<Vehicle> result;
        List<Vehicle> expected = new ArrayList<>();

        expected.add(stocks.get(0).getVehicle());
        expected.add(stocks.get(1).getVehicle());
        stocks.get(2).decreaseAmount();
        result = vehicleSorter.getStockVehicleList();

        assert (expected.equals(result));
    }

    @Test
    public void getFastChargingList() {

        List<Vehicle> expected = new ArrayList<>();

        stocks.get(1).getVehicle().setFastCharging(true);
        stocks.get(2).getVehicle().setFastCharging(true);
        expected.add(stocks.get(1).getVehicle().clone());
        expected.add(stocks.get(2).getVehicle().clone());

        List<Vehicle> result = vehicleSorter.getFastChargingList();


        assert(expected.equals(result));
    }

    @Test
    public void getSortedPrice() {

        List<Vehicle> expected = new ArrayList<>();
        List<Vehicle> result;

        expected.add(stocks.get(2).getVehicle().clone());
        expected.add(stocks.get(0).getVehicle().clone());
        expected.add(stocks.get(1).getVehicle().clone());

        result = vehicleSorter.getSortedPriceList();

        assert (expected.equals(result));
    }

    @Test
    public void getSortedHorsepowerList(){

        List<Vehicle> expected = new ArrayList<>();
        List<Vehicle> result;

        stocks.get(0).getVehicle().setMotor(new Motor("", "", 15));
        stocks.get(1).getVehicle().setMotor(new Motor("", "", 8));
        stocks.get(2).getVehicle().setMotor(new Motor("", "", 18));
        expected.add(stocks.get(1).getVehicle());
        expected.add(stocks.get(0).getVehicle());
        expected.add(stocks.get(2).getVehicle());
        result = vehicleSorter.getSortedHorsepowerList();

        assert(expected.equals(result));
    }

    @Test
    public void getSortedRangePerCharge(){

        List<Vehicle> expected = new ArrayList<>();
        List<Vehicle> result;

        stocks.get(0).getVehicle().setEnergyConsumptionKWperKm(20);
        stocks.get(0).getVehicle().setBattery(new Battery("", "", 100));
        stocks.get(1).getVehicle().setEnergyConsumptionKWperKm(20);
        stocks.get(1).getVehicle().setBattery(new Battery("", "", 300));
        stocks.get(2).getVehicle().setEnergyConsumptionKWperKm(20);
        stocks.get(2).getVehicle().setBattery(new Battery("", "", 200));
        expected.add(stocks.get(0).getVehicle());
        expected.add(stocks.get(2).getVehicle());
        expected.add(stocks.get(1).getVehicle());
        result = vehicleSorter.getSortedRangePerChargeList();

        assert (expected.equals(result));
    }
}