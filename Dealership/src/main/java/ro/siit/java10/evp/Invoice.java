package ro.siit.java10.evp;

public class Invoice {

    private Client client;
    private Vehicle vehicle;
    private float total;

    public Invoice(Client client, Vehicle vehicle, float total) {

        this.client = client;
        this.vehicle = vehicle;
        this.total = total;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public float getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return String.format("Invoice: \nClient: %s\nVehicle: %s\nTotal: %.2f",
                client, vehicle.getModel(), total);
    }
}
