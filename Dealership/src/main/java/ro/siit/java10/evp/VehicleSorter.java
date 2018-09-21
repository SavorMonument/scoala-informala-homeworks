package ro.siit.java10.evp;

import java.util.*;

public class VehicleSorter {

    private List<VehicleData> vehicleDataList;

    public VehicleSorter(List<VehicleData> vehicleDataList) {
        assert (null != vehicleDataList);

        this.vehicleDataList = vehicleDataList;
    }

    public enum FilterOptions {

        STOCK(new Filter.StockVehicles()),
        FAST_CHARGING(new Filter.FastChargingVehicles());

        Filter fi;

        FilterOptions(Filter f) {
            this.fi = f;
        }

        public Filter getFilter() {
            return fi;
        }
    }

    public enum SortingOptions {

        NORMAL(new Sorter.NormalSortedVehicles()),
        PRICE(new Sorter.PriceSortedVehicles()),
        HORSEPOWER(new Sorter.HorsepowerVehicles()),
        RANGE(new Sorter.RangeVehicles());

        Sorter so;

        SortingOptions(Sorter so) {
            this.so = so;
        }

        public Sorter getSorter() {
            return so;
        }
    }

    public List<VehicleData> getVehicleList(SortingOptions sOption, FilterOptions... fOptions){

        List<VehicleData> copyList = getCopyList(vehicleDataList);

        for(FilterOptions fo: fOptions){

            copyList = fo.getFilter().filter(copyList);
        }

        copyList = sOption.getSorter().sort(copyList);

        return copyList;
    }

    private static abstract class Sorter{

        private static List<VehicleData> getSorted(List<VehicleData> list, Comparator<VehicleData> comp){

            quickSort(list, 0, list.size() - 1, comp);

            return list;
        }

        abstract List<VehicleData> sort(List<VehicleData> list);

        private static class NormalSortedVehicles extends Sorter{

            @Override
            List<VehicleData> sort(List<VehicleData> list) {

                return list;
            }
        }

        private static class PriceSortedVehicles extends Sorter{

            @Override
            List<VehicleData> sort(List<VehicleData> list) {

                Comparator<VehicleData> comp = (o1, o2) -> (int) (o1.price - o2.price);

                return getSorted(list, comp);
            }
        }

        private static class HorsepowerVehicles extends Sorter{

            @Override
            List<VehicleData> sort(List<VehicleData> list) {

                Comparator<VehicleData> comp = (o1, o2) -> (o1.motor.getHorsepower() - o2.motor.getHorsepower());

                return getSorted(list, comp);
            }
        }

        private static class RangeVehicles extends Sorter{

            @Override
            List<VehicleData> sort(List<VehicleData> list) {

                Comparator<VehicleData> comp = (o1, o2) -> (o1.rangePerCharge_Km - o2.rangePerCharge_Km);

                return getSorted(list, comp);
            }
        }

    }

    private static abstract class Filter{

        abstract List<VehicleData> filter(List<VehicleData> list);

        private static class StockVehicles extends Filter{

            public List<VehicleData> filter(List<VehicleData> list){

                list.removeIf(vehicleData -> vehicleData.stock <= 0);

                return list;
            }
        }

        private static class FastChargingVehicles extends Filter{

            @Override
            List<VehicleData> filter(List<VehicleData> list) {

                list.removeIf(vehicleData -> !vehicleData.fastCharging);

                return list;
            }
        }
    }


    private static List<VehicleData> getCopyList(List<VehicleData> list){

        return new ArrayList<>(list);
    }

    private static <T> void quickSort(List<T> list, int left, int right, Comparator<T> comp){

        if (left < right){

            int pivot = getPivot(list, left, right, comp);

            quickSort(list, left, pivot - 1, comp);
            quickSort(list, pivot + 1, right, comp);
        }
    }

    private static <T> int getPivot(List<T> list, int left, int right, Comparator<T> comp){

        T pivot = list.get(right);

        int i = left - 1;

        for(int j = left; j < right; j++){

            if (comp.compare(list.get(j), pivot) < 0){
                i++;
                swap(list, i, j);
            }
        }

        swap(list, i + 1, right);
        return i + 1;
    }

    private static <T> void swap(List<T> list, int i, int j){

        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

}
