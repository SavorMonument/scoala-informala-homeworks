package ro.siit.java10.evp.Console;

import ro.siit.java10.evp.Console.Menues.Menu;
import ro.siit.java10.evp.DealershipsCentral;

public class MenuFactory {

    public MenuFactory(DealershipsCentral dCentral, ConsoleIO consIO) {

        Menu.setUp(dCentral, consIO);
    }

    public  Menu getMenuInstance(Menu.MenuTypes type){
        assert (Menu.isSetUp());

//        if (!Menu.isSetUp())
//            throw new IllegalStateException("The class is not set up yet");

        return type.getMenu();
    }
}

