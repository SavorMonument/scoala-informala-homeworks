package ro.siit.java10.evp;

public class Invoice {

    private int invoiceNumber;
    private Client client;
    private String dealership;
    private Vehicle vehicle;

    public Invoice(int invoice_number,Client client, String dealership, Vehicle vehicle) {

        this.invoiceNumber = invoice_number;
        this.client = client;
        this.dealership = dealership;
        this.vehicle = vehicle;
    }
}
