package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;

public abstract class PerDealershipMenuFactory extends Menu {

    protected PerDealershipMenuFactory(MenuTypes callingMenu) {

        super(callingMenu);
    }

    public static PerDealershipMenuFactory getDealershipMenu(MenuTypes callingMenu, Dealership deal){

        if (isModLogged())
            return new PerDealershipModMenu(callingMenu, deal);
        else
            return new PerDealershipClientMenu(callingMenu, deal);
    }
}
