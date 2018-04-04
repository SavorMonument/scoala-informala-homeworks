package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DealershipsCentralTest {

    DealershipsCentral Dcentral;
    ArrayList<Dealership> testDealerships = new ArrayList<>();

    @Before
    public void setUp(){

        Dcentral = new DealershipsCentral();

        testDealerships.add(new Dealership("Dealerpass", "Second street"));
        testDealerships.add(new Dealership("SapDeals", "Mars street"));
        testDealerships.add(new Dealership("RubbleTrouble", "Rip street"));
    }

    @After
    public void tearDown(){

        Dcentral = null;
        testDealerships = null;
    }

    @Test
    public void addDealership(){

        Dcentral.addDealership(testDealerships.get(0));

        assert (Dcentral.getDealershipList().get(0).equals(testDealerships.get(0)));
    }

    @Test
    public void addDealership_NullPointer(){

        Dealership testDealer = null;

        try {
            Dcentral.addDealership(testDealer);
        } catch (IllegalArgumentException e){}

        assert (true);
    }

    @Test
    public void addDealership_SameNameLoc(){

        Dcentral.addDealership(testDealerships.get(0));

        try {
            Dcentral.addDealership(testDealerships.get(0));
        } catch (IllegalArgumentException e){}

        assert (true);
    }

    @Test
    public void getDealershipList(){

        ArrayList<Dealership> expected = new ArrayList<>();

        Dcentral.addDealership(testDealerships.get(0));
        expected.add(testDealerships.get(0));
        Dcentral.addDealership(testDealerships.get(1));
        expected.add(testDealerships.get(1));

        assertEquals(expected, Dcentral.getDealershipList());
    }

    @Test
    public void getDealershipList_NoDealerships(){

        ArrayList<Dealership> expected = new ArrayList<>();

        assertEquals(expected, Dcentral.getDealershipList());
    }

    @Test
    public void removeDealership(){

        ArrayList<Dealership> expected = new ArrayList<>();

        Dcentral.addDealership(testDealerships.get(0));
        Dcentral.removeDealership(testDealerships.get(0));

        assertEquals(expected, Dcentral.getDealershipList());
    }

    @Test
    public void getNumberOfDealerships_NoDealerships(){

        assertEquals(0, Dcentral.getNumberOfDealerships());
    }

    @Test
    public void getNumberOfDealerships(){

        Dcentral.addDealership(testDealerships.get(0));
        Dcentral.addDealership(testDealerships.get(1));

        assertEquals(2, Dcentral.getNumberOfDealerships());
    }

    @Test
    public void getDealership(){

        Vehicle testVehicle = new Vehicle("Sap");
        Dealership result;

        testDealerships.get(0).addVehicle(testVehicle, 100);
        Dcentral.addDealership(testDealerships.get(0));
        result = Dcentral.getDealership(testDealerships.get(0).nameLocClone());

        assertEquals(1, result.getVehicleAvailability(testVehicle.hashCode()));
    }

    @Test
    public void getDealership_InvalidDealership(){

        assertEquals(null, Dcentral.getDealership(testDealerships.get(0)));
    }

    @Test
    public void getDealetrshipVehicleSorter(){

        Dcentral.addDealership(testDealerships.get(0));

        assert( null != Dcentral.getDealershipVehicleSorter(testDealerships.get(0).nameLocClone()));
    }

    @Test
    public void addClientAndGetClient(){

        Client testClient = new Client("Jess", "Screw");

        Dcentral.addClient(testClient);

        assertEquals(testClient, Dcentral.getClient(testClient.getFirstName(), testClient.getLastName()));
    }

    @Test
    public void addClient_Null(){

        try{
            Dcentral.addClient(null);
        } catch (IllegalArgumentException e){ }

        assert(true);
    }

    @Test
    public void getClient_Invalid(){

        assertEquals(null, Dcentral.getClient("", ""));
    }

    @Test
    public void isGreenBonusAvailable_NewVehicle(){

        Vehicle testVehicle = new Vehicle();
        testVehicle.setProductionYear(2018);

        assertEquals(true, Dcentral.isGreenBonusAvailable(testVehicle));
    }

    @Test
    public void isGreenBonusAvailable_UsedVehicle(){

        Vehicle testVehicle = new Vehicle();
        testVehicle.setProductionYear(2012);

        assertEquals(false, Dcentral.isGreenBonusAvailable(testVehicle));
    }

    @Test
    public void makeSell(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        Dcentral.addDealership(testDealerships.get(0));

        assertEquals(true,
                Dcentral.makeSell(testDealerships.get(0), testVehicle, testClient));

    }

    @Test
    public void makeSell_NullDealership(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        Dcentral.addDealership(testDealerships.get(0));

        try {
            Dcentral.makeSell(null, testVehicle, testClient);
        } catch (IllegalArgumentException e){ }

        assert (true);
    }

    @Test
    public void makeSell_InvalidCar(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");

        Dcentral.addDealership(testDealerships.get(0));

        assertEquals(false,
                Dcentral.makeSell(testDealerships.get(0), testVehicle, testClient));

    }

    @Test
    public void makeGreenBonusSell_NewCar(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");
        testVehicle.setProductionYear(2018);

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        Dcentral.addDealership(testDealerships.get(0));

        assertEquals(true,
                Dcentral.makeGreenBonusSell(testDealerships.get(0), testVehicle, testClient));

    }

    @Test
    public void makeGreenBonusSell_UsedVehicle(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");
        testVehicle.setProductionYear(2015);

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        Dcentral.addDealership(testDealerships.get(0));

        assertEquals(false,
                Dcentral.makeGreenBonusSell(testDealerships.get(0), testVehicle, testClient));

    }
}