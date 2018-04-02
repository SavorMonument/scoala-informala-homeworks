package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DealershipTest {

    private Dealership one_deals;
    private ArrayList<Vehicle> vehicles;

    @Before
    public void setUp() {

        one_deals = new Dealership("One Deals", "somewhere");
        vehicles = new ArrayList<>();

        vehicles.add(new Vehicle("series 3"));
        vehicles.add(new Vehicle("Alto"));
        vehicles.add(new Vehicle("Grand Vitara"));

        one_deals.addVehicle(vehicles.get(0), 0.0f);
        one_deals.addVehicle(vehicles.get(1), 0.0f);
        one_deals.addVehicle(vehicles.get(2), 0.0f);

    }

    @After
    public void tearDown() {

        one_deals = null;
        vehicles = null;
    }

    @Test
    public void addVehicle_priceBelowZero_ExceptionThrown() {

        try {
            one_deals.addVehicle(vehicles.get(0), -1);
        } catch (IllegalArgumentException e) {
            return;
        }

        assertTrue(true);
    }

    @Test
    public void addVehicle_nullVehicle_ExceptionThrown() {

        try {
            one_deals.addVehicle(null, 0);
        } catch (IllegalArgumentException e) {
            return;
        }

        assertTrue(true);
    }

    @Test
    public void addVehicle_DuplicateID() {

        one_deals.addVehicle(vehicles.get(0), 0.0f);

        assertEquals(2, one_deals.getVehicleAvailability(vehicles.get(0).hashCode()));
    }

    @Test
    public void removeVehicle_validVehicle() {

        int removedID = vehicles.get(0).hashCode();
        one_deals.removeVehicle(removedID);

        assertEquals(0, one_deals.getVehicleAvailability(removedID));
    }

    @Test
    public void getVehiclePrice_invalidID_ExceptionThrown() {

        try {
            one_deals.getVehiclePrice(1);
        } catch (InvalidParameterException e) {
            return;
        }

        assertTrue(true);
    }

    @Test
    public void getVehiclePrice_validID() {

        Vehicle testVehicle = new Vehicle("z");
        int testPrice = 1298;

        one_deals.addVehicle(testVehicle, testPrice);

        assertEquals(testPrice, one_deals.getVehiclePrice(testVehicle.hashCode()), 0.1);
    }

    @Test
    public void getVehicleAvailability_validID() {

        assertEquals(1, one_deals.getVehicleAvailability(vehicles.get(0).hashCode()));
    }

    @Test
    public void getVehicleAvailability_invalidID(){

        assertEquals(0, one_deals.getVehicleAvailability(1));
    }

    @Test
    public void getVehicleSorter_validStock(){

        VehicleSorter vSorter = one_deals.getVehicleSorter();

        assert( null != vSorter );
    }
}