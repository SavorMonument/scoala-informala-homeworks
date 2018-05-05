package ro.siit.java10.evp;

import java.security.InvalidParameterException;
import java.util.Objects;

public class Motor {

    private String manufacturer = "No manufacturer";
    private String model = "No model";
    private int horsepower;

    public Motor(){ }

    public Motor(String manufacturer, String model, int horsepower) {

        this.manufacturer = manufacturer;
        this.model = model;

        setHorsepower(horsepower);
    }

    public Motor(Motor motor){

        this.manufacturer = motor.manufacturer;
        this.model = motor.model;
        this.horsepower = motor.horsepower;
    }

    private void setHorsepower(int horsepower) {

        if (horsepower < 0)
            throw new InvalidParameterException("Can't have negative horse power");

        this.horsepower = horsepower;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    @Override
    public String toString() {
        return  "manufacturer: " + manufacturer +
                ", model: " + model +
                ", horsePower: " + horsepower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motor motor = (Motor) o;
        return horsepower == motor.horsepower &&
                Objects.equals(manufacturer, motor.manufacturer) &&
                Objects.equals(model, motor.model);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, model, horsepower);
    }
}
