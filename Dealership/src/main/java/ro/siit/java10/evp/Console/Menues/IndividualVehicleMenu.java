package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Console.ConsoleIO.Options;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.Dealership.VehicleData;

public class IndividualVehicleMenu extends Menu {

    private MenuTypes callingMenu;
    private Dealership deals;
    private VehicleData vehicleD;

    public IndividualVehicleMenu(MenuTypes callingMenu, Dealership deals, VehicleData vehicleD) {

        this.deals = deals;
        this.vehicleD = vehicleD;
        this.callingMenu = callingMenu;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        consIO.printVehicle(vehicleD, Options.YEAR, Options.RANGE_PER_CHARGE,
                Options.FAST_CHARGING, Options.MOTOR, Options.BATTERY, Options.PRICE, Options.STOCK);

        consIO.printString("\n 1 - Buy \n 0 - Back\n");
        int option = consIO.readCondInt(0, 1);

        if (option == 1){
            MenuTypes.SELL.setMenu(new SellMenu(MenuTypes.INDIVIDUAL_VEHICLE, deals, vehicleD));
            return MenuTypes.SELL;
        } else
            if (option == 0)
                return callingMenu;

        return MenuTypes.INDIVIDUAL_VEHICLE;

    }
}
