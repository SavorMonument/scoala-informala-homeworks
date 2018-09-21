package ro.siit.java10.evp.Serialization;

import ro.siit.java10.evp.Client;

import java.util.List;

public interface ClientSerializer {

    public void saveClients(List<Client> clients);
    public List<Client> loadClients();
}
