package Java_Design_Patterns.Factory_Pattern.Dish_Factory;

public class NoodlesDish implements Dish {
    @Override
    public void prepare() {
        System.out.println("Preparing Noodles....");
    }

    @Override
    public void serve() {
        System.out.println("Serving Noodles....");
    }
}
