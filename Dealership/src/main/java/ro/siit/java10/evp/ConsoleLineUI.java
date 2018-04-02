package ro.siit.java10.evp;

import java.util.ArrayList;

public class ConsoleLineUI {

    private DealershipsCentral dealershipsCentral = new DealershipsCentral();
    private Client currentClient;
    private ConsoleIO consIO = new ConsoleIO();
    private State state = State.user;

    private enum State {
        user,
        moderator
    }

    public void Do(){

        //if (state == State.moderator)
        ClientMainScreen();
    }

    private boolean modMainScreen() {

        int currentOption;
        boolean isdone = false;

        printMainModeratorScreen();

        currentOption = consIO.readCondInt(0, 5);

        switch (currentOption){
            case (0):
                printAllDealershipsVehicleList();
                break;
            case (1):
                printDealershipList();
                break;
            case (2):
                Dealership selectedDealership = selectDealership();
                if (null != selectedDealership)
                    while(modDealershipScreen(selectedDealership)) {}
                break;
            case (3):
                addDealership();
                break;
            case (4):
                removeDealership();
                break;
            default :
                return false;
        }
        return (true);
    }

    private boolean modDealershipScreen(Dealership selectedDealership){

        int option;

        printModDealershipScreen();

        option = consIO.readCondInt(0, 4);

        switch (option) {
            case (0): {
                printDealershipVehiclesWPriceStockList(selectedDealership);
                break;
            }
            case (1): {
                setStock(selectedDealership);
            }
            break;
            case (2): {
                addVehicle(selectedDealership);
                break;
            }
            case (3): {
                removeVehicle(selectedDealership);
                break;
            }
            case (4):{
                return false;
            }
        }

        return true;
    }

    private void ClientMainScreen(){

        int option;

        printClientMainScreen();

        option = consIO.readCondInt(0, 2);

        switch (option){
            case (0):
                printAllDealershipsStockVehicleList();
                break;
            case (1):
                Dealership selectedDealership = selectDealership();
                if (null != selectedDealership){
                    while(clientDealershipScreen(selectedDealership)){}
                }
                break;
            case (2):
                login();
                break;
        }

    }

    private Boolean clientDealershipScreen(Dealership dealership){

        int option;

        if (dealership.getVehicleSorter().getStockVehicleList().size() == 0){
            consIO.printString("No vehicles in stock");
            consIO.getKeyboardInput();
            return false;
        }

        printClientDealershipScreen();

        option = consIO.readCondInt(0, 5);

        switch (option){
            case (0):
                ArrayList<Vehicle> stockVehicles = (ArrayList<Vehicle>) dealership.getVehicleSorter().getStockVehicleList();
                consIO.printNumberedList(stockVehicles);
                printMoreInfo(stockVehicles, dealership);
                break;
            case (1):
                consIO.printNumberedList(dealership.getVehicleSorter().getFastChargingList());
                break;
            case (2):
                consIO.printNumberedList(dealership.getVehicleSorter().getSortedHorsepowerList());
                break;
            case (3):
                consIO.printNumberedList(dealership.getVehicleSorter().getSortedPriceList());
                break;
            case (4):
                consIO.printNumberedList(dealership.getVehicleSorter().getSortedRangePerChargeList());
                break;
            case (5):
                return  false;
        }
        return true;
    }


    public void addDealership(Dealership deal){

        dealershipsCentral.addDealeship(deal);
    }

    private void printMainModeratorScreen(){

        System.out.print('\n');
        System.out.println("0 - print compleate list of vehicles");
        System.out.println("1 - print dealership list");
        System.out.println("2 - select dealership");
        System.out.println("3 - add dealership");
        System.out.println("4 - remove dealership");
        System.out.println("5 - back");
    }

    private void printAllDealershipsVehicleList(){

        ArrayList<Dealership> dealershipNameList = (ArrayList<Dealership>) dealershipsCentral.getDealershipList();

        for (Dealership instance : dealershipNameList){

            consIO.printString(instance.getName() + "\n");

            consIO.printList(dealershipsCentral.getDealershipVehicleSorter(instance).getAllVehicleList());
        }
    }

    private void printDealershipList(){

        consIO.printList(dealershipsCentral.getDealershipList());
    }

