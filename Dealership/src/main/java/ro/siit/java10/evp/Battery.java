package ro.siit.java10.evp;

import java.util.Objects;

public class Battery {

    public String manufacturer = "";
    public String model = "";
    public int capacity = 0;

    public  Battery(){ }

    public Battery(String manufacturer, String model, int capacity) {

        this.manufacturer = manufacturer;
        this.model = model;
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return capacity == battery.capacity &&
                Objects.equals(manufacturer, battery.manufacturer) &&
                Objects.equals(model, battery.model);
    }

    @Override
    public int hashCode() {

        return Objects.hash(manufacturer, model, capacity);
    }
}
