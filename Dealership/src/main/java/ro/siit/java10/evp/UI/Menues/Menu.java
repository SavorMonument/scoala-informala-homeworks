package ro.siit.java10.evp.UI.Menues;


import ro.siit.java10.evp.*;
import ro.siit.java10.evp.UI.*;

public abstract class Menu {

    protected static DealershipsCentral dCentral;
    protected static ConsoleIO consIO;

    protected static Client currentClient;

    public enum MenuTypes {

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

    public abstract MenuTypes resolveMenuAndGetNextType();

    public static void setUp(DealershipsCentral dCent, ConsoleIO cons){

        dCentral = dCent;
        consIO = cons;
    }
    public static boolean isSetUp(){

        return ((null != consIO) || (null != dCentral));
    }

    protected static boolean isLoggedIn() {

        return (null != currentClient);
    }
    protected static boolean isModLogged(){

        if (!isLoggedIn())
            return false;

        return currentClient.getFirstName().equals("Mod");
    }

}
