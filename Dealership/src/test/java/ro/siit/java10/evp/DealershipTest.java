package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;

public class DealershipTest {

    private Dealership oneDeals;
    private List<VehicleData> oneDealsVehicleList;

    @Before
    public void setUp() {

        oneDeals = new Dealership("One Deals", "somewhere");
        oneDealsVehicleList = new ArrayList<>();

        VehicleData genericVehicle = buildGenericVehicleData();

        oneDeals.addVehicle(genericVehicle.model("series 3"));
        oneDeals.addVehicle(genericVehicle.model("Alto"));
        oneDeals.addVehicle(genericVehicle.model("Grand Vitara"));

        oneDealsVehicleList = oneDeals.getVehicleSorter().getVehicleList(
                VehicleSorter.SortingOptions.NORMAL);

    }

    @After
    public void tearDown() {

        oneDeals = null;
        oneDealsVehicleList = null;
    }

    private VehicleData buildGenericVehicleData() {

        return new VehicleData().model("Generic").productionYear(1999).energyConsumptionKWperKm(20)
                .motor(new Motor()).battery(new Battery()).stock(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVehicle_priceBelowZero_ExceptionThrown() {

        VehicleData genericVehicle = buildGenericVehicleData().price(-1);

        oneDeals.addVehicle(genericVehicle);
    }

    @Test
    public void addVehicle() {

        VehicleData vehicleD = buildGenericVehicleData();

        oneDeals.addVehicle(vehicleD);

        assertEquals(1, oneDeals.getAmountOfVehiclesInStock(vehicleD.buildVehicle().hashCode()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVehicle_NegativePrice() {

        VehicleData vehicleD = buildGenericVehicleData().price(-1);

        oneDeals.addVehicle(vehicleD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVehicle_NegativeYear() {

        VehicleData vehicleD = buildGenericVehicleData().productionYear(-1);

        oneDeals.addVehicle(vehicleD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVehicle_NullBattery() {

        VehicleData vehicleD = buildGenericVehicleData().battery(null);

        oneDeals.addVehicle(vehicleD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVehicle_NullMotor() {

        VehicleData vehicleD = buildGenericVehicleData().motor(null);

        oneDeals.addVehicle(vehicleD);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addVehicle_NegativeStock() {

        VehicleData vehicleD = buildGenericVehicleData().stock(-1);

        oneDeals.addVehicle(vehicleD);
    }

    @Test
    public void addVehicle_DuplicateHash() {

        VehicleData alreadyAddedVehicle = oneDealsVehicleList.get(0);

        oneDeals.addVehicle(alreadyAddedVehicle);

        assertEquals(2, oneDeals.getAmountOfVehiclesInStock(alreadyAddedVehicle.HASH));
    }

    @Test
    public void getVehicleStockNumber() {

        VehicleData alreadyAddedVehicle = oneDealsVehicleList.get(0);

        assertEquals(1, oneDeals.getAmountOfVehiclesInStock(alreadyAddedVehicle.HASH));
    }

    @Test
    public void setVehicleStock(){

        VehicleData vehicleD = oneDealsVehicleList.get(0);
        final int stockAmount = 20;

        oneDeals.setVehicleStock(vehicleD.HASH, stockAmount);

        assertEquals(stockAmount, oneDeals.getAmountOfVehiclesInStock(vehicleD.HASH));
    }

    @Test
    public void removeVehicle_validHash() {

        VehicleData alreadyAddedVehicle = oneDealsVehicleList.get(0);
        oneDeals.removeVehicle(alreadyAddedVehicle.HASH);

        assertEquals(0, oneDeals.getAmountOfVehiclesInStock(alreadyAddedVehicle.HASH));
    }

    @Test
    public void isGreenBonusAvailable_NewCar() {

        VehicleData vehicleD = buildGenericVehicleData().productionYear(
                Calendar.getInstance().get(Calendar.YEAR));

        oneDeals.addVehicle(vehicleD);

        assertTrue(oneDeals.isGreenBonusAvailable(vehicleD.buildVehicle().hashCode()));
    }

    @Test
    public void isGreenBonusAvailable_OldCar() {

        VehicleData vehicleD = buildGenericVehicleData().productionYear(1999);

        oneDeals.addVehicle(vehicleD);

        assertFalse(oneDeals.isGreenBonusAvailable(vehicleD.buildVehicle().hashCode()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void isGreenBonusAvailable_InvalidHash() {

        oneDeals.isGreenBonusAvailable(0);
    }

    @Test
    public void makeSell_CheckInvoices(){

        VehicleData alreadyAddedVehicle = oneDealsVehicleList.get(1);
        Client testClient = new Client("Ale", "Zar");

        oneDeals.tryMakeSell(alreadyAddedVehicle.HASH, testClient);

        assertEquals(1, oneDeals.getInvoiceList(testClient).size());
    }

    @Test
    public void makeSell_CheckStock(){

        VehicleData alreadyAddedVehicle = oneDealsVehicleList.get(1);
        Client testClient = new Client("Ale", "Zar");
        testClient.setCredit(10000);

        oneDeals.tryMakeSell(alreadyAddedVehicle.HASH, testClient);

        assertEquals(0, oneDeals.getAmountOfVehiclesInStock(alreadyAddedVehicle.HASH));
    }

    @Test
    public void makeSell_ClientNotEnoughCredit() {

        VehicleData vehicleD = buildGenericVehicleData().price(1.0f);
        Client testClient = new Client("Ale", "Zar");
        testClient.setCredit(0);

        oneDeals.addVehicle(vehicleD);

        assertFalse(oneDeals.tryMakeSell(vehicleD.buildVehicle().hashCode(), testClient));
    }

    @Test(expected = IllegalArgumentException.class)
    public void makeSell_InvalidHash() {

        Client testClient = new Client("Ale", "Zar");
        testClient.setCredit(10000);

        oneDeals.tryMakeSell(0, testClient);
    }

    @Test
    public void makeGreenBonusSell(){
        VehicleData vehicleD = buildGenericVehicleData().price(1.0f).productionYear(
                Calendar.getInstance().get(Calendar.YEAR));
        Client testClient = new Client("Ale", "Zar");
        testClient.setCredit(10.0f);

        oneDeals.addVehicle(vehicleD);

        assertTrue(oneDeals.tryMakeGreenBonusSell(vehicleD.buildVehicle().hashCode(), testClient));

    }

    @Test
    public void makeGreenBonusSell_WrongCarYear(){
        VehicleData vehicleD = buildGenericVehicleData().price(1.0f).productionYear(
                Calendar.getInstance().get(Calendar.YEAR) - 1);
        Client testClient = new Client("Ale", "Zar");
        testClient.setCredit(10.0f);

        oneDeals.addVehicle(vehicleD);

        assertFalse(oneDeals.tryMakeGreenBonusSell(vehicleD.buildVehicle().hashCode(), testClient));

    }
}