package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.UI.Selector;

import java.util.List;

public class RemoveDealershipMenu extends Menu {

    protected RemoveDealershipMenu(MenuTypes callingMenu) {

        super(callingMenu);
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        List<Dealership> dealList = dCentral.getDealershipList();

        if (dealList.size() == 0){
            consIO.printString("No dealerships to delete\n");
            return callingMenu;
        }

        int options = doSelection();

        if (options != dealList.size())
            dCentral.removeDealership(dealList.get(options));

        return callingMenu;
    }

    private int doSelection(){

        List<String> selections = Selector.listToStringList(dCentral.getDealershipList());
        selections.add("Back");

        Selector selector = new Selector(consIO, selections);

        return selector.printListAndGetOption();
    }
}
