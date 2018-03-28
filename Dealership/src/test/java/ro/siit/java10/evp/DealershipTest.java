package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DealershipTest {

    private Dealership one_deals;
    private ArrayList<Vehicle> vehicles;

    @Before
    public void setUp(){

        one_deals = new Dealership("One Deals", "somewhere");
        vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("Bmw", "series 3", 2005));
        vehicles.add(new Vehicle("Fiat", "Alto", 2001));
        vehicles.add(new Vehicle("Bmw", "Grand Vitara", 2005));

        one_deals.addVehicle(vehicles.get(0), 0.0f);
        one_deals.addVehicle(vehicles.get(1), 0.0f);
        one_deals.addVehicle(vehicles.get(2), 0.0f);

        System.out.println(vehicles.get(0).hashCode());
    }

    @After
    public void tearDown(){

        one_deals = null;
        vehicles = null;
    }

    @Test
    public void inStockList() {

        ArrayList<Vehicle> stock = one_deals.getStockList();

        assert(vehicles.equals(stock));
    }

    @Test
    public void fastChargingList() {

        ArrayList<Vehicle> fastChargingList = one_deals.getStockList();

        fastChargingList.get(0).setFast_charging(true);
        fastChargingList.get(2).setFast_charging(true);
        fastChargingList.remove(1);

        ArrayList<Vehicle> fastChargingTestList = one_deals.getFastChargingList();

        assert(fastChargingTestList.equals(fastChargingList));
    }
}