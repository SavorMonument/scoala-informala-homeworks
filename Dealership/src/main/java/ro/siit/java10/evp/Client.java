package ro.siit.java10.evp;

import java.io.Serializable;
import java.util.Objects;

public class Client implements Serializable {

    private final String FIRST_NAME;
    private final String lAST_NAME;
    private String telephone;
    private String address;

    public Client(String firstName, String lastName) {
        this.lAST_NAME = lastName;
        this.FIRST_NAME = firstName;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getlAST_NAME() {
        return lAST_NAME;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(FIRST_NAME, client.FIRST_NAME) &&
                Objects.equals(lAST_NAME, client.lAST_NAME);
    }

    @Override
    public int hashCode() {

        return Objects.hash(FIRST_NAME, lAST_NAME);
    }
}
