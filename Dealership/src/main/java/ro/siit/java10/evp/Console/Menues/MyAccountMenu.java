package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Console.Selector;

import java.util.ArrayList;
import java.util.List;

public class MyAccountMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.MY_ACCOUNT;

    private MenuTypes callingMenu;

    public MyAccountMenu(MenuTypes callingMenu) {
        this.callingMenu = callingMenu;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        if (!isLoggedIn()){
            MenuTypes.LOGIN.setMenu(new LoginMenu(callingMenu));
            return MenuTypes.LOGIN;
        }

        consIO.printString(String.format("You are logged in as %s %s\n\n",
                currentClient.getFirstName(), currentClient.getLastName()));

        int option = doSelection();

        switch (option){
            case (0):
                consIO.printString("Not here yet\n");
                return THIS_MENU_TYPE;
            case (1):
                consIO.printString("Not here yet\n");
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

}
