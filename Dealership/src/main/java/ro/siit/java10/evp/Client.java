package ro.siit.java10.evp;

public class Client {

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
}
