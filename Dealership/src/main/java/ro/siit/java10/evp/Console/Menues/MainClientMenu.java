package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Console.Selector;

import java.util.ArrayList;
import java.util.List;

public class MainClientMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.MAIN_CLIENT;

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        if (isModLogged()){
            return MenuTypes.MAIN_MOD;
        }

        int option;

        option = doSelection();

        switch (option){
            case (0):
                printAllDealershipsStockVehicleList();
            break;
            case (1):
                MenuTypes.PICK_DEALERSHIP.setMenu(new PickDealershipMenu(THIS_MENU_TYPE));
                return MenuTypes.PICK_DEALERSHIP;
            case (2):
                MenuTypes.MY_ACCOUNT.setMenu(new MyAccountMenu(THIS_MENU_TYPE));
                return MenuTypes.MY_ACCOUNT;
        }

        return THIS_MENU_TYPE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Print complete list of vehicles in stock");
        selections.add("Select dealership");
        selections.add("My Account");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
