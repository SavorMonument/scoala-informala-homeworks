package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Client;
import ro.siit.java10.evp.Console.Selector;

import java.util.ArrayList;
import java.util.List;

public class LoginMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.LOGIN;

    private MenuTypes callingMenu;

    public LoginMenu(MenuTypes callingMenu) {

        this.callingMenu = callingMenu;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {
        assert (null == currentClient);

        int option = doSelection();

        switch (option){
            case (-1):
                return THIS_MENU_TYPE;
            case (0):
                Client client;
                if (null != (client = tryReadingClient())){
                    currentClient = client;
                    dCentral.addClient(client);
                } else
                    return THIS_MENU_TYPE;
                break;
            case (1):
                if (!tryLoggingExistingAccount()) {
                    consIO.printString("No account by that name\n\n");
                    return THIS_MENU_TYPE;
                }
                break;
        }

        if (isModLogged())
            return MenuTypes.MAIN_MOD;
        else
            return callingMenu;
    }

    private int doSelection(){

        List<String> selections = new ArrayList<>();
        selections.add("New account");
        selections.add("Login existing account");
        selections.add("back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }

    private boolean tryLoggingExistingAccount(){

        consIO.printString("FirstName: ");
        String firstName = consIO.readString();
        consIO.printString("LastName: ");
        String lastName = consIO.readString();

        return (null != (currentClient = dCentral.getClient(firstName, lastName)));
    }

    private Client tryReadingClient(){

        String input;
        String firstName;
        String lastName;

        consIO.printString("First Name: ");
        if (null == (firstName = consIO.readString()))
            return null;

        consIO.printString("Last Name: ");
        if (null == (lastName = consIO.readString()))
            return null;

        Client underConstruction = new Client(firstName, lastName);

        consIO.printString("Phone number: ");
        if (null == (input = consIO.readString()))
            return null;
        underConstruction.setTelephone(input);

        consIO.printString("Address: ");
        if (null == (input = consIO.readString()))
            return null;
        underConstruction.setAddress(input);

        return underConstruction;
    }

}