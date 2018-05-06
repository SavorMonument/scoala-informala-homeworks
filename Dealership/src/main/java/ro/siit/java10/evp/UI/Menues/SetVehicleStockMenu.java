package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;

public class SetVehicleStockMenu extends Menu {

    private MenuTypes callingMenu;
    private VehicleData vehicleD;
    private Dealership deals;

    public SetVehicleStockMenu(MenuTypes callingMenu, Dealership deals, VehicleData vehicleD) {
        this.callingMenu = callingMenu;
        this.vehicleD = vehicleD;
        this.deals = deals;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        consIO.printString(String.format("Current stock: %d\n", vehicleD.stock));
        consIO.printString("New stock: ");

        int option = consIO.readCondInt(0, 1000000);

        if (option == -1){
            consIO.printString("Something went wrong");
            return callingMenu;
        }

        deals.setVehicleStock(vehicleD.HASH, option);

        return callingMenu;
    }
}
