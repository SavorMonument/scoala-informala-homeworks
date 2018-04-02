package ro.siit.java10.evp;

import java.util.Objects;

public class Vehicle implements Cloneable{

    private String model = "No Model";
    private int productionYear;
    private int energyConsumption_KWperKm;
    private int RangePerCharge_Km;
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
        vehicleClone.setMotor(motor.clone());
        vehicleClone.setBattery(battery.clone());

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

    public int getEnergyConsumption_KWperKm() {
        return energyConsumption_KWperKm;
    }

    public int getRangePerCharge_Km() {
        return RangePerCharge_Km;
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

    public void setEnergyConsumption_KWperKm(int energyConsumption_KWperKm) {

        if (energyConsumption_KWperKm < 0)
            throw new IllegalArgumentException("Can't have negative energy consumption");

        this.energyConsumption_KWperKm = energyConsumption_KWperKm;

        calculateRangePerCharge();
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

        calculateRangePerCharge();
    }

    private void calculateRangePerCharge(){

        if ((battery.getCapacity_KWh() > 0) && (energyConsumption_KWperKm > 0))
            RangePerCharge_Km = battery.getCapacity_KWh() / energyConsumption_KWperKm;
    }

    @Override
    public String toString() {
        return  "model: " + model +
                ", productionYear: " + productionYear +
                ", energyConsumption_KW/Km: " + energyConsumption_KWperKm +
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
                energyConsumption_KWperKm == vehicle.energyConsumption_KWperKm &&
                RangePerCharge_Km == vehicle.RangePerCharge_Km &&
                fastCharging == vehicle.fastCharging &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(motor, vehicle.motor) &&
                Objects.equals(battery, vehicle.battery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(model, productionYear, energyConsumption_KWperKm, RangePerCharge_Km, fastCharging, motor, battery);
    }
}
