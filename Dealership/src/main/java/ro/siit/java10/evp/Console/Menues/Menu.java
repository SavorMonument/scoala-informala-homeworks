package ro.siit.java10.evp.Console.Menues;


import ro.siit.java10.evp.*;
import ro.siit.java10.evp.Console.*;

import java.util.List;

public abstract class Menu {

    protected static DealershipsCentral dCentral;
    protected static ConsoleIO consIO;

    protected static Client currentClient;

    static {
        MenuTypes.MAIN_CLIENT.setMenu(new MainClientMenu());
        MenuTypes.MAIN_MOD.setMenu(new MainModMenu());
    }

    public enum MenuTypes {
        MAIN_CLIENT,
        MAIN_MOD,

        PICK_DEALERSHIP,
        PER_DEALERSHIP_CLIENT,
        PER_DEALERSHIP_MOD,
        PICK_VEHICLE,
        INDIVIDUAL_VEHICLE,
        MY_ACCOUNT,
        LOGIN,
        SELL,
        ADD_DEALERSHIP,
        REMOVE_DEALERSHIP;

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

    protected boolean isLoggedIn() {

        return (null != currentClient);
    }
    protected boolean isModLogged(){

        if (!isLoggedIn())
            return false;

        return currentClient.getFirstName().equals("Mod");
    }

    protected void addDealership(){

        Dealership toAdd = consIO.readDealership();

        if (null == toAdd)
            return;

        List<Dealership> dealershipNames = dCentral.getDealershipList();

        for (Dealership instance : dealershipNames){
            if (toAdd.equals(instance)){
                consIO.printString("There already is a deals there");
                return;
            }
        }

        dCentral.addDealership(toAdd);
    }
    protected void removeDealership() {

        consIO.printNumberedList(dCentral.getDealershipList());
        int option = consIO.readCondInt(1, dCentral.getNumberOfDealerships());

        if (option != -1)
            dCentral.removeDealership(dCentral.getDealershipList().get(option));
    }

}
