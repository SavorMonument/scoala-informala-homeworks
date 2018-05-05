package ro.siit.java10.evp.Serialization;

import ro.siit.java10.evp.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvDealershipLoader implements DealershipLoader {

    private BufferedReader reader;

    public CsvDealershipLoader(File file) throws FileNotFoundException, UnsupportedEncodingException {

        this.reader = getReader(file);
    }

    private BufferedReader getReader(File file) throws FileNotFoundException, UnsupportedEncodingException {

         return new BufferedReader(new InputStreamReader (new FileInputStream(file), "UTF-8"));
    }


    @Override
    public List<Dealership> loadDealerships() throws IOException {

        List<Dealership> dealerList = new ArrayList<>();

        String line;

        while (null != (line = reader.readLine())) {
            String[] tokens = line.split(",");

            Dealership dealership = new Dealership(tokens[0], tokens[1]);
            readVehicles(dealership);

            dealerList.add(dealership);
        }


        return dealerList;
    }

    private void readVehicles(Dealership deal) throws IOException{

        reader.readLine(); //Gets rid of the {
        String line = reader.readLine();

        while (!line.equals("}")){

            String[] tokens = line.split(",");

            VehicleData vehicleD = parseVehicleData(tokens);
            deal.addVehicle(vehicleD);

            line = reader.readLine();
        }
    }

    private VehicleData parseVehicleData(String[] tokens) throws IOException {

        VehicleData vehicleD = new VehicleData();

        vehicleD.model = tokens[0];
        vehicleD.fastCharging = Boolean.parseBoolean(tokens[1]);
        vehicleD.productionYear = Integer.parseInt(tokens[2]);
        vehicleD.energyConsumptionKWperKm = Integer.parseInt(tokens[3]);
        vehicleD.motor = readMotor(reader);
        vehicleD.battery = readBattery(reader);
        vehicleD.price = Float.parseFloat(tokens[4]);
        vehicleD.stock = Integer.parseInt(tokens[5]);

        return vehicleD;
    }

    private Motor readMotor(BufferedReader reader) throws IOException{

        String line = reader.readLine();

        String[] tokens = line.split(",");

        return new Motor(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
    }

    private Battery readBattery(BufferedReader reader) throws IOException{

        String line = reader.readLine();

        String[] tokens = line.split(",");

        return new Battery(tokens[0], tokens[1], Integer.parseInt(tokens[2]));
    }
}
