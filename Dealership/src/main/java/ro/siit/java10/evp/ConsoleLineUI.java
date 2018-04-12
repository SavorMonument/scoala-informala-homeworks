package ro.siit.java10.evp;

import java.util.ArrayList;

public class ConsoleLineUI {

    private DealershipsCentral dCentral = new DealershipsCentral();
    private Client currentClient;
    private ConsoleIO consIO = new ConsoleIO();
    private State state = State.user;

    private enum State {
        user,
        moderator
    }

    public void lazySetUp(){
        Dealership newD = new Dealership("BMW", "Sea side");
        Vehicle myVehicle1 = new Vehicle();
        Vehicle myVehicle2 = new Vehicle();
        Vehicle myVehicle4 = new Vehicle();

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

        myVehicle4.setModel("7 series");
        myVehicle4.setProductionYear(2018);
        myVehicle4.setFastCharging(true);
        myVehicle4.setEnergyConsumption_KWperKm(430);

        newD.addVehicle(myVehicle1, 1500);
        newD.addVehicle(myVehicle2, 15000);
        newD.addVehicle(myVehicle4, 100);

        dCentral.addDealership(newD);

        Dealership newD1 = new Dealership("Sap", "Ocean side");
        Vehicle myVehicle3 = new Vehicle();

        myVehicle3.setModel("13 series");
        myVehicle3.setProductionYear(1239);
        myVehicle3.setFastCharging(true);
        myVehicle3.setEnergyConsumption_KWperKm(120);
        myVehicle3.setMotor(new Motor("Reanault", "14-xp", 200));
        myVehicle3.setBattery(new Battery("Tesla", "56.fd1", 4500));


        newD1.addVehicle(myVehicle1, 15100);

        dCentral.addDealership(newD1);

        currentClient = new Client("Alex", "Goia");
    }

    public void Do(){

        if ((null != currentClient) && (currentClient.getFIRST_NAME().equals("Mod")))
            state = State.moderator;
        else
            state = State.user;

        switch (state){
            case user:
                clientMainScreen();
                break;
            case moderator:
                modMainScreen();
                break;
        }
    }

