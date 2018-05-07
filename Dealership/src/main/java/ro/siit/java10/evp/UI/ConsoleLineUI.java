package ro.siit.java10.evp.UI;

import ro.siit.java10.evp.*;
import ro.siit.java10.evp.UI.Menues.Menu;

import java.io.*;

public class ConsoleLineUI {

    private DealershipsCentral dCentral = new DealershipsCentral();
    private ConsoleIO consIO;

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

        Menu.resolveMenuInstance();

        dCentral.saveData();
    }

    public static void main(String[] args){

        ConsoleLineUI cLineUI = new ConsoleLineUI();

        cLineUI.dCentral.loadData();

        while(true){
            cLineUI.Do();
        }
    }

}
