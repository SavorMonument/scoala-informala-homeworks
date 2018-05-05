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

//    private void clientMainScreen(){
//
//        int option;
//
//        printClientMainScreen();
//
//        option = consIO.readCondInt(0, 2);
//
//        switch (option){
//            case (0):
//                printAllDealershipsStockVehicleList();
//                break;
//            case (1):
//                Dealership selectedDealership = selectDealership();
//                if (null != selectedDealership){
//                    while(clientDealershipScreen(selectedDealership)){}
//                }
//                break;
//            case (2):
//                tryLogin();
//                break;
//        }
//
//    }
//
//    private void addDealership(){
//
//        Dealership toAdd = consIO.readDealership();
//
//        if (null == toAdd)
//            return;
//
//        List<Dealership> dealershipNames = dCentral.getDealershipList();
//
//        for (Dealership instance : dealershipNames){
//            if (toAdd.equals(instance)){
//                consIO.printString("There already is a dealership there");
//                return;
//            }
//        }
//
//        dCentral.addDealership(toAdd);
//    }
//
//    private void removeDealership() {
//
//        consIO.printNumberedList(dCentral.getDealershipList());
//        int option = consIO.readCondInt(1, dCentral.getNumberOfDealerships());
//
//        if (option != -1)
//            dCentral.removeDealership(dCentral.getDealershipList().get(option));
//    }
//
//    private void setStock(Dealership dealership) {
//
//        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) dealership.getVehicleSorter().getAllVehicleList();
//
//        consIO.printNumberedList(vehicleList);
//
//        int toSet = consIO.readCondInt(0, vehicleList.size());
//        if (toSet != -1) {
//
//            int ammount = consIO.readCondInt(0, 1000);
//
//            if (ammount != -1)
//                dealership.setStockNumber(vehicleList.get(toSet).hashCode(), ammount);
//        }
//    }
//
//    private void addVehicle(Dealership dealership) {
//
//        Vehicle toAdd = consIO.readVehicle();
//
//        if (null != toAdd) {
//
//            consIO.printString("Price: ");
//            int vehiclePrice = consIO.readCondInt(0, 100000);
//
//            if (vehiclePrice != -1) {
//                dealership.addVehicle(toAdd, vehiclePrice);
//            }
//        }
//    }
//
//    private void removeVehicle(Dealership dealership){
//
//        ArrayList<Vehicle> allVehicles = (ArrayList<Vehicle>) dealership.getVehicleSorter().getAllVehicleList();
//
//        consIO.printNumberedList(allVehicles);
//
//        int toRemove = consIO.readCondInt(0, allVehicles.size());
//
//        if (toRemove != -1){
//            dealership.removeVehicle(allVehicles.get(toRemove).hashCode());
//        }
//    }
//
//    private void printClientMainScreen(){
//
//        consIO.printString("0 - Print compleate list of vehicles in stock\n");
//        consIO.printString("1 - Select dealership\n");
//        consIO.printString("2 - MY_ACCOUNT\n");
//    }
//
//    private void printAllDealershipsStockVehicleList(){
//
//        List<Dealership> dealershipNameList = dCentral.getDealershipList();
//
//        for (Dealership instance : dealershipNameList){
//
//            consIO.printString(instance.getName() + "\n");
//
//            consIO.printVehicleList(instance,
//                    instance.getVehicleSorter().getStockVehicleList());
//        }
//
//    }
//
//    private void printMoreInfo(ArrayList<Vehicle> vehicleList, Dealership dealership){
//
//        consIO.printString("Pick a vehicle for more info or " + vehicleList.size() + " to back\n");
//        int option = consIO.readCondInt(0, vehicleList.size());
//
//        if ((option != -1) && (option != vehicleList.size())){
//
//            consIO.printVehicle(dealership, vehicleList.get(option), consIO.Options.YEAR,
//                    consIO.Options.RANGE_PER_CHARGE, consIO.Options.FAST_CHARGING,
//                    consIO.Options.MOTOR, consIO.Options.BATTERY, consIO.Options.PRICE, consIO.Options.STOCK);
//
//            if(pitchSell()){
//                makeSell(vehicleList.get(option), dealership);
//            }
//        }
//
//    }

//    private boolean pitchSell(){
//
//        consIO.printString("\n 1 - Buy \n 0 - Back\n");
//        int option = consIO.readCondInt(0, 1);
//
//        if (option == 1){
//            return true;
//        }
//        return false;
//    }
//
//    private void makeSell(Vehicle toSell, Dealership dealership){
//
//
//    }
//
//    private void tryLogin(){
//
//
//    }

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