    private boolean modMainScreen() {

        int currentOption;

        printModMainScreen();

        currentOption = consIO.readCondInt(0, 4);

        switch (currentOption){
            case (0):
                printAllDealershipsVehicleList();
                break;
            case (1):
                Dealership selectedDealership = selectDealership();
                if (null != selectedDealership)
                    while(modDealershipScreen(selectedDealership)) {}
                break;
            case (2):
                addDealership();
                break;
            case (3):
                removeDealership();
                break;
            case (4):
                currentClient = null;
                break;
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

    private void clientMainScreen(){

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
                tryLogin();
                break;
        }

    }

    private Boolean clientDealershipScreen(Dealership dealership){

        int option;

        if (dealership.getVehicleSorter().getStockVehicleList().size() == 0){
            consIO.printString("No vehicles in stock\n");
            consIO.getKeyboardInput();
            return false;
        }

        printClientDealershipScreen();

        option = consIO.readCondInt(0, 5);

        switch (option){
            case (0):
                ArrayList<Vehicle> stockVehicles = (ArrayList<Vehicle>) dealership.getVehicleSorter().getStockVehicleList();
                consIO.printVehicleList(dealership, stockVehicles, ConsoleIO.Options.YEAR);
                printMoreInfo(stockVehicles, dealership);
                break;
            case (1):
                ArrayList<Vehicle> fastChargingVehicles = (ArrayList<Vehicle>) (dealership.getVehicleSorter().getFastChargingList());
                consIO.printVehicleList(dealership, fastChargingVehicles, ConsoleIO.Options.YEAR, ConsoleIO.Options.FAST_CHARGING);
                printMoreInfo(fastChargingVehicles, dealership);
                break;
            case (2):
                ArrayList<Vehicle> horsepowerSortedVehicles = (ArrayList<Vehicle>) dealership.getVehicleSorter().getSortedHorsepowerList();
                consIO.printVehicleList(dealership, horsepowerSortedVehicles, ConsoleIO.Options.YEAR, ConsoleIO.Options.MOTOR);
                printMoreInfo(horsepowerSortedVehicles, dealership);
                break;
            case (3):
                ArrayList<Vehicle> priceSortedVehicles = (ArrayList<Vehicle>) dealership.getVehicleSorter().getSortedPriceList();
                consIO.printVehicleList(dealership, priceSortedVehicles, ConsoleIO.Options.YEAR, ConsoleIO.Options.PRICE);
                printMoreInfo(priceSortedVehicles, dealership);
                break;
            case (4):
                ArrayList<Vehicle> rangeSortedVehicles = (ArrayList<Vehicle>) dealership.getVehicleSorter().getSortedRangePerChargeList();
                consIO.printVehicleList(dealership, rangeSortedVehicles, ConsoleIO.Options.YEAR, ConsoleIO.Options.RANGE_PER_CHARGE);
                printMoreInfo(rangeSortedVehicles, dealership);
                break;
            case (5):
                return  false;
        }
        return true;
    }

    private void printModMainScreen(){

        consIO.printString("\n");
        consIO.printString("0 - print complete list of vehicles\n");
        consIO.printString("1 - select dealership\n");
        consIO.printString("2 - add dealership\n");
        consIO.printString("3 - remove dealership\n");
        consIO.printString("4 - logout\n");
    }

    private void printAllDealershipsVehicleList(){

        ArrayList<Dealership> dealershipNameList = (ArrayList<Dealership>) dCentral.getDealershipList();

        for (Dealership instance : dealershipNameList){

            consIO.printString(instance.getName() + "\n");

            consIO.printVehicleList(instance,
                    dCentral.getDealership(instance).getVehicleSorter().getAllVehicleList());
        }
    }

    private void addDealership(){

        Dealership toAdd = consIO.readDealership();

        if (null == toAdd)
            return;

        ArrayList<Dealership> dealershipNames = (ArrayList<Dealership>) dCentral.getDealershipList();

        for (Dealership instance : dealershipNames){
            if (instance.equals(toAdd)){
                consIO.printString("There already is a dealership there");
                return;
            }
        }

        dCentral.addDealership(toAdd);

    }

    private void removeDealership() {

        consIO.printNumberedList(dCentral.getDealershipList());
        int option = consIO.readCondInt(1, dCentral.getNumberOfDealerships());

        if (option != -1)
            dCentral.removeDealership(dCentral.getDealershipList().get(option));
    }

    private Dealership selectDealership() {

        int option;

        if (dCentral.getNumberOfDealerships() > 0) {
            consIO.printNumberedList(dCentral.getDealershipList());
            option = consIO.readCondInt(0, dCentral.getNumberOfDealerships() - 1);

            if (option != -1){
                return (dCentral.getDealership(dCentral.getDealershipList().get(option)));
            }
        }

        return null;
    }

    private void printModDealershipScreen(){

        System.out.print('\n');
        consIO.printString("0 - print vehicle list\n");
        consIO.printString("1 - set vehicle stock\n");
        consIO.printString("2 - add new vehicle\n");
        consIO.printString("3 - remove vehicle\n");
        consIO.printString("4 - back\n");
    }

    private void printDealershipVehiclesWPriceStockList(Dealership dealership) {

        ArrayList<Vehicle> vehicleList = (ArrayList<Vehicle>) dealership.getVehicleSorter().getAllVehicleList();

        consIO.printVehicleList(dealership, vehicleList, ConsoleIO.Options.YEAR,
                ConsoleIO.Options.PRICE, ConsoleIO.Options.STOCK);
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

        consIO.printString("0 - Print compleate list of vehicles in stock\n");
        consIO.printString("1 - Select dealership\n");
        consIO.printString("2 - MyAccount\n");
    }

    private void printAllDealershipsStockVehicleList(){

        ArrayList<Dealership> dealershipNameList = (ArrayList<Dealership>) dCentral.getDealershipList();

        for (Dealership instance : dealershipNameList){

            consIO.printString(instance.getName() + "\n");

            consIO.printVehicleList(instance,
                    dCentral.getDealership(instance).getVehicleSorter().getStockVehicleList());
        }

    }

    private void printClientDealershipScreen(){

        consIO.printString("0 - get list of vehicles in stock\n");
        consIO.printString("1 - get list of vehicles with fast charging\n");
        consIO.printString("2 - get list of vehicles sorted by Horsepower\n");
        consIO.printString("3 - get list of vehicles sorted by Price\n");
        consIO.printString("4 - get list of vehicles sorted by Range per charge\n");
        consIO.printString("5 - back\n");
    }

    private void printMoreInfo(ArrayList<Vehicle> vehicleList, Dealership dealership){

        consIO.printString("Pick a vehicle for more info or " + vehicleList.size() + " to back\n");
        int option = consIO.readCondInt(0, vehicleList.size());

        if ((option != -1) && (option != vehicleList.size())){

            consIO.printVehicle(dealership, vehicleList.get(option), ConsoleIO.Options.YEAR,
                    ConsoleIO.Options.RANGE_PER_CHARGE, ConsoleIO.Options.FAST_CHARGING,
                    ConsoleIO.Options.MOTOR, ConsoleIO.Options.BATTERY, ConsoleIO.Options.PRICE, ConsoleIO.Options.STOCK);

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
            tryLogin();
            if (null == currentClient)
                return;
        }

        consIO.printString("\n 0 - Full Price \n 1 - ask GreenBonus\n");

        int option = consIO.readCondInt(0, 1);

        if (option == -1){
            return;
        }

        boolean successful = false;

        if (option == 0) {
            successful = dCentral.makeSell(dealership, toSell, currentClient);
        } else {
            if (option == 1) {
                if (dCentral.isGreenBonusAvailable(toSell)) {
                    consIO.printString("The vehicle is eligible for Green Bonus, 1 to buy\n");
                    if (consIO.readCondInt(1, 1) == 1) {
                        successful = dCentral.makeGreenBonusSell(dealership, toSell, currentClient);
                    }
                }
                else{
                    consIO.printString("Green Bonus not available\n");
                }
            }
        }

        if (successful)
            consIO.printString("Congratulations!! You can see your purchase in your account" +
                    "(Not yet implemented)\n");
        else
            consIO.printString("Something went wrong, Please try again\n");
    }

    private void tryLogin(){

        if(null != currentClient) {
            consIO.printString("logged in as: " + currentClient.getFIRST_NAME() +
                    "\n0 - logout \n1 - back\n");

            int option = consIO.readCondInt(0, 1);

            if ((option == 1) || (option == -1))
                return;
            else {
                currentClient = null;
                return;
            }
        }

        consIO.printString("0 - new account \n1 - login existing account \n2 - back\n");

        int option = consIO.readCondInt(0, 2);

        if (option == 0){
            Client toAdd;
            if (null != (toAdd = consIO.readClient())){
                currentClient = toAdd;
                dCentral.addClient(toAdd);
            }
        }
        if (option == 1){
            consIO.printString("FristName: ");
            String firstName = consIO.readString();
            consIO.printString("LastName: ");
            String lastName = consIO.readString();

            if (null == (currentClient = dCentral.getClient(firstName, lastName))){
                consIO.printString("No account by that name\n");
                tryLogin();
            }
        }
    }


    public static void main(String[] args) {

        ConsoleLineUI user = new ConsoleLineUI();

        //user.lazySetUp();

        //user.dCentral.saveData();
        user.dCentral.loadData();

        while(true){
            user.Do();
            user.dCentral.saveData();
        }

    }
}
