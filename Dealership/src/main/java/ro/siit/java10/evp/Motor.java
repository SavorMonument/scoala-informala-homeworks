package ro.siit.java10.evp;

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
}