    private void addDealership(){

        Dealership toAdd = consIO.readDealership();

        if (null == toAdd)
            return;

        ArrayList<Dealership> dealershipNames = (ArrayList<Dealership>) dealershipsCentral.getDealershipList();

        for (Dealership instance : dealershipNames){
            if (instance.equals(toAdd)){
                System.out.println("There already is a dealership there");
                return;
            }
        }

        dealershipsCentral.addDealeship(toAdd);

    }

    private void removeDealership() {

        consIO.printNumberedList(dealershipsCentral.getDealershipList());
        int option = consIO.readCondInt(1, dealershipsCentral.getNumberOfDealerships());

        if (option != -1)
            dealershipsCentral.removeDealership(dealershipsCentral.getDealershipList().get(option));
    }

    private Dealership selectDealership() {

        int option;

        if (dealershipsCentral.getNumberOfDealerships() > 0) {
            consIO.printNumberedList(dealershipsCentral.getDealershipList());
            option = consIO.readCondInt(0, dealershipsCentral.getNumberOfDealerships() - 1);

            if (option != -1){
                return (dealershipsCentral.getDealership(dealershipsCentral.getDealershipList().get(option)));
            }
        }

        return null;
    }

    private void printModDealershipScreen(){

        System.out.print('\n');
        System.out.println("0 - print vehicle list");
        System.out.println("1 - set vehicle stock");
        System.out.println("2 - add new vehicle");
        System.out.println("3 - remove vehicle");
        System.out.println("4 - back");
    }

