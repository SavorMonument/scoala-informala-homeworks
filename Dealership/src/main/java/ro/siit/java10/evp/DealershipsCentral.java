package ro.siit.java10.evp;

import java.util.*;

public class DealershipsCentral{

    private ArrayList<Dealership> availableDealerships = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    public DealershipsCentral() {
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

    public VehicleSorter getDealershipVehicleSorter(Dealership dealership){

        for (Dealership instance : availableDealerships){

            if (instance.equals(dealership)){
                return instance.getVehicleSorter();
            }
        }

        return null;
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

    public Boolean isGreenBonusAvailable(Vehicle vehicle){

        if (vehicle.getProductionYear() == Calendar.getInstance().get(Calendar.YEAR)){
            if (GreenBonus.hasEnoughBudget()){
                return true;
            }
        }
        return false;
    }

    public Boolean makeGreenBonusSell(Dealership dealership, Vehicle toSell, Client buyer){

        if (isGreenBonusAvailable(toSell)){
            return (makeSell(dealership, toSell, buyer));
        }

        return false;
    }

    public Boolean makeSell(Dealership dealership, Vehicle toSell, Client buyer){

        Dealership actualDealership = getDealership(dealership);

        if ((null == actualDealership) || (toSell == null))
            throw new IllegalArgumentException("No null pointer allowed");

        if(actualDealership.getVehicleAvailability(toSell.hashCode()) > 0){
            Invoice invoice = new Invoice(buyer, toSell);

            actualDealership.decreaseStock(toSell.hashCode());
            actualDealership.addInvoice(invoice);

            return (true);
        }

        return (false);
    }


}
