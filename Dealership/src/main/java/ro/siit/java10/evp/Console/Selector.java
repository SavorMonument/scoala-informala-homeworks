package ro.siit.java10.evp.Console;

import ro.siit.java10.evp.VehicleData;

import java.util.ArrayList;
import java.util.List;


/**
 * Takes a string list
 * Outputs a numbered list and takes a selection using consIO
 */
public class Selector {

    protected ConsoleIO consIO;
    private List<String> list;

    public Selector(ConsoleIO consIO, List<String> list) {

        this.consIO = consIO;
        this.list = list;
    }

    public int printListAndGetOption(){
        assert (list.size() > 0);

        for (int i = 0; i < list.size(); i++) {

            consIO.printString(String.format(" %d - %s\n", i, list.get(i)));
        }

        int option = consIO.readCondInt(0, list.size() - 1);

        return option;
    }

    public static <T> List<String> listToStringList(List<T> list){

        List<String> strList = new ArrayList<>();

        for (T instance: list){

            strList.add(instance.toString());
        }

        return strList;
    }

    public static List<String> vehicleDataListToStringList(List<VehicleData> list,
                                                           VehicleData.Options... options){

        List<String> strList = new ArrayList<>();

        for (VehicleData instance: list){

            strList.add(instance.stringRepresentation(options));
        }

        return strList;
    }
}
