package ro.siit.java10.evp;

public class Client {

    private final String lastName;
    private final String firstName;
    private String telephone;
    private String address;

    public Client(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public Client(String lastName, String firstName, String telephone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.telephone = telephone;
        this.address = address;
    }
}
