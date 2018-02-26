package ro.siit.java10.evp;

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

    public boolean is_same_vehicle(Vehicle contestant){

        return ((manufacturer.matches(contestant.manufacturer)) &&
                (model.matches(contestant.model)) &&
                (production_year == contestant.production_year) &&
                (energy_consumption == contestant.energy_consumption) &&
                (fast_charging == contestant.fast_charging));
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
}
