package ro.siit.java10.evp;

import java.util.*;

public class DealershipsCentral{

    private ArrayList<Dealership> availableDealerships = new ArrayList<>();
    private ArrayList<Client> clients = new ArrayList<>();

    public DealershipsCentral() {
    }

    public void addDealeship(Dealership newDealership){

        if (null == newDealership)
            throw new IllegalArgumentException("Invalid dealership");

        for(Dealership instance : availableDealerships){
            if (instance.equals(newDealership)){
                return;
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
            dealershipsNameList.add(instance.nameAndLocClone());
        }

        return dealershipsNameList;
    }

    public int getNumberOfDealerships(){

        return availableDealerships.size();
    }

    /**
     * Only the name and location of the param has to match and returns a
     * reference to a dealership from the List in this object
     *
     * @param dealer
     * @return
     */
    public Dealership getDealership(Dealership dealer){

        for (Dealership instance : availableDealerships){
            if (instance.equals(dealer)){
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

    public Client getClient(String firstname, String lastName){

        for (Client instance : clients){
            if (instance.getFirstName().equals(firstname))
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

        if ((null == dealership) || (toSell == null))
            throw new IllegalArgumentException("No null pointer allowed");

        if(dealership.getVehicleAvailability(toSell.hashCode()) > 0){
            Invoice invoice = new Invoice(buyer, toSell);

            dealership.decreaseStock(toSell.hashCode());
            dealership.addInvoice(invoice);

            return (true);
        }

        return (false);
    }


}
