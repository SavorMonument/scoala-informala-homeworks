package ro.siit.java10.evp.Console;

import ro.siit.java10.evp.Client;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.Vehicle;
import ro.siit.java10.evp.VehicleData;

import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    private Scanner Stdin;

    public ConsoleIO() {

        Stdin = new Scanner(System.in);
    }

    public void printList(List toPrintList){

        for (int i = 0; i < toPrintList.size(); i++){

            System.out.println(toPrintList.get(i).toString());
        }
    }

    public void printNumberedList(List toPrintList){

        for (int i = 0; i < toPrintList.size(); i++){

            System.out.println(i + " - " + toPrintList.get(i).toString());
        }
    }

    public void printString(String toPrintString){

        System.out.print(toPrintString);
    }

    /**
     * Attempts to read a non empty String for console
     *
     * @return String or null if can't read non empty
     */
    public String readString(){

        String input;

        input = Stdin.nextLine();

        if (input.isEmpty()){
            printString("Can't be empty, 1 to try again\n");

            String option = Stdin.nextLine();
            if (isInt(option)){
                if (Integer.parseInt(option) == 1){
                    return readString();
                }
            }
            return null;
        }

        return input;
    }

    public void getKeyboardInput(){

        Stdin.nextLine();
    }

    public int readCondInt(int min, int max){

        int option;
        String line;

        line = Stdin.nextLine();

        if (isInt(line)){

            option = Integer.parseInt(line);
            if ((option >= min) && (option <= max)){
                return option;
            }
        }

        System.out.println("Looks like the number is not good, 1 to try again");
        line = Stdin.nextLine();

        if (isInt(line)){

            option = Integer.parseInt(line);
            if (option == 1){
                return readCondInt(min, max);
            }
        }

        return (-1);
    }

    /**
     * Checks if string is int
     * range: -99,999,999 to 999,999,999
     *
     * @return boolean
     */
    private boolean isInt(String str){

        if ((str.length() < 9) && (str.length() > 0)){

            int i = 0;

            if (str.charAt(0) == '-') {
                i = 1;
            }

            while (i < str.length()) {
                if (!isDigit(str.charAt(i)))
                    return false;
                i++;
            }

            return true;
        }

        return false;
    }

    private boolean isDigit(char c) {

        return ((c <= '9') && (c >= '0'));
    }

    public Dealership readDealership() {

        String name;
        String location;

        System.out.print("Dealership name: ");
        name = readString();

        if (null == name)
            return null;

        System.out.print("Dealership location: ");
        location = readString();

        if (null == location)
            return null;

        return (new Dealership(name, location));
    }

//    public Vehicle readVehicle(){
//
//        String strInput;
//        int intInput;
//
//        Vehicle inConstruction = new Vehicle();
//
//        System.out.print("Vehicle name: ");
//        if (null == (strInput = readString()))
//            return null;
//        inConstruction.setModel(strInput);
//
//        System.out.print("Vehicle production year: ");
//        if (-1 == (intInput = readCondInt(1900, 2018)))
//            return null;
//        inConstruction.setProductionYear(intInput);
//
//        System.out.print("Vehicle energy consumption KW/Km: ");
//        if (-1 == (intInput = readCondInt(1, 1000)))
//            return null;
//        inConstruction.setEnergyConsumptionKWperKm(intInput);
//
//        System.out.print("Vehicle has fast charging(1 or 0): ");
//        if (-1 == (intInput = readCondInt(0, 1)))
//            return null;
//        if (intInput == 1){
//            inConstruction.setFastCharging(true);
//        } else
//            inConstruction.setFastCharging(false);
//
//        return inConstruction;
//    }

    public Client readClient(){

        String input;
        String firstname;
        String lastName;

        printString("First Name: ");
        if (null == (firstname = readString()))
            return null;

        printString("Last Name: ");
        if (null == (lastName = readString()))
            return null;

        Client underConstruction = new Client(firstname, lastName);

        printString("Phone number: ");
        if (null == (input = readString()))
            return null;
        underConstruction.setTelephone(input);

        printString("Address: ");
        if (null == (input = readString()))
            return null;
        underConstruction.setAddress(input);

        return underConstruction;
    }

    public void close(){

        Stdin.close();
    }

}
