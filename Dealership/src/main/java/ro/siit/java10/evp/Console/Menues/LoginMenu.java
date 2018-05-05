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

        if (option == -1){
            return THIS_MENU_TYPE;
        }

        if (option == 0){

            Client client;
            if (null != (client = consIO.readClient())){
                currentClient = client;
                dCentral.addClient(client);
            }
        }
        if (option == 1){
            if (!tryLoggingExistingAccount()) {
                consIO.printString("No account by that name\n\n");
                return THIS_MENU_TYPE;
            }
        }

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

        if (null != (currentClient = dCentral.getClient(firstName, lastName))) {
            return true;
        }

        return false;
    }
}