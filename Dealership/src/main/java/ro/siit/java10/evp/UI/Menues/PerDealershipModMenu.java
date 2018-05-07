package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.*;
import ro.siit.java10.evp.UI.Selector;

import java.util.ArrayList;
import java.util.List;

public class PerDealershipModMenu extends PerDealershipMenuFactory {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.PER_DEALERSHIP;

    private Dealership deals;

    protected PerDealershipModMenu(MenuTypes callingMenu, Dealership deals) {

        super(callingMenu);
        this.deals = deals;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        int option;

        option = doSelection();

        switch (option) {
            case (0):
                MenuTypes.PICK_VEHICLE.setMenu(PickVehicleMenu.PickVehicleListType.ALL
                        .getMenuInstance(deals, THIS_MENU_TYPE));
                return MenuTypes.PICK_VEHICLE;
            case (1):
                MenuTypes.ADD_VEHICLE.setMenu(new AddVehicleMenu(THIS_MENU_TYPE, deals));
                return MenuTypes.ADD_VEHICLE;
            case (2):
                return MenuTypes.MAIN_MENU;
        }

        return THIS_MENU_TYPE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Print complete vehicle list");
        selections.add("Add Vehicle");
        selections.add("Back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }

}
