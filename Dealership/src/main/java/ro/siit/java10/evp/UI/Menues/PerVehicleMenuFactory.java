package ro.siit.java10.evp.UI.Menues;

import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;

public abstract class PerVehicleMenuFactory extends Menu {

    public static PerVehicleMenuFactory getVehicleMenu(MenuTypes callingMenu, Dealership deal, VehicleData vehicleD){

        if (isModLogged())
            return new PerVehicleModMenu(callingMenu, deal, vehicleD);
        else
            return new PerVehicleClientMenu(callingMenu, deal, vehicleD);
    }
}
