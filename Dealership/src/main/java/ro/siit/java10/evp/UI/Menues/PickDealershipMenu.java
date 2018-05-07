package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.UI.Selector;

import java.util.List;

public class PickDealershipMenu extends Menu {

    protected PickDealershipMenu(MenuTypes callingMenu) {

        super(callingMenu);
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {
        int numberOfDealerships = dCentral.getNumberOfDealerships();

        if (numberOfDealerships <= 0){
            consIO.printString("No dealerships");
            return callingMenu;
        }

        int option = doSelection();

        if (option != dCentral.getDealershipList().size()) {

            Menu nextMenu = PerDealershipMenuFactory.getDealershipMenu(callingMenu,
                    dCentral.getDealershipList().get(option));
            MenuTypes.PER_DEALERSHIP.setMenu(nextMenu);

            return MenuTypes.PER_DEALERSHIP;
        }

        return callingMenu;
    }

    private int doSelection(){

        List<String> selections = Selector.listToStringList(dCentral.getDealershipList());
        selections.add("Back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
