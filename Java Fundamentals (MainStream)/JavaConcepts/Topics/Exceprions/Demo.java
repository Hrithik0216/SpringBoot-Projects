package Exceprions;

public class Demo {
    public static class Test {
        public void method1() {
            method2();
        }

        public void method2() {
            method3();
        }

        public void method3() {
            throw new ArithmeticException();
        }
    }

    public static void main(String[] args) {
        Test t1 = new Test();
//        t1.method1(); //ArithMetic
        String s = null;
//        char ch = s.charAt(0);
//        System.out.println(ch); //Null pointer
        String arun = "arun";
        int ansArun = Integer.parseInt(arun);
        System.out.println(ansArun);
    }
}
