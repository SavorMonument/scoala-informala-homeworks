package ro.siit.java10.evp;

public class VehicleData {

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

    public VehicleData(Vehicle vehicle, float price, int stock){

        ID = vehicle.hashCode();
        model = vehicle.getModel();
        productionYear = vehicle.getProductionYear();
        energyConsumptionKWperKm = vehicle.getEnergyConsumptionKWperKm();
        rangePerCharge_Km = vehicle.getRangePerChargeInKm();
        fastCharging = vehicle.hasFastCharging();

        motor = new Motor(vehicle.getMotor());
        battery = new Battery(vehicle.getBattery());

        this.price = price;
        this.stock = stock;
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

    public String stringRepresentation(Options... options){

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
