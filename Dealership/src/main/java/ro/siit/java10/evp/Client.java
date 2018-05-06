package ro.siit.java10.evp;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {

    private final String firstName;
    private final String lastName;
    private float credit = 1000000.0f;
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

    public float getCredit() {
        return credit;
    }

    public void setCredit(float credit) {
        assert (credit >= 0);

        this.credit = credit;
    }

    public void subtractCredit(float credit) {


        this.credit -= credit;
    }

    @Override
    public String toString() {

        return String.format("Client\nFirstName: %s\nLastName: %s", firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;

        return (firstName.compareToIgnoreCase(client.firstName) == 0) &&
                (lastName.compareToIgnoreCase(client.lastName) == 0);
    }

    @Override
    public int hashCode() {

        return Objects.hash(firstName, lastName);
    }
}
