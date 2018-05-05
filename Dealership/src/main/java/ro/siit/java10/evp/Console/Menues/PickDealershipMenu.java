package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Dealership;

public class PickDealershipMenu extends Menu {

    private MenuTypes callingMenu;

    public PickDealershipMenu(MenuTypes callingMenu) {
        this.callingMenu = callingMenu;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {
        int numberOfDealerships = dCentral.getNumberOfDealerships();

        if (numberOfDealerships <= 0){
            consIO.printString("No dealerships");
            return callingMenu;
        }

        printDealerships();

        int option = consIO.readCondInt(0, numberOfDealerships - 1);

        if (option != -1) {

            if (callingMenu == MenuTypes.MAIN_CLIENT){
                return mainClientMenu(dCentral.getDealershipList().get(option));
            }
            else
                return mainModMenu(dCentral.getDealershipList().get(option));
        }

        return callingMenu;
    }

    private MenuTypes mainModMenu(Dealership selectedD) {

        Menu nextMenu = new PerDealershipModMenu(callingMenu, selectedD);
        MenuTypes.PER_DEALERSHIP_MOD.setMenu(nextMenu);

        return MenuTypes.PER_DEALERSHIP_MOD;
    }

    private MenuTypes mainClientMenu(Dealership selectedD) {

        Menu nextMenu = new PerDealershipClientMenu(callingMenu, selectedD);
        MenuTypes.PER_DEALERSHIP_CLIENT.setMenu(nextMenu);

        return MenuTypes.PER_DEALERSHIP_CLIENT;
    }

    private void printDealerships(){

        consIO.printNumberedList(dCentral.getDealershipList());
    }
}
