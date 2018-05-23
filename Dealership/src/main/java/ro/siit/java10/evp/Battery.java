package ro.siit.java10.evp;

import java.security.InvalidParameterException;
import java.util.Objects;

public class Battery implements Cloneable {

    private String manufacturer = "No manufacturer";
    private String model = "No model";
    private int capacityKWh;

    public  Battery(){ }

    public Battery(String manufacturer, String model, int capacity) {

        this.manufacturer = manufacturer;
        this.model = model;

        setCapacityKWh(capacity);
    }

    private void setCapacityKWh(int capacityKWh) {

        if (capacityKWh < 0)
            throw new InvalidParameterException("Can't have negative capacityKWh");

        this.capacityKWh = capacityKWh;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getCapacityKWh() {
        return capacityKWh;
    }

    @Override
    public Battery clone(){

        return new Battery(manufacturer, model, capacityKWh);
    }

    @Override
    public String toString() {
        return  "manufacturer: " + manufacturer +
                ", model: " + model +
                ", capacity(KWh): " + capacityKWh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return capacityKWh == battery.capacityKWh &&
                Objects.equals(manufacturer, battery.manufacturer) &&
                Objects.equals(model, battery.model);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, model, capacityKWh);
    }
}
