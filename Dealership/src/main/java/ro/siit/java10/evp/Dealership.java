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

        Vehicle vehicle = vehicleD.getVehicle();

        for (Stock instance : stock){
            if (instance.getVehicle().equals(vehicle) && (instance.getPrice() == vehicleD.price)){

                instance.setAmount(instance.getAmount() + vehicleD.stock);
                return;
            }
        }

        Stock moreStock = new Stock(vehicle, vehicleD.price);
        moreStock.setAmount(vehicleD.stock);

        stock.add(moreStock);
    }

    public void decreaseStock(int hash){

        for (Stock instance : stock){
            if (instance.getVehicle().hashCode() == hash){

                instance.decreaseAmount();
                return;
            }
        }
    }

    public void removeVehicle(int hash){

        for(int i = 0; i < stock.size(); i++){

            if (stock.get(i).getVehicle().hashCode() == hash){
                stock.remove(i);
                return;
            }
        }
    }

    public void setStockNumber(int hash, int stockAmount){

        for (Stock instance : stock){
            if (instance.getVehicle().hashCode() == hash){

                instance.setAmount(stockAmount);
                return;
            }
        }
    }

    public float getVehiclePrice(int hash){

        for (Stock instance : stock) {

            if (instance.getVehicle().hashCode() == hash){
                return instance.getPrice();
            }
        }

        throw new InvalidParameterException("no vehicle with this ID");
    }

    public int getVehicleStockNumber(int hash){

        for (Stock instance : stock){

            if (instance.getVehicle().hashCode() == hash)
                return instance.getAmount();
        }

        return 0;
    }

    public VehicleSorter getVehicleSorter(){

        List<VehicleData> vehicleDataList = new ArrayList<>();

        for (Stock instance : stock){
            vehicleDataList.add(new VehicleData(instance.getVehicle(), instance.getPrice(),
                    instance.getAmount()));
        }


        return (new VehicleSorter(vehicleDataList));
    }

    public void addInvoice(Invoice toAdd){

        if (toAdd == null)
            throw new IllegalArgumentException("No null invoices");

        invoices.add(toAdd);
    }

    public boolean isGreenBonusAvailable(int hash){

        if (getVehicleStockNumber(hash) > 0) {

            if (getVehicle(hash).getProductionYear() == Calendar.getInstance().get(Calendar.YEAR)) {

                return GreenBonus.hasEnoughBudget();
            }
        }
        return false;
    }

    public boolean makeGreenBonusSell(int hash, Client buyer){

        if (isGreenBonusAvailable(hash))
            return (makeSell(hash, buyer));

        return false;
    }

    public boolean makeSell(int hash, Client buyer){
        assert  (null != buyer);

        if (buyer.getCredit() < getVehiclePrice(hash))
            return false;

        Vehicle vehicle;

        try {
            vehicle = getVehicle(hash);
        } catch (IllegalArgumentException e){
            return false;
        }

        buyer.substractCredit(getVehiclePrice(hash));
        Invoice invoice = new Invoice(buyer, vehicle);
        addInvoice(invoice);
        decreaseStock(hash);
        GreenBonus.subtractMoneyFromBudget();
        GreenBonus.addCompletedInvoice(invoice);

        return (true);

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
