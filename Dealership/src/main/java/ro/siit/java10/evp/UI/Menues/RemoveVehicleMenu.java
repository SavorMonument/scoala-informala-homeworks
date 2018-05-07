package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.UI.Selector;
import ro.siit.java10.evp.VehicleData;

import java.util.ArrayList;
import java.util.List;

public class RemoveVehicleMenu extends Menu {

    private VehicleData vehicleD;
    private Dealership deals;

    protected RemoveVehicleMenu(MenuTypes callingMenu, Dealership deals, VehicleData vehicleD) {

        super(callingMenu);
        this.vehicleD = vehicleD;
        this.deals = deals;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        consIO.printString("Are you sure?\n");

        int option = doSelection();

        if (option == 0)
            deals.removeVehicle(vehicleD.HASH);

        return callingMenu;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Yes");
        selections.add("No");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
