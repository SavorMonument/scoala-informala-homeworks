package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.*;
import ro.siit.java10.evp.Console.Selector;
import ro.siit.java10.evp.VehicleData.Options;

import java.util.ArrayList;
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

        option = doSelection();

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
                addVehicle();
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

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Print vehicle list");
        selections.add("Set vehicle stock");
        selections.add("Add new vehicle");
        selections.add("Remove vehicle");
        selections.add("Back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }

    private void printVehiclesPriceStockList(Dealership dealership) {

        List<VehicleData> vehicleList = dealership.getVehicleSorter().getVehicleList(VehicleSorter.SortingOptions.NORMAL);

        for(VehicleData vd: vehicleList){
            consIO.printString(vd.stringRepresentation(Options.YEAR, Options.PRICE, Options.STOCK));
        }
    }

    private void addVehicle(){

        VehicleData vehicleD;

        if (null != (vehicleD = readVehicle())){
            deals.addVehicle(vehicleD);
        }

    }

    private VehicleData readVehicle(){

    String strInput;
    int intInput;

    VehicleData vehicleD = new VehicleData();

    System.out.print("Vehicle name: ");
    if (null == (strInput = consIO.readString()))
        return null;
    vehicleD.model(strInput);

    System.out.print("Vehicle production year: ");
    if (-1 == (intInput = consIO.readCondInt(1900, 2018)))
        return null;
    vehicleD.productionYear(intInput);

    System.out.print("Vehicle energy consumption KW/Km: ");
    if (-1 == (intInput = consIO.readCondInt(1, 1000)))
        return null;
    vehicleD.energyConsumptionKWperKm(intInput);

    System.out.print("Vehicle has fast charging(1 or 0): ");
    if (-1 == (intInput = consIO.readCondInt(0, 1)))
        return null;
    if (intInput == 1){
        vehicleD.fastCharging(true);
    } else
        vehicleD.fastCharging(false);

    return vehicleD;
}

}
