package MethodsInJava;

public class Methods {

    /*
    * Instead of overloading everytime when number of parameters are unknown, use variable arguments parameters
    * int addvalue(int a, int b) {
        return a + b;
    }
    int addValue(int a, int b, int c) {
        return a + b + c;
    }
    int addValue(int a, int b, int c, int d) {
        return a + b + c + d;
    }
    * */
    static int addValue(int ...variable){
        int sum=0;
        for(int i: variable){
            sum+=i;
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println("Hello World");
//        Object method = new MethodsInJava.Methods();
        int result1 = Methods.addValue(1,2,3);
        int result2 = Methods.addValue(2,3,4,6,6,6,6);
        System.out.println(result1+" "+result2);
    }
}