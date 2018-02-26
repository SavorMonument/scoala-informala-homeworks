package ro.siit.java10.evp;


public class Stock {

    private Vehicle vehicle;
    private float price;

    public Stock(Vehicle vehicle, float price) {
        this.vehicle = vehicle;
        this.price = price;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
