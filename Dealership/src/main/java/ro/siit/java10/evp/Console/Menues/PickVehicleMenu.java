package ro.siit.java10.evp.Console.Menues;

import ro.siit.java10.evp.Console.Selector;
import ro.siit.java10.evp.Dealership;
import ro.siit.java10.evp.VehicleData;
import ro.siit.java10.evp.VehicleSorter;
import ro.siit.java10.evp.VehicleSorter.SortingOptions;
import ro.siit.java10.evp.VehicleSorter.FilterOptions;

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

            VehicleSorter vs = deals.getVehicleSorter();
            List<VehicleData> vehicleList = vs.getVehicleList(SortingOptions.NORMAL, FilterOptions.STOCK);

            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    VehicleData.Options.YEAR);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehicleFastChargingList extends PickVehicleMenu {

        public PickVehicleFastChargingList(Dealership deals, MenuTypes callingMenu) {

            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            VehicleSorter vs = deals.getVehicleSorter();
            List<VehicleData> vehicleList = vs.getVehicleList(SortingOptions.NORMAL, FilterOptions.FAST_CHARGING,
                    FilterOptions.STOCK);

            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    VehicleData.Options.YEAR, VehicleData.Options.FAST_CHARGING);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehicleHorsepowerList extends PickVehicleMenu {

        public PickVehicleHorsepowerList(Dealership deals, MenuTypes callingMenu) {

            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            VehicleSorter vs = deals.getVehicleSorter();
            List<VehicleData> vehicleList = vs.getVehicleList(SortingOptions.HORSEPOWER, FilterOptions.STOCK);

            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    VehicleData.Options.YEAR, VehicleData.Options.MOTOR);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehiclePriceList extends PickVehicleMenu {

        public PickVehiclePriceList(Dealership deals, MenuTypes callingMenu) {
            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            VehicleSorter vs = deals.getVehicleSorter();
            List<VehicleData> vehicleList = vs.getVehicleList(SortingOptions.PRICE, FilterOptions.STOCK);

            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    VehicleData.Options.YEAR, VehicleData.Options.PRICE);

            return resolveOption(vehicleList, vehicleStr);
        }
    }

    public static class PickVehicleRangeList extends PickVehicleMenu {

        public PickVehicleRangeList(Dealership deals, MenuTypes callingMenu) {
            super(deals, callingMenu);
        }

        @Override
        public MenuTypes resolveMenuAndGetNextType() {

            VehicleSorter vs = deals.getVehicleSorter();
            List<VehicleData> vehicleList = vs.getVehicleList(SortingOptions.RANGE, FilterOptions.STOCK);

            List<String> vehicleStr = Selector.vehicleDataListToStringList(vehicleList,
                    VehicleData.Options.YEAR, VehicleData.Options.RANGE_PER_CHARGE);

            return resolveOption(vehicleList, vehicleStr);
        }
    }
}
