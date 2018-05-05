package ro.siit.java10.evp;

import java.io.Serializable;
import java.util.List;

public interface DealershipSerializer {

    public void saveDelearships(List<Dealership> deals);
    public List<Dealership> loadDealerships();

    class DealershipData implements Serializable {
        public String name;
        public String location;

        public DealershipData(Dealership deal) {
            this.name = deal.getName();
            this.location = deal.getLocation();
        }
    }

    class VehicleDataSer implements Serializable{

        public String model;
        public boolean fastCharging;
        public int productionYear;
        public int energyConsumption_KWperKm;
        public MotorData motor;
        public BatteryData battery;

        public float price;
        public int stockAmount;

        public VehicleDataSer(Vehicle vehicle, float price, int stockAmount){

            model = vehicle.getModel();
            productionYear = vehicle.getProductionYear();
            energyConsumption_KWperKm = vehicle.getEnergyConsumptionKWperKm();
            fastCharging = vehicle.hasFastCharging();
            motor = new MotorData(vehicle.getMotor());
            battery = new BatteryData(vehicle.getBattery());

            this.price = price;
            this.stockAmount = stockAmount;
        }

        class MotorData implements Serializable{

            public String manufacturer;
            public String model;
            public int horsepower;

            public MotorData(Motor motor) {
                this.manufacturer = motor.getManufacturer();
                this.model = motor.getModel();
                this.horsepower = motor.getHorsepower();
            }
        }

        class BatteryData implements Serializable{

            public String manufacturer;
            public String model;
            public int capacity;

            public BatteryData(Battery battery) {
                this.manufacturer = battery.getManufacturer();
                this.model = battery.getModel();
                this.capacity = battery.getCapacityKWh();
            }

        }


    }
}
