package ro.siit.java10.evp;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvDealershipSerializer implements DealershipSerializer {

    private static final String CSV_VEHICLE_PATTERN = "{0},{1},{2,number,0},{3,number,0},{4,number,0},{5,number,0}\n";
    private static final String CSV_MOTOR_PATTERN = "{0},{1},{2,number,0}\n";
    private static final String CSV_BATTERY_PATTERN = "{0},{1},{2,number,0}\n";

    File IOFile;


    public CsvDealershipSerializer(File IOFile) {
        this.IOFile = IOFile;
    }

    @Override
    public void saveDelearships(List<Dealership> deals) {

        try (BufferedWriter fwriter = new BufferedWriter(new FileWriter(IOFile))) {
            for (Dealership instance : deals) {
                saveDealership(instance, fwriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDealership(Dealership deal, Writer writer) throws IOException {

        saveDealershipData(new DealershipData(deal), writer);

        writer.write("{\n");
        ArrayList<Vehicle> dealershipVehicles = (ArrayList<Vehicle>) deal.getVehicleSorter().getAllVehicleList();
        for (Vehicle instance : dealershipVehicles) {
            saveVehicleData(new VehicleData(instance, deal.getVehiclePrice(instance.hashCode()),
                    deal.getVehicleAvailability(instance.hashCode())), writer);
        }
        writer.write("}\n");

    }

    private void saveDealershipData(DealershipData data, Writer fwriter) throws IOException {

        fwriter.write(data.name + "," + data.location + "\n");
    }

    private void saveVehicleData(VehicleData vehicleData, Writer writer) throws IOException {

        writer.write(MessageFormat.format(CSV_VEHICLE_PATTERN, vehicleData.model, vehicleData.fastCharging,
                vehicleData.productionYear, vehicleData.energyConsumption_KWperKm,
                vehicleData.price, vehicleData.stockAmount));

        writer.write(MessageFormat.format(CSV_MOTOR_PATTERN, vehicleData.motor.manufacturer,
                vehicleData.motor.model, vehicleData.motor.horsepower));

        writer.write( MessageFormat.format(CSV_BATTERY_PATTERN, vehicleData.battery.manufacturer,
                vehicleData.battery.model, vehicleData.battery.capacity));
    }

    @Override
    public List<Dealership> loadDealerships() {

        List<Dealership> dealList = new ArrayList<>();

        try (BufferedReader freader = new BufferedReader(new InputStreamReader(
                new FileInputStream(IOFile), "UTF-8"))){

            String line;

            while (null != (line = freader.readLine())) {
                String[] tokens = line.split(",");

                Dealership dealership = new Dealership(tokens[0], tokens[1]);
                readVehicles(dealership, freader);

                dealList.add(dealership);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return dealList;
    }

    private void readVehicles(Dealership deal, BufferedReader reader) throws IOException{

        reader.readLine(); //Gets rid of the {
        String line = reader.readLine();

        while (!line.equals("}")){
            String[] tokens = line.split(",");

            Vehicle vehicle = new Vehicle(tokens[0]);

            vehicle.setFastCharging(Boolean.parseBoolean(tokens[1]));
            vehicle.setProductionYear(Integer.parseInt(tokens[2]));
            vehicle.setEnergyConsumptionKWperKm(Integer.parseInt(tokens[3]));

            vehicle.setMotor(readMotor(reader));
            vehicle.setBattery(readBattery(reader));

            deal.addVehicle(vehicle, Float.parseFloat(tokens[4]));
            deal.setStockNumber(vehicle.hashCode(), Integer.parseInt(tokens[5]));

            line = reader.readLine();
        }
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
