package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ro.siit.java10.evp.VehicleSorter.FilterOptions;
import ro.siit.java10.evp.VehicleSorter.SortingOptions;
import ro.siit.java10.evp.Vehicle.VehicleBuilder;
import java.util.*;

import static junit.framework.TestCase.assertEquals;

public class VehicleSorterTest {

    List<VehicleData> vehicleDs;
    VehicleSorter vehicleSorter;

    @Before
    public void setUp(){

        vehicleDs = new ArrayList<>();

        vehicleDs.add(new VehicleData(new VehicleBuilder().model("Zoro").build(), 15.0f, 1));
        vehicleDs.add(new VehicleData(new VehicleBuilder().model("Vara").build(), 16.0f, 1));
        vehicleDs.add(new VehicleData(new VehicleBuilder().model("Miri").build(), 13.0f, 1));

        vehicleSorter = new VehicleSorter(vehicleDs);
    }

    @After
    public void tearDown(){

        vehicleSorter = null;
    }

    @Test
    public void normalSort(){

        List<VehicleData> result;

        result = vehicleSorter.getVehicleList(SortingOptions.NORMAL);

        assertEquals (vehicleDs, result);
    }

    @Test
    public void priceSort(){

        List<VehicleData> result;
        List<VehicleData> expected = new ArrayList<>();

        expected.add(vehicleDs.get(2));
        expected.add(vehicleDs.get(0));
        expected.add(vehicleDs.get(1));
        result = vehicleSorter.getVehicleList(SortingOptions.PRICE);

        assertEquals(expected, result);
    }

    @Test
    public void horsepowerSort(){

        List<VehicleData> result;
        List<VehicleData> expected = new ArrayList<>();
        addMotors(vehicleDs);

        expected.add(vehicleDs.get(0));
        expected.add(vehicleDs.get(2));
        expected.add(vehicleDs.get(1));
        result = vehicleSorter.getVehicleList(SortingOptions.HORSEPOWER);

        assertEquals(expected, result);

    }

    private void addMotors(List<VehicleData> list){

        list.get(0).motor = new Motor("a", "b", 10);
        list.get(1).motor = new Motor("a", "b", 30);
        list.get(2).motor = new Motor("a", "b", 20);
    }

    @Test
    public void rangeSort(){

        assert (false);
    }

    @Test
    public void normalSort_StockFilter() {

        List<VehicleData> result;
        List<VehicleData> expected = new ArrayList<>(vehicleDs);
        vehicleDs.add(new VehicleData(new VehicleBuilder().model("Sairi").build(), 13.0f, 0));

        result = vehicleSorter.getVehicleList(SortingOptions.NORMAL, FilterOptions.STOCK);

        assertEquals(expected, result);
    }

    @Test
    public void normalSort_FastChargeFilter() throws InterruptedException {

        List<VehicleData> result;
        List<VehicleData> expected = new ArrayList<>();
        vehicleDs.get(1).fastCharging = true;
        expected.add(vehicleDs.get(1));

        result = vehicleSorter.getVehicleList(SortingOptions.NORMAL, FilterOptions.FAST_CHARGING);

        assertEquals(expected, result);
    }

    @Test
    public void normalSort_StockAndFastChargeFilter(){

        List<VehicleData> result;
        List<VehicleData> expected = new ArrayList<>();
        vehicleDs.get(1).fastCharging = true;
        expected.add(vehicleDs.get(1));

        result = vehicleSorter.getVehicleList(SortingOptions.NORMAL, FilterOptions.STOCK,
                FilterOptions.FAST_CHARGING);

        assertEquals(expected, result);

    }
}