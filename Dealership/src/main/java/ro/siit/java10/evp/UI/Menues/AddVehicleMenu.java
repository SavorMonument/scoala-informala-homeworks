package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.*;

public class AddVehicleMenu extends Menu {

    private MenuTypes callingMenu;
    private Dealership deals;

    public AddVehicleMenu(MenuTypes callingMenu, Dealership deals) {
        this.callingMenu = callingMenu;
        this.deals = deals;
    }

    @Override
    public MenuTypes resolveMenuAndGetNextType() {

        VehicleData vehicleD = readVehicle();

        if (null != vehicleD)
            deals.addVehicle(vehicleD);

        return callingMenu;
    }

    private VehicleData readVehicle(){

        String strInput;
        int intInput;

        VehicleData vehicleD = new VehicleData();

        System.out.print("Vehicle model: ");
        if (null == (strInput = consIO.readLine()))
            return null;
        vehicleD.model(strInput);

        System.out.print("Vehicle production year: ");
        if (-1 == (intInput = consIO.readCondInt(1900, 2018)))
            return null;
        vehicleD.productionYear(intInput);

        System.out.print("Vehicle energy consumption KW/Km: ");
        if (-1 == (intInput = consIO.readCondInt(1, 1000)))
            return null;
        vehicleD.energyConsumptionKWperKm(intInput);

        System.out.print("Vehicle has fast charging(1 or 0): ");
        if (-1 == (intInput = consIO.readCondInt(0, 1)))
            return null;
        if (intInput == 1){
            vehicleD.fastCharging(true);
        } else
            vehicleD.fastCharging(false);

        vehicleD.motor = new Motor();
        vehicleD.battery = new Battery();

        return vehicleD;
    }
}
