
public class Invoice {

    private int invoice_number;
    private Client client;
    private String dealership;
    private Vehicle vehicle;

    public Invoice(int invoice_number,Client client, String dealership, Vehicle vehicle) {

        this.invoice_number = invoice_number;
        this.client = client;
        this.dealership = dealership;
        this.vehicle = vehicle;
    }
}
