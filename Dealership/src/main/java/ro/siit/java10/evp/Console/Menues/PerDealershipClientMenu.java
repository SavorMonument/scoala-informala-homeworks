package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Console.Selector;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleSorter;

import java.util.ArrayList;
import java.util.List;

public class PerDealershipClientMenu extends Menu{

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.PER_DEALERSHIP_CLIENT;

    private Dealership deals;
    private MenuTypes callingMenu;

    public PerDealershipClientMenu(MenuTypes callingMenu, Dealership deals) {

        this.deals = deals;
        this.callingMenu = callingMenu;
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
                Menu nextMenu = PickVehicleMenu.VehicleListType.STOCK.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (1): {
                Menu nextMenu = PickVehicleMenu.VehicleListType.FAST_CHARGING.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (2): {
                Menu nextMenu = PickVehicleMenu.VehicleListType.HORSEPOWER.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (3): {
                Menu nextMenu = PickVehicleMenu.VehicleListType.PRICE.
                        getMenuInstance(deals, THIS_MENU_TYPE);
                MenuTypes.PICK_VEHICLE.setMenu(nextMenu);
                return MenuTypes.PICK_VEHICLE;
            }
            case (4): {
                Menu nextMenu = PickVehicleMenu.VehicleListType.RANGE.
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
