package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.UI.Selector;

import java.util.ArrayList;
import java.util.List;

public class AddDealershipMenu extends Menu {

    private MenuTypes callingMenu;

    public AddDealershipMenu(MenuTypes callingMenu) {
        this.callingMenu = callingMenu;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        Dealership deal = readDealership();

        if (null == deal){
            consIO.printString("A problem occurred\n");
            return callingMenu;
        }

        dCentral.addDealership(deal);

        return callingMenu;

    }


    public Dealership readDealership() {

    String name;
    String location;

    System.out.print("Dealership name: ");
    name = consIO.readLine();

    if (null == name)
        return null;

    System.out.print("Dealership location: ");
    location = consIO.readLine();

    if (null == location)
        return null;

    return (new Dealership(name, location));
}
}
