package Java_Design_Patterns.Factory_Pattern.Dish_Factory;

public class DishImplementation {
    public static void main(String[] args) {
        Dish d1 = DishFactory.getFishInstanceBasedOnType(Dishes.NODDLES);
        d1.prepare();
        d1.serve();
        Dish d2 = DishFactory.getFishInstanceBasedOnType(Dishes.PIZZA);
        d2.prepare();
        d2.serve();
    }
}
