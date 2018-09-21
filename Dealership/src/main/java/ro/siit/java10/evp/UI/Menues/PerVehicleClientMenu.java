package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.UI.Selector;
import ro.siit.java10.evp.VehicleData.Options;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;

import java.util.ArrayList;
import java.util.List;

public class PerVehicleClientMenu extends PerVehicleMenuFactory {

    private Dealership deals;
    private VehicleData vehicleD;

    protected PerVehicleClientMenu(MenuTypes callingMenu, Dealership deals, VehicleData vehicleD) {

        super(callingMenu);
        this.deals = deals;
        this.vehicleD = vehicleD;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        consIO.printString(vehicleD.stringRepresentation(Options.YEAR, Options.RANGE_PER_CHARGE,
                Options.FAST_CHARGING, Options.MOTOR, Options.BATTERY, Options.PRICE, Options.STOCK));

        int option = doSelection();

        switch (option){
            case (0):
                MenuTypes.SELL.setMenu(new SellMenu(callingMenu, deals, vehicleD));
                return MenuTypes.SELL;
            case (1):
                return callingMenu;
        }

        return MenuTypes.PER_VEHICLE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Buy");
        selections.add("Back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
