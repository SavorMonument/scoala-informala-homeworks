package ro.siit.java10.evp.Serialization;


import ro.siit.java10.evp.Client;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectClientSerializer implements ClientSerializer {

    private static final Client CLIENT_END_MARKER = new Client("########", "########");
    private File IOFile;

    public ObjectClientSerializer(File IOFile) {
        this.IOFile = IOFile;
    }

    @Override
    public void saveClients(List<Client> clients) {

        try(ObjectOutputStream Oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(IOFile)))) {

            for (Client instance : clients){
                Oout.writeObject(clients);
            }

            Oout.writeObject(CLIENT_END_MARKER);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> loadClients() {
        List<Client> clients = new ArrayList<>();

        try(ObjectInputStream Oinput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(IOFile)))){

            Client readClient;
            while (CLIENT_END_MARKER != (readClient = (Client) Oinput.readObject())){
                clients.add(readClient);
            }

        } catch (IOException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return clients;
    }
}
