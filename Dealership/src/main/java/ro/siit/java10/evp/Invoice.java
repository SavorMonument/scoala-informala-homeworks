package ro.siit.java10.evp;

public class Invoice {

    private static int CURRENT_INVOICE_NUMBER;

    private int invoiceNumber;
    private Client client;
    private Vehicle vehicle;

    public Invoice(Client client, Vehicle vehicle) {

        invoiceNumber = CURRENT_INVOICE_NUMBER;
        this.client = client;
        this.vehicle = vehicle;

        CURRENT_INVOICE_NUMBER += 1;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public Client getClient() {
        return client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
