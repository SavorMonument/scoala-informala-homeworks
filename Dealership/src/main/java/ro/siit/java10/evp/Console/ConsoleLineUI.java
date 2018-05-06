package ro.siit.java10.evp.Console;

import ro.siit.java10.evp.*;
import ro.siit.java10.evp.Console.Menues.*;

public class ConsoleLineUI {

    private DealershipsCentral dCentral = new DealershipsCentral();
    private ConsoleIO consIO = new ConsoleIO();
    private Menu.MenuTypes currentMenuType = Menu.MenuTypes.MAIN_CLIENT;

    public ConsoleLineUI() {
        Menu.setUp(dCentral, consIO);
    }

    public void Do(){

        Menu menu = currentMenuType.getMenu();

        currentMenuType = menu.resolveMenuAndGetNextType();
    }

    public static void main(String[] args) {

        ConsoleLineUI user = new ConsoleLineUI();


        //user.dCentral.saveData();
        user.dCentral.loadData();

        while(true){
            user.Do();
            user.dCentral.saveData();
        }

    }

}
