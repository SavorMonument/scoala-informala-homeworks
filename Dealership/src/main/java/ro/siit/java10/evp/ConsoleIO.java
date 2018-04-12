package ro.siit.java10.evp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleIO {

    private Scanner Stdin;

    public enum Options{
        YEAR,
        E_CONSUMPTION,
        RANGE_PER_CHARGE,
        FAST_CHARGING,
        MOTOR,
        BATTERY,
        PRICE,
        STOCK
    }

    public ConsoleIO() {

        Stdin = new Scanner(System.in);
    }

    public Client makeAccount(){
        //Writer

        return null;
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

    public void printVehicleList(Dealership deals, List<Vehicle> vehicles, Options... options){

        for (Vehicle currVehicle : vehicles){

            printVehicle(deals, currVehicle, options);
        }
    }

    public void printVehicle(Dealership deals, Vehicle vehicle, Options... options){

        printString("Model: " + vehicle.getModel() + "   ");

        for (Options opt : options) {

            switch (opt) {
                case YEAR:
                    printString("Production Year: " + vehicle.getProductionYear() + "   ");
                    break;
                case E_CONSUMPTION:
                    printString("Energy Consumption(kw/km): " + vehicle.getEnergyConsumption_KWperKm() + "   ");
                    break;
                case RANGE_PER_CHARGE:
                    printString("Range per full charge(km): " + vehicle.getRangePerCharge_Km() + "   ");
                    break;
                case FAST_CHARGING:
                    if (vehicle.hasFastCharging())
                        printString("Has fast charging   ");
                    else
                        printString("It does not have fast charging   ");
                    break;
                case MOTOR:
                    printString("\nMotor: " + vehicle.getMotor().toString() + "   \n");
                    break;
                case BATTERY:
                    printString("\nBattery: " + vehicle.getBattery().toString() + "   \n");
                    break;
                case PRICE:
                    printString("\nPrice: " + deals.getVehiclePrice(vehicle.hashCode()) + "\n");
                    break;
                case STOCK:
                    printString("\nAvailable: " + deals.getVehicleAvailability(vehicle.hashCode()) + "\n");
                    break;
            }
        }
        printString("\n");
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

    public Vehicle readVehicle(){

        String strInput;
        int intInput;

        Vehicle inConstruction = new Vehicle();

        System.out.print("Vehicle name: ");
        if (null == (strInput = readString()))
            return null;
        inConstruction.setModel(strInput);

        System.out.print("Vehicle production year: ");
        if (-1 == (intInput = readCondInt(1900, 2018)))
            return null;
        inConstruction.setProductionYear(intInput);

        System.out.print("Vehicle energy consumption KW/Km: ");
        if (-1 == (intInput = readCondInt(1, 1000)))
            return null;
        inConstruction.setEnergyConsumption_KWperKm(intInput);

        System.out.print("Vehicle has fast charging(1 or 0): ");
        if (-1 == (intInput = readCondInt(0, 1)))
            return null;
        if (intInput == 1){
            inConstruction.setFastCharging(true);
        } else
            inConstruction.setFastCharging(false);

        return inConstruction;
    }

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
