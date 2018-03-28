package ro.siit.java10.evp;

import java.util.Objects;

public class Vehicle {

    private String manufacturer;
    private String model;
    private int production_year;
    private int energy_consumption;
    private boolean fast_charging;
    private Motor motor;
    private Battery battery;

    public Vehicle(String manufacturer, String model, int production_year,boolean fast_charging, int energy_consumption, Motor motor, Battery battery) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.production_year = production_year;
        this.energy_consumption = energy_consumption;
        this.fast_charging = fast_charging;
        this.motor = motor;
        this.battery = battery;
    }

    public Vehicle(String manufacturer, String model, int production_year) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.production_year = production_year;

        energy_consumption = 0;
        motor = new Motor();
        battery = new Battery();
    }

    public boolean has_fast_charging() {
        return fast_charging;
    }

    public void setFast_charging(boolean fast_charging) {
        this.fast_charging = fast_charging;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getProduction_year() {
        return production_year;
    }

    public int getEnergy_consumption() {
        return energy_consumption;
    }

    public Motor getMotor() {
        return motor;
    }

    public Battery getBattery() {
        return battery;
    }

    public void setMotor(Motor new_motor){

        motor = new_motor;
    }

    public void setBattery(Battery new_battery){

        battery = new_battery;
    }

    public void setEnergy_consumption(int energy_consumption) {

        this.energy_consumption = energy_consumption;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", production_year=" + production_year +
                ", energy_consumption=" + energy_consumption +
                ", fast_charging=" + fast_charging +
                ", motor=" + motor +
                ", battery=" + battery +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return production_year == vehicle.production_year &&
                energy_consumption == vehicle.energy_consumption &&
                fast_charging == vehicle.fast_charging &&
                Objects.equals(manufacturer, vehicle.manufacturer) &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(motor, vehicle.motor) &&
                Objects.equals(battery, vehicle.battery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, model, production_year, energy_consumption, fast_charging);
    }
}
