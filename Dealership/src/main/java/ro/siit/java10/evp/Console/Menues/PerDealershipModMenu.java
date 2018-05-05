package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.*;
import ro.siit.java10.evp.VehicleData.Options;

import java.util.List;

public class PerDealershipModMenu extends Menu {

    private MenuTypes callingMenu;
    private Dealership deals;

    public PerDealershipModMenu(MenuTypes callingMenu, Dealership deals) {

        this.callingMenu = callingMenu;
        this.deals = deals;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        int option;

        printScreen();
        option = consIO.readCondInt(0, 4);

        switch (option) {
            case (0): {
                printVehiclesPriceStockList(deals);
                break;
            }
            case (1): {
                //setStock(selectedDealership);
            }
            break;
            case (2): {
                //addVehicle(selectedDealership);
                break;
            }
            case (3): {
                //removeVehicle(selectedDealership);
                break;
            }
            case (4):
                return MenuTypes.MAIN_MOD;
        }

        return MenuTypes.PER_DEALERSHIP_MOD;
    }

    private void printScreen(){

        consIO.printString("\n");
        consIO.printString("0 - print vehicle list\n");
        consIO.printString("1 - set vehicle stock\n");
        consIO.printString("2 - add new vehicle\n");
        consIO.printString("3 - remove vehicle\n");
        consIO.printString("4 - back\n");
    }

    private void printVehiclesPriceStockList(Dealership dealership) {

        List<VehicleData> vehicleList = dealership.getVehicleSorter().getVehicleList(VehicleSorter.SortingOptions.NORMAL);

        for(VehicleData vd: vehicleList){
            consIO.printString(vd.stringRepresentation(Options.YEAR, Options.PRICE, Options.STOCK));
        }
    }
}
