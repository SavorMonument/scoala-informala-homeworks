
public class Vehicle {


    private String manufacturer;
    private String model;
    private int production_year;
    private int energy_consumption;
    private Motor motor;
    private Battery battery;

    public Vehicle(String manufacturer, String model, int production_year, int energy_consumption, Motor motor, Battery battery) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.production_year = production_year;
        this.energy_consumption = energy_consumption;
        this.motor = motor;
        this.battery = battery;
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

    public void changeMotor(Motor new_motor){

        motor = new_motor;
    }


    public void changeBattery(Battery new_battery){

        battery = new_battery;
    }
}
