package ro.siit.java10.evp;

import java.security.InvalidParameterException;
import java.util.Objects;

public class Battery implements Cloneable {

    private String manufacturer = "No manufacturer";
    private String model = "No model";
    private int capacity_KWh;

    public  Battery(){ }

    public Battery(String manufacturer, String model, int capacity) {

        this.manufacturer = manufacturer;
        this.model = model;

        setCapacity_KWh(capacity);
    }

    private void setCapacity_KWh(int capacity_KWh) {

        if (capacity_KWh < 0)
            throw new InvalidParameterException("Can't have negative capacity_KWh");

        this.capacity_KWh = capacity_KWh;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getCapacity_KWh() {
        return capacity_KWh;
    }

    @Override
    public Battery clone(){

        return new Battery(manufacturer, model, capacity_KWh);
    }

    @Override
    public String toString() {
        return  "manufacturer: " + manufacturer +
                ", model: " + model +
                ", capacity(KWh): " + capacity_KWh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return capacity_KWh == battery.capacity_KWh &&
                Objects.equals(manufacturer, battery.manufacturer) &&
                Objects.equals(model, battery.model);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, model, capacity_KWh);
    }
}
