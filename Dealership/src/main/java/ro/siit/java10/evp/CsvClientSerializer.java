package ro.siit.java10.evp;

import java.io.*;
import java.nio.Buffer;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvClientSerializer implements ClientSerializer {

    private static final String CSV_Client_PATTERN = "{0},{1},{2},{3}\n";

    File IOFile;

    public CsvClientSerializer(File IOFile) {
        this.IOFile = IOFile;
    }

    @Override
    public void saveClients(List<Client> clients) {

        try (BufferedWriter Bwriter = new BufferedWriter(new FileWriter(IOFile))) {

            for (Client instance : clients) {
                saveClient(Bwriter, instance);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void saveClient(BufferedWriter writer, Client client) throws IOException {

        writer.write(MessageFormat.format(CSV_Client_PATTERN, client.getFIRST_NAME(), client.getlAST_NAME(),
                client.getTelephone(), client.getAddress()));
    }

    @Override
    public List<Client> loadClients() {

        List<Client> clients = new ArrayList<>();

        try(BufferedReader Breader = new BufferedReader(new InputStreamReader(new FileInputStream(IOFile), "UTF-8"))){

            Client client;
            while (null != (client = readClient(Breader))){
                clients.add(client);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return clients;
    }

    private Client readClient(BufferedReader Breader) throws IOException{

        String line = Breader.readLine();

        if (line == null)
            return null;

        String tokens[] = line.split(",");

        Client client = new Client(tokens[0], tokens[1]);
        client.setTelephone(tokens[2]);
        client.setAddress(tokens[3]);

        return client;
    }
}
