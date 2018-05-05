package ro.siit.java10.evp;

import org.junit.Test;

import static org.junit.Assert.*;

public class StockTest {

    @Test(expected = IllegalArgumentException.class)
    public void constructor_nullVehicle_exception(){

        new Stock(null, 0.0f);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void constructor_negativePrice_exception(){
//
//        new Stock(new Vehicle("sad"), -1.0f);
//    }

//    @Test
//    public void decreaseAmount(){
//
//        Stock testStock = new Stock(new Vehicle("Bad"), 1.0f);
//
//        testStock.decreaseAmount();
//
//        assertEquals(0, testStock.getAmount());
//    }

//    @Test(expected = IllegalStateException.class)
//    public void decreaseAmount_decreaseBelowZero_exception(){
//
//        Stock testStock = new Stock(new Vehicle("Bad"), 1.0f);
//
//        testStock.decreaseAmount();
//        testStock.decreaseAmount();
//    }
}