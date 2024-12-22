package GenericClass.GenericClasses.Bounds;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        Bus bus = new Bus();
        List<Vehicle> vehicles = new ArrayList<>();
        /*
        * We can add car and bus to Vehicle as they are their children
        * */
        vehicles.add(new Car());
        vehicles.add(new Bus());
//        List<Bus> buses = new ArrayList<>();
//        buses.add(new Car());
    }
}
