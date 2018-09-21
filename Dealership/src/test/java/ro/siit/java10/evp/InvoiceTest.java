package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class InvoiceTest {

    Invoice tInvoice;
    Client tClient;
    Vehicle tVehicle;

    @Before
    public void setUp(){


        Vehicle.VehicleBuilder vehicleB = new Vehicle.VehicleBuilder();
        tVehicle = vehicleB.model("Idk").battery(new Battery()).motor(new Motor()).build();

        tClient = new Client("Zolo", "Zara");
        tInvoice = new Invoice(tClient, tVehicle, 102.0f);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getGetClient(){

        assertEquals(tClient, tInvoice.getClient());
    }

    @Test
    public void getGetVehicle(){

        assertEquals(tVehicle, tInvoice.getVehicle());
    }

    @Test
    public void getGetTotal(){

        assertEquals(102.0f, tInvoice.getTotal(), 0.0001f);
    }
}