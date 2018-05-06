package ro.siit.java10.evp.UI;

import ro.siit.java10.evp.VehicleData;

import java.util.ArrayList;
import java.util.List;


/**
 * Takes a list of strings outputs it numbered one per line and takes a selection using ConsoleIO
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

        if (option == -1)
            return printListAndGetOption();
        else
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
