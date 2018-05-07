package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.Invoice;
import ro.siit.java10.evp.UI.Selector;

import java.util.ArrayList;
import java.util.List;

public class MyAccountMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.MY_ACCOUNT;

    protected MyAccountMenu(MenuTypes callingMenu) {

        super(callingMenu);
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        if (isNotLoggedIn()){
            MenuTypes.LOGIN.setMenu(new LoginMenu(callingMenu));
            return MenuTypes.LOGIN;
        }

        consIO.printString(String.format("You are logged in as %s %s\n\n",
                currentClient.getFirstName(), currentClient.getLastName()));

        int option = doSelection();

        switch (option){
            case (0):
                printPurchaseHistoryAcrossAllDealerships();
                consIO.getKeyboardInput();
                return THIS_MENU_TYPE;
            case (1):
                consIO.printString(String.format("Balance: %.1f USD\n",
                        currentClient.getCredit()));
                consIO.getKeyboardInput();
                return THIS_MENU_TYPE;
            case (2):
                consIO.printString("Not here yet\n");
                return THIS_MENU_TYPE;
            case (3):
                currentClient = null;
                return callingMenu;
            case (4):
                return callingMenu;
        }

        return THIS_MENU_TYPE;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("See purchase history");
        selections.add("See account balance");
        selections.add("Add credit");
        selections.add("Log out");
        selections.add("back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }

    private void printPurchaseHistoryAcrossAllDealerships(){

        List<Dealership> dealerships = dCentral.getDealershipList();

        for (int i = 0; i < dealerships.size(); i++) {

            printPurchaseHistoryPerOneDealership(dealerships.get(i));
        }
    }

    private void printPurchaseHistoryPerOneDealership(Dealership deals){

        List<Invoice> invoices = deals.getInvoiceList(currentClient);

        if (invoices.size() > 0){

            consIO.printString(deals.toString() + "\n");
            for (Invoice inv: invoices){

                consIO.printString(inv.toString() + "\n");
            }
        }
    }

}
