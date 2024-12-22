package ConstructorTypes;/*
* This explains about parametrized constructor, No args constructor,
* Parametrized, constructor overloading, private contsructor
* Constructor chaining -> this, super()in child sub class
* */

public class Person {
    String name;
    int age;
    public Person() {
        this("Hrithik");
        System.out.print("non parametrized");;
    }
    public Person(String name){
        this.name = name;
        System.out.println("ConstructorTypes.Person constructor: "+name);
    }
//    public static void main(String[] args) {
//        ConstructorTypes.Person person = new ConstructorTypes.Person();
//    }
}