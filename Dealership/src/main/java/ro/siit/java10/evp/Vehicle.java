package ro.siit.java10.evp;

import java.util.Objects;

public class Vehicle implements Cloneable{

    private String model;
    private int productionYear;
    private int energyConsumptionKWperKm;
    private boolean fastCharging = false;
    private Motor motor = new Motor();
    private Battery battery = new Battery();

    private Vehicle(String model) {

        this.model = model;
    }

    public Vehicle(VehicleBuilder builder){

        this.model = builder.model;
        this.productionYear = builder.productionYear;
        this.energyConsumptionKWperKm = builder.energyConsumptionKWperKm;
        this.fastCharging = builder.fastCharging;
        this.motor = builder.motor;
        this.battery = builder.battery;
    }

    public Vehicle(Vehicle vehicle){

        this.model = vehicle.model;
        this.productionYear = vehicle.productionYear;
        this.energyConsumptionKWperKm = vehicle.energyConsumptionKWperKm;
        this.fastCharging = vehicle.fastCharging;
        this.motor = vehicle.motor;
        this.battery = vehicle.battery;
    }

    public boolean hasFastCharging() {
        return fastCharging;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public int getEnergyConsumptionKWperKm() {
        return energyConsumptionKWperKm;
    }

    public Motor getMotor() {
        return motor;
    }

    public Battery getBattery() {
        return battery;
    }

    public int getRangePerChargeInKm(){

        if ((battery.getCapacityKWh() > 0) && (energyConsumptionKWperKm > 0))
            return battery.getCapacityKWh() / energyConsumptionKWperKm;

        return 0;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return productionYear == vehicle.productionYear &&
                energyConsumptionKWperKm == vehicle.energyConsumptionKWperKm &&
                fastCharging == vehicle.fastCharging &&
                Objects.equals(model, vehicle.model) &&
                Objects.equals(motor, vehicle.motor) &&
                Objects.equals(battery, vehicle.battery);
    }

    @Override
    public int hashCode() {

        return Objects.hash(model, productionYear, energyConsumptionKWperKm,
                fastCharging, motor, battery);
    }

    public static final class VehicleBuilder {

        private String model = "No Model";
        private int productionYear;
        private int energyConsumptionKWperKm;
        private boolean fastCharging = false;
        private Motor motor = new Motor();
        private Battery battery = new Battery();

        public VehicleBuilder() {
        }

        public VehicleBuilder model(String model){
            this.model = model;
            return this;
        }

        public VehicleBuilder productionYear(int productionYear){
            assert (productionYear > 0);

            this.productionYear = productionYear;
            return this;
        }

        public VehicleBuilder energyConsumptionKWperKm(int energyConsumptionKWperKm){
            assert (energyConsumptionKWperKm > 0);

            this.energyConsumptionKWperKm = energyConsumptionKWperKm;
            return this;
        }

        public VehicleBuilder fastCharging(boolean fastCharging){
            this.fastCharging = fastCharging;
            return this;
        }

        public VehicleBuilder motor(Motor motor){
            assert (null != motor);

            this.motor = motor;
            return this;
        }

        public VehicleBuilder battery(Battery battery){
            assert (null != battery);

            this.battery = battery;
            return this;
        }

        public Vehicle build(){

            return new Vehicle(this);
        }
    }
}
