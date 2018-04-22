package ro.siit.java10.evp;

import java.io.File;
import java.util.*;

public class DealershipsCentral{

    private ArrayList<Dealership> availableDealerships = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    public DealershipsCentral() {
    }

    public void saveData(){
        DealershipSerializer dealerSaver = new CsvDealershipSerializer(new File("./ProgramData/dealerships.csv"));
        ClientSerializer clientSaver = new ObjectClientSerializer(new File("./ProgramData/Binary_Clients.dat"));

        dealerSaver.saveDelearships(availableDealerships);
        clientSaver.saveClients(clients);
    }

    public void loadData(){
        CsvDealershipSerializer dealerLoader = new CsvDealershipSerializer(new File("./ProgramData/dealerships.csv"));
        CsvClientSerializer clientLoader = new CsvClientSerializer(new File("./ProgramData/clients.csv"));

        availableDealerships = (ArrayList<Dealership>) dealerLoader.loadDealerships();
        clients = (ArrayList<Client>) clientLoader.loadClients();

        //If it can't load for some reason wait for it to print the exception before continuing
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
        }

    }

    public void addDealership(Dealership newDealership){

        if (null == newDealership)
            throw new IllegalArgumentException("Null pointer");

        for(Dealership instance : availableDealerships){
            if (instance.equals(newDealership)){
                throw new IllegalArgumentException("Can't have same name and location dealershis");
            }
        }

        availableDealerships.add(newDealership);
    }

    public void removeDealership(Dealership toRemove){

        for (int i = 0; i < availableDealerships.size(); i++){

            if (availableDealerships.get(i).equals(toRemove)) {
                availableDealerships.remove(i);
                break;
            }
        }
    }

    /**
     * @return  Dealerships list with only the names and locations
     */
    public List<Dealership> getDealershipList(){

        List<Dealership> dealershipsNameList = new ArrayList<>();

        for (Dealership instance : availableDealerships){
            dealershipsNameList.add(instance.nameLocClone());
        }

        return dealershipsNameList;
    }

    public int getNumberOfDealerships(){

        return availableDealerships.size();
    }

    public Dealership getDealership(Dealership dealership){

        for (Dealership instance : availableDealerships){
            if (instance.equals(dealership)){
                return instance;
            }
        }
        return null;
    }

    public VehicleSorter retrieveDealershipVehicleSorter(Dealership dealership){

        if (null == dealership)
            throw new IllegalArgumentException("Can't have null dealership");

        for (Dealership instance : availableDealerships){

            if (instance.equals(dealership)){
                return instance.getVehicleSorter();
            }
        }

        throw new IllegalArgumentException("Could not find a dealership by that name");
    }

    public void addClient(Client toAdd) {

        if (null == toAdd)
            throw new IllegalArgumentException("Client is null");

        clients.add(toAdd);
    }

    public Client getClient(String firstName, String lastName){

        for (Client instance : clients){
            if (instance.getFirstName().equals(firstName))
                if (instance.getLastName().equals(lastName)){
                    return instance;
                }
        }

        return null;
    }

    public boolean isGreenBonusAvailable(Vehicle vehicle){

        if (vehicle.getProductionYear() == Calendar.getInstance().get(Calendar.YEAR)){
            if (GreenBonus.hasEnoughBudget()){
                return true;
            }
        }
        return false;
    }

    public boolean makeGreenBonusSell(Dealership dealership, Vehicle toSell, Client buyer){

        if (isGreenBonusAvailable(toSell)){
            return (makeSell(dealership, toSell, buyer));
        }

        return false;
    }

    public boolean makeSell(Dealership dealership, Vehicle toSell, Client buyer){

        Dealership actualDealership = getDealership(dealership);

        if ((null == actualDealership) || (toSell == null))
            throw new IllegalArgumentException("No null pointer allowed");

        if(actualDealership.getVehicleAvailability(toSell.hashCode()) > 0){
            Invoice invoice = new Invoice(buyer, toSell);

            actualDealership.decreaseStock(toSell.hashCode());
            actualDealership.addInvoice(invoice);
            GreenBonus.addCompletedInvoice(invoice);

            return (true);
        }

        return (false);
    }


}
