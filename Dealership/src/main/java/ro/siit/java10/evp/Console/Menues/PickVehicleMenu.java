package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Dealership.Options;
import ro.siit.java10.evp.Console.Selector;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.Dealership.VehicleData;

import java.util.List;

public abstract class PickVehicleMenu extends Menu {

    protected Dealership deals;
    private MenuTypes callingMenu;

    public enum VehicleListType{
        STOCK{
            @Override
            public PickVehicleMenu getMenuInstance(Dealership deals, MenuTypes callingMenu) {
                return new PickVehicleStockList(deals, callingMenu);
            }
        },
        FAST_CHARGING{
            @Override
            public PickVehicleMenu getMenuInstance(Dealership deals, MenuTypes callingMenu) {
                return new PickVehicleFastChargingList(deals, callingMenu);
            }
        },
        HORSEPOWER {
            @Override
            public PickVehicleMenu getMenuInstance(Dealership deals, MenuTypes callingMenu) {
                return new PickVehicleHorsepowerList(deals, callingMenu);
            }
        },
        PRICE {
            @Override
            public PickVehicleMenu getMenuInstance(Dealership deals, MenuTypes callingMenu) {
                return new PickVehiclePriceList(deals, callingMenu);
            }
        },
        RANGE {
            @Override
            public PickVehicleMenu getMenuInstance(Dealership deals, MenuTypes callingMenu) {
                return new PickVehicleRangeList(deals, callingMenu);
            }
        };

        public abstract PickVehicleMenu getMenuInstance(Dealership deals, MenuTypes callingMenu);
    }

    private PickVehicleMenu(Dealership deals, MenuTypes callingMenu) {
        this.deals = deals;
        this.callingMenu = callingMenu;
    }

    protected MenuTypes resolveOption(List<VehicleData> vehicleList, List<String> strRepresList){

        strRepresList.add("back");
        Selector selector = new Selector(consIO, strRepresList);

        int option = selector.printListAndGetOption();

        if (option == -1)
            return MenuTypes.PICK_VEHICLE;

        if (option == vehicleList.size())
            return callingMenu;


        MenuTypes.INDIVIDUAL_VEHICLE.setMenu(new IndividualVehicleMenu(
                    MenuTypes.PICK_VEHICLE, deals, vehicleList.get(option)));

        return MenuTypes.INDIVIDUAL_VEHICLE;


    }

    public static class PickVehicleStockList extends PickVehicleMenu {

        public PickVehicleStockList(Dealership deals, MenuTypes callingMenu) {

            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            List<VehicleData> vehicleList = deals.getVehicleSorter().getStockVehicleList();
            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    Options.YEAR);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehicleFastChargingList extends PickVehicleMenu {

        public PickVehicleFastChargingList(Dealership deals, MenuTypes callingMenu) {
            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            List<VehicleData> vehicleList = deals.getVehicleSorter().getFastChargingList();
            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    Options.YEAR, Options.FAST_CHARGING);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehicleHorsepowerList extends PickVehicleMenu {

        public PickVehicleHorsepowerList(Dealership deals, MenuTypes callingMenu) {
            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            List<Dealership.VehicleData> vehicleList = deals.getVehicleSorter().getSortedHorsepowerList();
            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    Options.YEAR, Options.MOTOR);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehiclePriceList extends PickVehicleMenu {

        public PickVehiclePriceList(Dealership deals, MenuTypes callingMenu) {
            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            List<Dealership.VehicleData> vehicleList = deals.getVehicleSorter().getSortedPriceList();
            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    Options.YEAR, Options.PRICE);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehicleRangeList extends PickVehicleMenu {

        public PickVehicleRangeList(Dealership deals, MenuTypes callingMenu) {
            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            List<Dealership.VehicleData> vehicleList = deals.getVehicleSorter().getSortedRangePerChargeList();
            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    Options.YEAR, Options.RANGE_PER_CHARGE);

            return resolveOption(vehicleList, vehicleStr);
        }
    }
}
