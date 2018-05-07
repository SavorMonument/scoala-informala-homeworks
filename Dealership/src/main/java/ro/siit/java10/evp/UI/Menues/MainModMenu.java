package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.UI.Selector;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;
import ro.siit.java10.evp.VehicleSorter;

import java.util.ArrayList;
import java.util.List;

public class MainModMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.MAIN_MENU;

    protected MainModMenu() {

        super(THIS_MENU_TYPE);
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        int Option;

        Option = doSelection();

        switch (Option){
            case (0):
                printAllDealershipsVehicleList();
                break;
            case (1):
                MenuTypes.PICK_DEALERSHIP.setMenu(new PickDealershipMenu(THIS_MENU_TYPE));
                return MenuTypes.PICK_DEALERSHIP;
            case (2):
                MenuTypes.ADD_DEALERSHIP.setMenu(new AddDealershipMenu(THIS_MENU_TYPE));
                return MenuTypes.ADD_DEALERSHIP;
            case (3):
                MenuTypes.REMOVE_DEALERSHIP.setMenu(new RemoveDealershipMenu(THIS_MENU_TYPE));
                return MenuTypes.REMOVE_DEALERSHIP;
            case (4):
                return MenuTypes.MY_ACCOUNT;
        }

        return THIS_MENU_TYPE;
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
