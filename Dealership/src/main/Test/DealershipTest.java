import org.junit.jupiter.api.Test;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.Vehicle;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DealershipTest {

    private Dealership one_deals = new Dealership("One Deals", "somewhere");
    private ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    DealershipTest() {

        vehicles.add(new Vehicle("Bmw", "series 3", 2005));
        vehicles.add(new Vehicle("Fiat", "Alto", 2001));
        vehicles.add(new Vehicle("Bmw", "Grand Vitara", 2005));

        one_deals.addVehicle(vehicles.get(0), 0.0f); 
        one_deals.addVehicle(vehicles.get(1), 0.0f);
        one_deals.addVehicle(vehicles.get(2), 0.0f);
    }

    @Test
    public void inStockList() {

        ArrayList<Vehicle> stock = one_deals.inStockList();

        assert(vehicles.get(0).is_same_vehicle(stock.get(0)));
        assert(vehicles.get(1).is_same_vehicle(stock.get(1)));
        assert(vehicles.get(2).is_same_vehicle(stock.get(2)));
        assert(stock.size() == 3);

    }

    @Test
    public void fastChargingList() {

        ArrayList<Vehicle> stock = one_deals.inStockList();

        stock.get(0).setFast_charging(true);
        stock.get(2).setFast_charging(true);

        ArrayList<Vehicle> fast_charging = one_deals.fastChargingList();

        assert(fast_charging.get(0).is_same_vehicle(stock.get(0)));
        assert(fast_charging.get(1).is_same_vehicle(stock.get(2)));
        assert(fast_charging.size() == 2);
    }
}