package GenericClass.GenericClasses.Bounds;

public class Vehicle {
    private String model;
    private int horsePower;
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    void applyBrake(){
        System.out.println("Brake");
    }

}
