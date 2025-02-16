package LLD_Fundamentals.Liskov_Substitution_Principle;

import java.util.ArrayList;
import java.util.List;

public class Execution {
    public static void main(String[] args) {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new MotorCycle());
        vehicles.add(new Bicycle());
        vehicles.add(new Bicycle());
        vehicles.add(new Bicycle());

        for(Vehicle vehicle : vehicles){
            System.out.println(vehicle.getNumberOfSeats());
        }
    }
}
