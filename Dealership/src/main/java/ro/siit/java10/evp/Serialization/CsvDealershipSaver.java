package ro.siit.java10.evp.Serialization;

import ro.siit.java10.evp.*;

import java.io.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CsvDealershipSaver implements DealershipSaver {

    private static final String CSV_VEHICLE_PATTERN = "{0},{1},{2,number,0},{3,number,0},{4,number,0},{5,number,0}\n";
    private static final String CSV_MOTOR_PATTERN = "{0},{1},{2,number,0}\n";
    private static final String CSV_BATTERY_PATTERN = "{0},{1},{2,number,0}\n";

    File IOFile;

    public CsvDealershipSaver(File IOFile) {

        this.IOFile = IOFile;
    }

    @Override
    public void saveDealerships(List<Dealership> deals) {

        try (BufferedWriter fwriter = new BufferedWriter(new FileWriter(IOFile))) {
            for (Dealership instance : deals) {
                saveOneDealership(instance, fwriter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveOneDealership(Dealership deal, Writer writer) throws IOException {

        saveDealershipData(deal, writer);

        writer.write("{\n");

        VehicleSorter vSorter = deal.getVehicleSorter();
        List<VehicleData> dealershipVehicles = vSorter.getVehicleList(VehicleSorter.SortingOptions.NORMAL);

        for (VehicleData instance : dealershipVehicles) {
            saveOneVehicleData(instance, writer);
        }

        writer.write("}\n");

    }

    private void saveDealershipData(Dealership deal, Writer fWriter) throws IOException {

        fWriter.write(String.format("%s,%s\n", deal.getName(), deal.getLocation()));
    }

    private void saveOneVehicleData(VehicleData vehicleD, Writer writer) throws IOException {

        writer.write(MessageFormat.format(CSV_VEHICLE_PATTERN, vehicleD.model, vehicleD.fastCharging,
                vehicleD.productionYear, vehicleD.energyConsumptionKWperKm,
                vehicleD.price, vehicleD.stock));

        writer.write(MessageFormat.format(CSV_MOTOR_PATTERN, vehicleD.motor.getManufacturer(),
                vehicleD.motor.getModel(), vehicleD.motor.getHorsepower()));

        writer.write( MessageFormat.format(CSV_BATTERY_PATTERN, vehicleD.battery.getManufacturer(),
                vehicleD.battery.getModel(), vehicleD.battery.getCapacityKWh()));
    }

}
