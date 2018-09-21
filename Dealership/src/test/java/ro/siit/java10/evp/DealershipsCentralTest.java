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
}