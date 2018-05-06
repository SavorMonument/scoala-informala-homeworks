package ro.siit.java10.evp;

import java.security.InvalidParameterException;
import java.util.*;

public class Dealership {

    private String name;
    private String location;
    private ArrayList<Stock> stock = new ArrayList<Stock>();
    private ArrayList<Invoice> invoices = new ArrayList<Invoice>();

    public Dealership(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {

        return name;
    }

    public String getLocation() {

        return location;
    }



    public void addVehicle(VehicleData vehicleD){
        assert (null != vehicleD);

        if (!isValidVehicleData(vehicleD))
            throw new IllegalArgumentException("Invalid vehicle: " + vehicleD.stringRepresentation());

        Vehicle vehicle = vehicleD.buildVehicle();

        int pos;
        if ((pos = getStockPosition(vehicle)) >= 0){

            if (stock.get(pos).getPrice() == vehicleD.price){
                stock.get(pos).addAmount(vehicleD.stock);
            }
        } else {

            Stock nextStock = new Stock(vehicle, vehicleD.price);
            nextStock.setAmount(vehicleD.stock);
            stock.add(nextStock);
        }
    }

    private boolean isValidVehicleData(VehicleData vehicleD){

        return (vehicleD.price >= 0) && (vehicleD.stock >= 0)
                && (null != vehicleD.motor) && (null != vehicleD.battery)
                && (vehicleD.productionYear > 0);
    }

    private int getStockPosition(Vehicle vehicle){

        for (int i = 0; i < stock.size(); i++) {

            if (stock.get(i).getVehicle().equals(vehicle))
                return i;
        }

        return -1;
    }

    public void setVehicleStock(int hash, int amount){

        int pos = getVehicleLocationInStock(hash);

        stock.get(pos).setAmount(amount);
    }

    public void removeVehicle(int hash){

        stock.remove(getVehicleLocationInStock(hash));
    }

    public int getAmountOfVehiclesInStock(int hash){

        int pos;

        try{
            pos = getVehicleLocationInStock(hash);
        }catch (IllegalArgumentException e){
            return 0;
        }

        return stock.get(pos).getAmount();
    }

    private int getVehicleLocationInStock(int hash){

        for(int i = 0; i < stock.size(); i++){

            if (stock.get(i).getVehicle().hashCode() == hash){
                return i;
            }
        }

        throw new IllegalArgumentException("No vehicle at that hash");
    }

    public VehicleSorter getVehicleSorter(){

        List<VehicleData> vehicleDataList = new ArrayList<>();

        for (Stock instance : stock){
            vehicleDataList.add(new VehicleData(
                    instance.getVehicle(), instance.getPrice(), instance.getAmount()));
        }


        return (new VehicleSorter(vehicleDataList));
    }

    public List<Invoice> getInvoiceList(Client client){

        List<Invoice> clientInvoices = new ArrayList<>();

        for(Invoice inv: invoices){

            if (inv.getClient().equals(client))
                clientInvoices.add(inv);
        }

        return clientInvoices;
    }



    public boolean isGreenBonusAvailable(int hash){

        if (getVehicle(hash).getProductionYear() == Calendar.getInstance().get(Calendar.YEAR))
            return GreenBonus.hasEnoughBudget();

        return false;
    }

    public boolean makeGreenBonusSell(int hash, Client buyer){

        if (isGreenBonusAvailable(hash)) {
            return (makeSell(hash, buyer));
        }

        return false;
    }

    public boolean makeSell(int hash, Client buyer){
        assert  (null != buyer);

        if (buyer.getCredit() < getVehiclePrice(hash))
            return false;

        Stock vehicleStock = stock.get(getVehicleLocationInStock(hash));

        buyer.subtractCredit(getVehiclePrice(hash));
        Invoice invoice = new Invoice(buyer, vehicleStock.getVehicle(), vehicleStock.getPrice());
        addInvoice(invoice);
        vehicleStock.decreaseAmount();
        GreenBonus.subtractMoneyFromBudget();
        GreenBonus.addCompletedInvoice(invoice);

        return (true);

    }

    private void addInvoice(Invoice toAdd){

        if (toAdd == null)
            throw new IllegalArgumentException("No null invoices");

        invoices.add(toAdd);
    }

    private float getVehiclePrice(int hash){

        for (Stock instance : stock) {

            if (instance.getVehicle().hashCode() == hash){
                return instance.getPrice();
            }
        }

        throw new InvalidParameterException("no vehicle with this HASH");
    }

    private Vehicle getVehicle(int hash){

        for(Stock s: stock){
            if (s.getVehicle().hashCode() == hash)
                return s.getVehicle();
        }
        throw new IllegalArgumentException("No such vehicle");
    }

    @Override
    public String toString() {
        return  "name: " + name +
                " location: " + location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealership that = (Dealership) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, location);
    }
}
