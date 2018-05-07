package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Client;
import ro.siit.java10.evp.UI.Selector;

import java.util.ArrayList;
import java.util.List;

public class LoginMenu extends Menu {

    private static final MenuTypes THIS_MENU_TYPE = MenuTypes.LOGIN;

    protected LoginMenu(MenuTypes callingMenu) {

        super(callingMenu);
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {
        assert (null == currentClient);

        int option = doSelection();

        switch (option){
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

        return MenuTypes.MAIN_MENU;
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
        String firstName = consIO.readLine();

        if (null == firstName)
            return false;

        consIO.printString("LastName: ");
        String lastName = consIO.readLine();

        return (null != (currentClient = dCentral.getClient(firstName, lastName)));
    }

    private Client tryReadingClient(){

        String input;
        String firstName;
        String lastName;

        consIO.printString("First Name: ");
        if (null == (firstName = consIO.readLine()))
            return null;

        consIO.printString("Last Name: ");
        if (null == (lastName = consIO.readLine()))
            return null;

        Client underConstruction = new Client(firstName, lastName);

        consIO.printString("Phone number: ");
        if (null == (input = consIO.readLine()))
            return null;
        underConstruction.setTelephone(input);

        consIO.printString("Address: ");
        if (null == (input = consIO.readLine()))
            return null;
        underConstruction.setAddress(input);

        return underConstruction;
    }

}