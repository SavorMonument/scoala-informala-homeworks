
public class Stock {

    private Vehicle vehicle;
    private float price;
    private int stock;

    public Stock(Vehicle vehicle, float price, int stock) {
        this.vehicle = vehicle;
        this.price = price;
        this.stock = stock;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
}
