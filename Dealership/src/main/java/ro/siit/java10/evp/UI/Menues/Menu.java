package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.*;
import ro.siit.java10.evp.UI.*;

/**
 * This abstract class defines how menu screen class should be structured
 *
 * The class has to be set up before use i.e. it should be assigned a DealershipCentral and ConsoleIO
 *
 * Each class that extends from this one has to implement resolveMenuAndGetNextType
 * this function should print a menu do whatever and construct the next menu screen
 * assign it to it's enum and return that MenuType
 *
 * The purpose of the MenuType enum is to hold a history of previous Menus so you can
 * jump between them
 *
 */
public abstract class Menu {

    protected static DealershipsCentral dCentral;
    protected static ConsoleIO consIO;
    protected static Client currentClient;

    protected MenuTypes callingMenu;

    public Menu(MenuTypes callingMenu) {

        this.callingMenu = callingMenu;
    }

    public abstract MenuTypes resolveMenuAndGetNextType();


    private static MenuTypes currentMenu = MenuTypes.MAIN_MENU;

    protected enum MenuTypes {

        MAIN_MENU{
            @Override
            public Menu getMenu() {
                if (isModLogged())
                    return new MainModMenu();
                else
                    return new MainClientMenu();
            }
        },

        PICK_DEALERSHIP,
        PER_DEALERSHIP,
        PICK_VEHICLE,
        PER_VEHICLE,
        MY_ACCOUNT,
        LOGIN,
        SELL,
        ADD_DEALERSHIP,
        REMOVE_DEALERSHIP,
        SET_VEHICLE_STOCK,
        ADD_VEHICLE,
        REMOVE_VEHICLE;

        Menu m;
        public void setMenu(Menu m) {
            this.m = m;
        }
        public Menu getMenu() {
            return m;
        }
    }

    public static void setUp( DealershipsCentral dCent, ConsoleIO cons){

        dCentral = dCent;
        consIO = cons;

    }

    public static void resolveMenuInstance(){

        if (!isSetUP())
            throw new IllegalStateException("You need not set up first");

        currentMenu = currentMenu.getMenu().resolveMenuAndGetNextType();
    }

    private static boolean isSetUP() {

        return (null != dCentral) && (null != consIO);
    }

    protected static boolean isNotLoggedIn() {

        return (null == currentClient);
    }
    protected static boolean isModLogged(){

        if (isNotLoggedIn())
            return false;

        return currentClient.getFirstName().equals("Mod");
    }

}
