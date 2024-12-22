package ClassAndTypes;

public class NestedClass {
    String name;
    int age;
    static int rollNo;

    public class NestedClassExample {
        void display() {
            System.out.println("Name: " + name);
            System.out.println("role: " + rollNo);
        }
    }
    public class NestedClassExample2 {
        static void display() {
            /*
            * Non-static methods cannot be referenced to static methods
            * System.out.println("Name: " + name);
            * */

            System.out.println("role: " + rollNo);
        }
    }

    public static void main(String[] args) {
        NestedClass obj = new
                NestedClass();
        NestedClass.NestedClassExample newObj = obj.new NestedClassExample();
        newObj.display();
    }

}
