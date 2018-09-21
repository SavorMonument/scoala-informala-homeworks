package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.UI.Selector;
import ro.siit.java10.evp.VehicleData;

import java.util.ArrayList;
import java.util.List;

public class PerVehicleModMenu extends PerVehicleMenuFactory {

    private Dealership deals;
    private VehicleData vehicleD;

    protected PerVehicleModMenu(MenuTypes callingMenu, Dealership deals, VehicleData vehicleD) {

        super(callingMenu);
        this.deals = deals;
        this.vehicleD = vehicleD;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        consIO.printString(vehicleD.stringRepresentation(VehicleData.Options.YEAR, VehicleData.Options.RANGE_PER_CHARGE,
                VehicleData.Options.FAST_CHARGING, VehicleData.Options.MOTOR, VehicleData.Options.BATTERY, VehicleData.Options.PRICE, VehicleData.Options.STOCK));

        int option = doSelection();

        switch (option){
            case (0):
                MenuTypes.SET_VEHICLE_STOCK.setMenu(new SetVehicleStockMenu(callingMenu, deals, vehicleD));
                return MenuTypes.SET_VEHICLE_STOCK;
            case (1):
                MenuTypes.REMOVE_VEHICLE.setMenu(new RemoveVehicleMenu(callingMenu, deals, vehicleD));
                return MenuTypes.REMOVE_VEHICLE;
            case (2):
                return callingMenu;
        }

        return MenuTypes.PER_VEHICLE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Set stock");
        selections.add("Remove vehicle");
        selections.add("Back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
