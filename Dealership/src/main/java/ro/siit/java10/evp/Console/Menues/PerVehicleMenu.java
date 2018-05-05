package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Console.Selector;
import ro.siit.java10.evp.VehicleData.Options;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;

import java.util.ArrayList;
import java.util.List;

public class PerVehicleMenu extends Menu {

    private MenuTypes callingMenu;
    private Dealership deals;
    private VehicleData vehicleD;

    public PerVehicleMenu(MenuTypes callingMenu, Dealership deals, VehicleData vehicleD) {

        this.deals = deals;
        this.vehicleD = vehicleD;
        this.callingMenu = callingMenu;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        consIO.printString(vehicleD.stringRepresentation(Options.YEAR, Options.RANGE_PER_CHARGE,
                Options.FAST_CHARGING, Options.MOTOR, Options.BATTERY, Options.PRICE, Options.STOCK));

        int option = doSelection();

        if (option == 0){
            MenuTypes.SELL.setMenu(new SellMenu(MenuTypes.INDIVIDUAL_VEHICLE, deals, vehicleD));
            return MenuTypes.SELL;
        } else
            if (option == 1)
                return callingMenu;

        return MenuTypes.INDIVIDUAL_VEHICLE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Buy");
        selections.add("Back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
