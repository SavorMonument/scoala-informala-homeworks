package ro.siit.java10.evp;

import java.util.Objects;

public class Vehicle {

    private String manufacturer;
    private String model;
    private int productionYear;
    private int energyConsumption;
    private boolean fastCharging;
    private Motor motor;
    private Battery battery;

    public Vehicle(String manufacturer, String model, int productionYear,boolean fastCharging, int energyConsumption, Motor motor, Battery battery) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.productionYear = productionYear;
        this.energyConsumption = energyConsumption;
        this.fastCharging = fastCharging;
        this.motor = motor;
        this.battery = battery;
    }

    public Vehicle(String manufacturer, String model, int productionYear) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.productionYear = productionYear;

        energyConsumption = 0;
        motor = new Motor();
        battery = new Battery();
    }

    public boolean hasFastCharging() {
        return fastCharging;
    }

    public void setFastCharging(boolean fastCharging) {
        this.fastCharging = fastCharging;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public int getEnergyConsumption() {
        return energyConsumption;
    }

    public Motor getMotor() {
        return motor;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setMotor(Motor newMotor){

        motor = newMotor;
    }

    public void setBattery(Battery newBattery){

        battery = newBattery;
    }

    public void setEnergyConsumption(int energyConsumption) {

        this.energyConsumption = energyConsumption;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", productionYear=" + productionYear +
                ", energyConsumption=" + energyConsumption +
                ", fastCharging=" + fastCharging +
                ", motor=" + motor +
                ", battery=" + battery +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return productionYear == vehicle.productionYear &&
                energyConsumption == vehicle.energyConsumption &&
                fastCharging == vehicle.fastCharging &&
                Objects.equals(manufacturer, vehicle.manufacturer) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(motor, vehicle.motor) &&
                Objects.equals(battery, vehicle.battery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, model, productionYear, energyConsumption, fastCharging);
    }
}
