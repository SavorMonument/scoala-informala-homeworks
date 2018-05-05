package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Console.Selector;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;
import ro.siit.java10.evp.VehicleSorter;

import java.util.ArrayList;
import java.util.List;

public class MainModMenu extends Menu {

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        int Option;

        Option = doSelection();

        switch (Option){
            case (0):
                printAllDealershipsVehicleList();
                break;
            case (1):
                MenuTypes.PICK_DEALERSHIP.setMenu(new PickDealershipMenu(MenuTypes.MAIN_MOD));
                return MenuTypes.PICK_DEALERSHIP;
            case (2):
                //return MenuTypes.ADD_DEALERSHIP;
            case (3):
                //return MenuTypes.REMOVE_DEALERSHIP;
            case (4):
                //return MenuTypes.LOGIN;
        }

        return MenuTypes.MAIN_MOD;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Print complete list of vehicles");
        selections.add("Select dealership");
        selections.add("Add dealership");
        selections.add("Remove dealership");
        selections.add("My account");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }

    private void printAllDealershipsVehicleList(){

        List<Dealership> dealershipNameList = dCentral.getDealershipList();

        for (Dealership instance : dealershipNameList){

            consIO.printString(instance.getName() + "\n");

            List<VehicleData> vehicleList = instance.getVehicleSorter().getVehicleList(VehicleSorter.SortingOptions.NORMAL);

            for(VehicleData vd: vehicleList){
                consIO.printString(vd.stringRepresentation() + "\n");
            }
        }
    }
}
