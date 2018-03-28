package ro.siit.java10.evp;

public class Invoice {

    private int invoiceNumber;
    private Client client;
    private String dealership;
    private Vehicle vehicle;

    public Invoice(int invoiceNumber,Client client, String dealership, Vehicle vehicle) {

        this.invoiceNumber = invoiceNumber;
        this.client = client;
        this.dealership = dealership;
        this.vehicle = vehicle;
    }
}
