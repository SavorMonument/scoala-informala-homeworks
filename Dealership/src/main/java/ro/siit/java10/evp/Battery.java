package ro.siit.java10.evp;

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
}
