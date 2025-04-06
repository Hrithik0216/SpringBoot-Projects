package collections.Student;

import java.util.*;
import java.util.function.ToDoubleFunction;

public class Execution {
    public static void main(String[] args) {

        List<Student> students = Arrays.asList(
                new Student("John Doe", 23),
                new Student("Hrithik", 21),
                new Student("Alice", 20),
                new Student("Bob", 22),
                new Student("Charlie", 19),
                new Student("Diana", 24),
                new Student("Eve", 22),
                new Student("Frank", 23),
                new Student("Grace", 21),
                new Student("Henry", 20)
        );
        Iterator<Student> studentIterator =students.iterator();
        while(studentIterator.hasNext()){
            Student val = studentIterator.next();
            System.out.println("Stdent value: "+val.toString());
        }

        students.forEach(val->{
            System.out.println(val.toString());
        });

        //comparator and comparable
        //First way
        System.out.println("After sorting");
        Collections.sort(students,new Comparator<Student>(){
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        //Second way of using compareTo()
        /*
        * Create a new Class implementing Comparator<ClassTYpe> Interface.
        * Implement comapre() method
        * Create object of that class inside Collections.Sort(collection, new object of class implementing comparator interface)
        *
        * */
        Iterator<Student> afterSorting = students.iterator();
        while(afterSorting.hasNext()){
            Student val = afterSorting.next();
            System.out.println("Stdent value after sorting: "+val.toString());
        }

    }
}