    private void printDealershipVehiclesWPriceStockList(Dealership dealership) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) dealership.getVehicleSorter().getAllVehicleList();

        for (Vehicle instance : vehicleList){
            System.out.println(instance);
            System.out.print("Price: " + dealership.getVehiclePrice(instance.hashCode()) + " ");
            System.out.println("Stock: " + dealership.getVehicleAvailability(instance.hashCode()));
        }
    }

    private void setStock(Dealership dealership) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) dealership.getVehicleSorter().getAllVehicleList();

        consIO.printNumberedList(vehicleList);

        int toSet = consIO.readCondInt(0, vehicleList.size());
        if (toSet != -1) {

            int ammount = consIO.readCondInt(0, 1000);

            if (ammount != -1)
                dealership.setStockNumber(vehicleList.get(toSet).hashCode(), ammount);
        }
    }

    private void addVehicle(Dealership dealership) {

        Vehicle toAdd = consIO.readVehicle();

        if (null != toAdd) {

            consIO.printString("Price: ");
            int vehiclePrice = consIO.readCondInt(0, 100000);

            if (vehiclePrice != -1) {
                dealership.addVehicle(toAdd, vehiclePrice);
            }
        }
    }

    private void removeVehicle(Dealership dealership){

        ArrayList<Vehicle> allVehicles = (ArrayList<Vehicle>) dealership.getVehicleSorter().getAllVehicleList();

        consIO.printNumberedList(allVehicles);

        int toRemove = consIO.readCondInt(0, allVehicles.size());

        if (toRemove != -1){
            dealership.removeVehicle(allVehicles.get(toRemove).hashCode());
        }
    }

    private void printClientMainScreen(){

        System.out.println("0 - get compleate list of vehicles in stock");
        System.out.println("1 - select dealership");
        System.out.println("2 - make account");
    }

    private void printAllDealershipsStockVehicleList(){

        ArrayList<Dealership> dealershipNameList = (ArrayList<Dealership>) dealershipsCentral.getDealershipList();

        for (Dealership instance : dealershipNameList){

            consIO.printString(instance.getName() + "\n");

            consIO.printList(dealershipsCentral.getDealershipVehicleSorter(instance).getStockVehicleList());
        }

    }

    private void printClientDealershipScreen(){

        System.out.println("0 - get list of vehicles in stock");
        System.out.println("1 - get list of vehicles with fast charging");
        System.out.println("2 - get list of vehicles sorted by Horsepower");
        System.out.println("3 - get list of vehicles sorted by Price");
        System.out.println("4 - get list of vehicles sorted by Range per charge");
        System.out.println("5 - back");
    }

    private void printMoreInfo(ArrayList<Vehicle> vehicleList, Dealership dealership){

        consIO.printString("Pick a vehicle for more info or " + vehicleList.size() + " to back\n");
        int option = consIO.readCondInt(0, vehicleList.size());

        if ((option != -1) && (option != vehicleList.size())){
            consIO.printString(vehicleList.get(option).toString() + "\n");
            consIO.printString("Price: " + dealership.getVehiclePrice(vehicleList.get(option).hashCode()) + "\n");
            consIO.printString("Stock: " + dealership.getVehicleAvailability(vehicleList.get(option).hashCode()) + "\n");

            if(pitchSell()){
                makeSell(vehicleList.get(option), dealership);
            }
        }

    }

    private boolean pitchSell(){

        consIO.printString("\n 1 - Buy \n 0 - Back\n");
        int option = consIO.readCondInt(0, 1);

        if (option == 1){
            return true;
        }
        return false;
    }

    private void makeSell(Vehicle toSell, Dealership dealership){

        if (null == currentClient){
            login();
            if (null == currentClient)
                return;
        }

        consIO.printString("\n 0 - Full Price \n 1 - ask GreenBonus\n");

        int option = consIO.readCondInt(0, 1);

        if (option == -1) {
            return;
        }

        boolean succesfull = false;

        if (option == 0) {
            succesfull = dealershipsCentral.makeSell(dealership, toSell, currentClient);
        }
        if (option == 1){
            if (dealershipsCentral.isGreenBonusAvailable(toSell)) {
                consIO.printString("The vehicle is eligible for Green Bonus, 1 to continue\n");
                if (consIO.readCondInt(1, 1) == 1) {
                    succesfull = dealershipsCentral.makeGreenBonusSell(dealership, toSell, currentClient);
                }
            }
        }

        if (succesfull)
            consIO.printString("Congratulations!! You can see your purchase in your account\n");
        else
            consIO.printString("Something went wrong, Please try again\n");
    }

    public void login(){

        if(null != currentClient) {
            consIO.printString("Already logged in as: " + currentClient.getFirstName() +
                    "\n0 - logout \n1 - back\n");

            int option = consIO.readCondInt(0, 1);

            if ((option == 1) || (option == -1))
                return;
            else {
                currentClient = null;
                return;
            }
        }

        consIO.printString("0 - new account \n1 - login existing account \n2 - exit\n");

        int option = consIO.readCondInt(0, 2);

        if (option == 0){
            Client toAdd;
            if (null != (toAdd = consIO.readClient())){
                currentClient = toAdd;
                dealershipsCentral.addClient(toAdd);
            }
        }
        if (option == 1){
            consIO.printString("FristName: ");
            String firstName = consIO.readString();
            consIO.printString("LastName: ");
            String lastName = consIO.readString();

            if (null == (currentClient = dealershipsCentral.getClient(firstName, lastName))){
                login();
            }
        }
    }


    public static void main(String[] args) {

        ConsoleLineUI user = new ConsoleLineUI();

        {
            Dealership newD = new Dealership("BMW", "Sea side");
            Vehicle myVehicle1 = new Vehicle();
            Vehicle myVehicle2 = new Vehicle();

            myVehicle1.setModel("3 series");
            myVehicle1.setProductionYear(1999);
            myVehicle1.setFastCharging(true);
            myVehicle1.setEnergyConsumption_KWperKm(120);
            myVehicle1.setMotor(new Motor("Reanault", "14-xp", 200));
            myVehicle1.setBattery(new Battery("Tesla", "56.fd1", 4500));

            myVehicle2.setModel("1 series");
            myVehicle2.setProductionYear(2011);
            myVehicle2.setFastCharging(false);
            myVehicle2.setEnergyConsumption_KWperKm(430);

            newD.addVehicle(myVehicle1, 1500);
            newD.addVehicle(myVehicle2, 15000);

            user.addDealership(newD);
        }

        {
            Dealership newD = new Dealership("Sap", "Ocean side");
            Vehicle myVehicle1 = new Vehicle();

            myVehicle1.setModel("13 series");
            myVehicle1.setProductionYear(1239);
            myVehicle1.setFastCharging(true);
            myVehicle1.setEnergyConsumption_KWperKm(120);
            myVehicle1.setMotor(new Motor("Reanault", "14-xp", 200));
            myVehicle1.setBattery(new Battery("Tesla", "56.fd1", 4500));


            newD.addVehicle(myVehicle1, 15100);

            user.addDealership(newD);
        }

        while(true){
            user.Do();
        }
    }
}
