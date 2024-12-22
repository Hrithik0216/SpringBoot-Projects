package GenericClass.GenericClasses;

public class MainClass {
    public static void main(String[] args) {
        Bus<String> bus = new Bus<String>();
        Bus<Number> bus2 = new Bus<>();
        GenericMethod gm = new GenericMethod();
        Bus2<String,String> bus3 = new Bus2<>();
        bus3.setAddress("Sri kanchi nagar");
        Object address = bus3.getAddress();
        System.out.println(address);
        /*
        * Return type of the method is unknown so we have use object
        * */
        Object answer =  gm.printDetails("3");
        System.out.println(answer.getClass());
        System.out.println(answer);
        bus.print("Vatsan");
        bus2.print(22.2);
    }
}
