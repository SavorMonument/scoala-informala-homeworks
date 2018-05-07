package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.UI.Selector;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;

import java.util.ArrayList;
import java.util.List;

public class SellMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.SELL;

    private Dealership deals;
    private VehicleData vehicleD;

    protected SellMenu(MenuTypes callingMenu, Dealership deals, VehicleData vehicleD) {

        super(callingMenu);
        this.deals = deals;
        this.vehicleD = vehicleD;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        if (isNotLoggedIn()) {
            MenuTypes.LOGIN.setMenu(new LoginMenu(THIS_MENU_TYPE));
            return MenuTypes.LOGIN;
        }

        int option = doSelection();

        boolean successful = false;

        if (option == 0) {
            successful = tryNormalSell();
        } else {
            if (option == 1)
                successful = tryGreenBonusSell();
        }

        if (successful)
            consIO.printString("Congratulations!! You can see your purchase in your account" +
                    "(Not yet implemented)\n\n");
        else
            consIO.printString("Something went wrong, Please try again\n");

        return callingMenu;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("Full Price");
        selections.add("GreenBonus");
        selections.add("back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }

    private boolean tryGreenBonusSell() {
        assert (null != currentClient);

        if (deals.isGreenBonusAvailable(vehicleD.HASH)){

            return deals.tryMakeGreenBonusSell(vehicleD.HASH, currentClient);

        } else{
            consIO.printString("No GreenBonus for you  ");
        }

        return false;
    }

    private boolean tryNormalSell() {
        assert (null != currentClient);

        return deals.tryMakeSell(vehicleD.HASH, currentClient);
    }

}
