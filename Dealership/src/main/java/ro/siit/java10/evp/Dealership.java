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

    public void addVehicle(Vehicle vehicle, float price){

        for (Stock instance : stock){
            if (instance.getVehicle().equals(vehicle) && (instance.getPrice() == price)){

                instance.setAmount(instance.getAmount() + 1);
                return;
            }
        }

        stock.add(new Stock(vehicle, price));
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
            vehicleDataList.add(new VehicleData(instance.getVehicle()));
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

    public class VehicleData{

        public int ID;
        public String model;
        public int productionYear;
        public int energyConsumptionKWperKm;
        public int rangePerCharge_Km;
        public boolean fastCharging;
        public Motor motor;
        public Battery battery;

        public float price;
        public int stock;

        public VehicleData(Vehicle vehicle){

            ID = vehicle.hashCode();
            model = vehicle.getModel();
            productionYear = vehicle.getProductionYear();
            energyConsumptionKWperKm = vehicle.getEnergyConsumptionKWperKm();
            rangePerCharge_Km = vehicle.getRangePerCharge_Km();
            fastCharging = vehicle.hasFastCharging();

            motor = new Motor(vehicle.getMotor());
            battery = new Battery(vehicle.getBattery());

            price = getVehiclePrice(ID);
            stock = getVehicleStockNumber(ID);
        }

        public String toString(Options... options){

            StringBuilder representation = new StringBuilder();

            representation.append(String.format("Model: %s     ", model));

            for(Options opt: options) {

                switch (opt) {
                    case YEAR:
                        representation.append(String.format("Production Year: %d     ", productionYear));
                        break;
                    case E_CONSUMPTION:
                        representation.append(String.format("Energy Consumption(kw/km): %d     ", energyConsumptionKWperKm));
                        break;
                    case RANGE_PER_CHARGE:
                        representation.append(String.format("Range per full charge(km): %d     ", rangePerCharge_Km));
                        break;
                    case FAST_CHARGING:
                        if (fastCharging)
                            representation.append("Has fast charging   ");
                        else
                            representation.append("It does not have fast charging   ");
                        break;
                    case MOTOR:
                        representation.append(String.format("\nMotor: %s\n", motor.toString()));
                        break;
                    case BATTERY:
                        representation.append(String.format("\nBattery: %s\n", battery.toString()));
                        break;
                    case PRICE:
                        representation.append(String.format("\nPrice: %s\n", price));
                        break;
                    case STOCK:
                        representation.append(String.format("\nAvailable: %s\n", stock));
                        break;
                }
            }

            return representation.toString();
        }
    }
}
