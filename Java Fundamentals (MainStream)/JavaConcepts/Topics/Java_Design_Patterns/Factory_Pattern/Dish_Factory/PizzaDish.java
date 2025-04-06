package Java_Design_Patterns.Factory_Pattern.Dish_Factory;

public class PizzaDish implements Dish {
    @Override
    public void prepare() {
        System.out.println("Preparing Pizza...");
    }

    @Override
    public void serve() {
        System.out.println("Serving Pizza...");
    }
}
