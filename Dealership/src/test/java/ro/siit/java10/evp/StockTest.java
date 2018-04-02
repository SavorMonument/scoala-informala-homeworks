package ro.siit.java10.evp;

import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {

    @Test
    public void constructor_nullVehicle_exception(){

        try {
            new Stock(null, 0.0f);
        } catch (IllegalArgumentException e){
        }

        assertTrue(true);
    }

    @Test
    public void constructor_negativePrice_exception(){

        try {
            new Stock(new Vehicle("sad"), -1.0f);
        } catch (IllegalArgumentException e){
        }

        assertTrue(true);
    }

    @Test
    public void decreaseAmount(){

        Stock testStock = new Stock(new Vehicle("Bad"), 1.0f);

        testStock.decreaseAmount();

        assertEquals(0, testStock.getAmount());
    }

    @Test
    public void decreaseAmount_decreaseBelowZero_exception(){

        Stock testStock = new Stock(new Vehicle("Bad"), 1.0f);

        testStock.decreaseAmount();

        try{
            testStock.decreaseAmount();
        } catch (IllegalStateException e){
        }

        assertTrue(true);
    }
}