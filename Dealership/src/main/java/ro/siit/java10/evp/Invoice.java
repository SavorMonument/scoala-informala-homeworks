package ro.siit.java10.evp;

public class Invoice {

    private int currentInvoiceNumber;
    private int invoiceNumber;
    private Client client;
    private Vehicle vehicle;

    public Invoice(Client client, Vehicle vehicle) {

        invoiceNumber = currentInvoiceNumber;
        this.client = client;
        this.vehicle = vehicle;

        currentInvoiceNumber += 1;
    }
}
