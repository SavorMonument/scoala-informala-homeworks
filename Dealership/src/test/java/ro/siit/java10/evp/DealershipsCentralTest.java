package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DealershipsCentralTest {

    DealershipsCentral dCentral;
    ArrayList<Dealership> testDealerships = new ArrayList<>();

    @Before
    public void setUp(){

        dCentral = new DealershipsCentral();

        testDealerships.add(new Dealership("Dealerpass", "Second street"));
        testDealerships.add(new Dealership("SapDeals", "Mars street"));
        testDealerships.add(new Dealership("RubbleTrouble", "Rip street"));
    }

    @After
    public void tearDown(){

        dCentral = null;
        testDealerships = null;
    }

    @Test
    public void addDealership(){

        dCentral.addDealership(testDealerships.get(0));

        assert (dCentral.getDealershipList().get(0).equals(testDealerships.get(0)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDealership_NullPointer(){

        Dealership testDealer = null;

        dCentral.addDealership(testDealer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addDealership_SameNameLoc(){

        dCentral.addDealership(testDealerships.get(0));

        dCentral.addDealership(testDealerships.get(0));
    }

    @Test
    public void getDealershipList(){

        ArrayList<Dealership> expected = new ArrayList<>();

        dCentral.addDealership(testDealerships.get(0));
        expected.add(testDealerships.get(0));
        dCentral.addDealership(testDealerships.get(1));
        expected.add(testDealerships.get(1));

        assertEquals(expected, dCentral.getDealershipList());
    }

    @Test
    public void getDealershipList_NoDealerships(){

        ArrayList<Dealership> expected = new ArrayList<>();

        assertEquals(expected, dCentral.getDealershipList());
    }

    @Test
    public void removeDealership(){

        ArrayList<Dealership> expected = new ArrayList<>();

        dCentral.addDealership(testDealerships.get(0));
        dCentral.removeDealership(testDealerships.get(0));

        assertEquals(expected, dCentral.getDealershipList());
    }

    @Test
    public void getNumberOfDealerships_NoDealerships(){

        assertEquals(0, dCentral.getNumberOfDealerships());
    }

    @Test
    public void getNumberOfDealerships(){

        dCentral.addDealership(testDealerships.get(0));
        dCentral.addDealership(testDealerships.get(1));

        assertEquals(2, dCentral.getNumberOfDealerships());
    }

    @Test
    public void getDealership(){

        Vehicle testVehicle = new Vehicle("Sap");
        Dealership result;

        testDealerships.get(0).addVehicle(testVehicle, 100);
        dCentral.addDealership(testDealerships.get(0));
        result = dCentral.getDealership(testDealerships.get(0).nameLocClone());

        assertEquals(1, result.getVehicleAvailability(testVehicle.hashCode()));
    }

    @Test
    public void getDealership_InvalidDealership(){

        assertEquals(null, dCentral.getDealership(testDealerships.get(0)));
    }

    @Test
    public void getDealetrshipVehicleSorter(){

        dCentral.addDealership(testDealerships.get(0));

        assert( null != dCentral.retrieveDealershipVehicleSorter(testDealerships.get(0).nameLocClone()));
    }

    @Test
    public void addClientAndGetClient(){

        Client testClient = new Client("Jess", "Screw");

        dCentral.addClient(testClient);

        assertEquals(testClient, dCentral.getClient(testClient.getFirstName(), testClient.getLastName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void addClient_Null(){

        dCentral.addClient(null);
    }

    @Test
    public void getClient_Invalid(){

        assertEquals(null, dCentral.getClient("", ""));
    }

    @Test
    public void isGreenBonusAvailable_NewVehicle(){

        Vehicle testVehicle = new Vehicle();
        testVehicle.setProductionYear(2018);

        assertEquals(true, dCentral.isGreenBonusAvailable(testVehicle));
    }

    @Test
    public void isGreenBonusAvailable_UsedVehicle(){

        Vehicle testVehicle = new Vehicle();
        testVehicle.setProductionYear(2012);

        assertEquals(false, dCentral.isGreenBonusAvailable(testVehicle));
    }

    @Test
    public void makeSell(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        dCentral.addDealership(testDealerships.get(0));

        assertEquals(true,
                dCentral.makeSell(testDealerships.get(0), testVehicle, testClient));

    }

    @Test(expected = IllegalArgumentException.class)
    public void makeSell_NullDealership(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        dCentral.addDealership(testDealerships.get(0));


        dCentral.makeSell(null, testVehicle, testClient);
    }

    @Test
    public void makeSell_InvalidCar(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");

        dCentral.addDealership(testDealerships.get(0));

        assertEquals(false,
                dCentral.makeSell(testDealerships.get(0), testVehicle, testClient));

    }

    @Test
    public void makeGreenBonusSell_NewCar(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");
        testVehicle.setProductionYear(2018);

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        dCentral.addDealership(testDealerships.get(0));

        assertEquals(true,
                dCentral.makeGreenBonusSell(testDealerships.get(0), testVehicle, testClient));

    }

    @Test
    public void makeGreenBonusSell_UsedVehicle(){

        Client testClient = new Client("Jess", "Screw");
        Vehicle testVehicle = new Vehicle("Sap");
        testVehicle.setProductionYear(2015);

        testDealerships.get(0).addVehicle(testVehicle, 1.0f);
        dCentral.addDealership(testDealerships.get(0));

        assertEquals(false,
                dCentral.makeGreenBonusSell(testDealerships.get(0), testVehicle, testClient));

    }
}