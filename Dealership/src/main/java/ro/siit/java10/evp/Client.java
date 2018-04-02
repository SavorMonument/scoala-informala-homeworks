package ro.siit.java10.evp;

import java.util.ArrayList;

public class Client {

    private final String firstName;
    private final String lastName;
    private String telephone;
    private String address;

    public Client(String firstName, String lastName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }
}
