package Java_Design_Patterns.Factory_Pattern.Dish_Factory;

import java.lang.classfile.instruction.SwitchCase;

public class DishFactory {
    public static Dish getFishInstanceBasedOnType(Dishes type) {
        switch (type) {
            case NODDLES:
                return new NoodlesDish();
            case PIZZA:
                return new PizzaDish();
            default:
                return new Dish() {
                    @Override
                    public void prepare() {
                        System.out.println("Preparing a generic dish object...");
                    }

                    @Override
                    public void serve() {
                        System.out.println("Serving a generic dish object...");
                    }
                };
        }
    }
}
