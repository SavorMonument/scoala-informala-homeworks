package ro.siit.java10.evp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {

    Vehicle genVehicle;

    @Before
    public void setUp(){

        genVehicle = new Vehicle.VehicleBuilder().battery(new Battery())
                .motor(new Motor()).build();
    }

    @After
    public void TearDown(){

        genVehicle = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_nullVehicle_exception(){

        new Stock(null, 0.0f);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructor_negativePrice_exception(){

        new Stock(genVehicle, -1.0f);
    }

    @Test
    public void decreaseAmount(){

        Stock genStock = new Stock(genVehicle, 1.0f);

        genStock.decreaseAmount();

        assertEquals(0, genStock.getAmount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void decreaseAmount_decreaseBelowZero_exception(){

        Stock genStock = new Stock(genVehicle, 1.0f);

        genStock.decreaseAmount();
        genStock.decreaseAmount();
    }

    @Test(expected = IllegalArgumentException.class)
    public void setAmount(){

        Stock genStock = new Stock(genVehicle, 1.0f);

        genStock.setAmount(-1);
    }

    @Test
    public void addAmount(){

        Stock genStock = new Stock(genVehicle, 1.0f);

        genStock.addAmount(3);

        assertEquals(4, genStock.getAmount());
    }

    @Test
    public void getPrice(){

        Stock genStock = new Stock(genVehicle, 1.0f);

        assertEquals(1.0f, genStock.getPrice(), 0.001f);
    }

    @Test
    public void getVehicle(){

        Stock genStock = new Stock(genVehicle, 1.0f);

        assertEquals(genVehicle, genStock.getVehicle());
    }
}