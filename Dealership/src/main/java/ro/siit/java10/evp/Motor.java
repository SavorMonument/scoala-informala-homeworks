package ro.siit.java10.evp;

import java.util.Objects;

public class Motor {

    public String manufacturer = "";
    public String model = "";
    public int power = 0;

    public Motor(){ }

    public Motor(String manufacturer, String model, int power) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.power = power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motor motor = (Motor) o;
        return power == motor.power &&
                Objects.equals(manufacturer, motor.manufacturer) &&
                Objects.equals(model, motor.model);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, model, power);
    }
}
