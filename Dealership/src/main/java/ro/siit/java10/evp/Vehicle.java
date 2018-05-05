package ro.siit.java10.evp;

import java.util.Objects;

public class Vehicle implements Cloneable{

    private String model = "No Model";
    private int productionYear;
    private int energyConsumptionKWperKm;
    private boolean fastCharging = false;
    private Motor motor = new Motor();
    private Battery battery = new Battery();

    public Vehicle() {
    }

    public Vehicle(String model) {

        this.model = model;
    }

    @Override
    protected Vehicle clone(){

        Vehicle vehicleClone = new Vehicle();

        vehicleClone.setModel(model);
        vehicleClone.setProductionYear(productionYear);
        vehicleClone.setFastCharging(fastCharging);
        vehicleClone.setEnergyConsumptionKWperKm(energyConsumptionKWperKm);
        vehicleClone.setMotor(new Motor(motor));
        vehicleClone.setBattery(new Battery(battery));

        return (vehicleClone);

    }

    public boolean hasFastCharging() {
        return fastCharging;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public int getEnergyConsumptionKWperKm() {
        return energyConsumptionKWperKm;
    }

    public Motor getMotor() {
        return motor;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setFastCharging(boolean fastCharging) {
        this.fastCharging = fastCharging;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setEnergyConsumptionKWperKm(int energyConsumptionKWperKm) {

        if (energyConsumptionKWperKm < 0)
            throw new IllegalArgumentException("Can't have negative energy consumption");

        this.energyConsumptionKWperKm = energyConsumptionKWperKm;
    }

    public void setProductionYear(int productionYear) {

        if (productionYear < 0)
            throw new IllegalArgumentException("Can't have a negative year");

        this.productionYear = productionYear;
    }

    public void setMotor(Motor newMotor){

        if (null == newMotor)
            throw new IllegalArgumentException("Invalid motor");

        motor = newMotor;
    }

    public void setBattery(Battery newBattery){

        if (null == newBattery)
            throw new IllegalArgumentException("Invalid battery");

        battery = newBattery;
    }

    public int getRangePerChargeInKm(){

        if ((battery.getCapacityKWh() > 0) && (energyConsumptionKWperKm > 0))
            return battery.getCapacityKWh() / energyConsumptionKWperKm;

        return 0;
    }

    @Override
    public String toString() {
        return  "model: " + model +
                ", productionYear: " + productionYear +
                ", energyConsumption_KW/Km: " + energyConsumptionKWperKm +
                " fastCharging: " + fastCharging +
                ", motor: " + motor +
                ", battery: " + battery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return  productionYear == vehicle.productionYear &&
                energyConsumptionKWperKm == vehicle.energyConsumptionKWperKm &&
                fastCharging == vehicle.fastCharging &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(motor, vehicle.motor) &&
                Objects.equals(battery, vehicle.battery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(model, productionYear, energyConsumptionKWperKm,
                fastCharging, motor, battery);
    }
}
