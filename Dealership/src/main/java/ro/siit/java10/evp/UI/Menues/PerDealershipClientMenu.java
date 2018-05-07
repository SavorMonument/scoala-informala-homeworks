package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.UI.Selector;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleSorter;

import java.util.ArrayList;
import java.util.List;

public class PerDealershipClientMenu extends PerDealershipMenuFactory {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.PER_DEALERSHIP;

    private Dealership deals;

    public PerDealershipClientMenu(MenuTypes callingMenu, Dealership deals) {

        super(callingMenu);
        this.deals = deals;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        if (this.deals.getVehicleSorter().getVehicleList(VehicleSorter.SortingOptions.NORMAL).size() == 0){

            consIO.printString("No vehicles in stock\n");
            consIO.getKeyboardInput();

            return callingMenu;
        }

        int option = doSelection();

        switch (option){
            case (0): {
                Menu nextMenu = PickVehicleMenu.PickVehicleListType.STOCK.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (1): {
                Menu nextMenu = PickVehicleMenu.PickVehicleListType.FAST_CHARGING.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (2): {
                Menu nextMenu = PickVehicleMenu.PickVehicleListType.HORSEPOWER.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (3): {
                Menu nextMenu = PickVehicleMenu.PickVehicleListType.PRICE.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (4): {
                Menu nextMenu = PickVehicleMenu.PickVehicleListType.RANGE.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (5):
                return callingMenu;
        }

        return THIS_MENU_TYPE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Get list of vehicles in stock");
        selections.add("Get list of vehicles with fast charging");
        selections.add("Get list of vehicles sorted by Horsepower");
        selections.add("Get list of vehicles sorted by Price");
        selections.add("Get list of vehicles sorted by Range per charge");
        selections.add("back");


        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
