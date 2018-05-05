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

    public void removeDealership(Dealership Dealership){

        for (int i = 0; i < availableDealerships.size(); i++){

            if (availableDealerships.get(i).equals(Dealership)) {
                availableDealerships.remove(i);
                break;
            }
        }
    }

    public List<Dealership> getDealershipList(){

        return availableDealerships;
    }

    public int getNumberOfDealerships(){

        return availableDealerships.size();
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

}
