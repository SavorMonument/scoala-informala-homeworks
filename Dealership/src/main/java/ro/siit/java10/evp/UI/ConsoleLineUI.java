package ro.siit.java10.evp.UI;

import ro.siit.java10.evp.*;
import ro.siit.java10.evp.UI.Menues.*;

import java.io.*;

public class ConsoleLineUI {

    private DealershipsCentral dCentral = new DealershipsCentral();
    private ConsoleIO consIO;
    private Menu.MenuTypes currentMenuType = Menu.MenuTypes.MAIN_MENU;

    public ConsoleLineUI() {

        consIO = new ConsoleIO(getBWriter(), getBReader());

        Menu.setUp(dCentral, consIO);
    }

    private BufferedReader getBReader() {

        return new BufferedReader(new InputStreamReader(System.in));
    }

    private BufferedWriter getBWriter() {

        return new BufferedWriter(new OutputStreamWriter(System.out));
    }


    public void Do(){

        Menu menu = currentMenuType.getMenu();

        currentMenuType = menu.resolveMenuAndGetNextType();

        dCentral.saveData();
    }

    public static void main(String[] args){

        ConsoleLineUI user = new ConsoleLineUI();

        user.dCentral.loadData();

        while(true){
            user.Do();
        }
    }

}
