package ParrallelStreams;

import collections.Student.Student;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Execution {
    public static void main(String[] args) {
        List<Student> allStudents = Arrays.asList(
                new Student("Hrithik", 25), new Student("Arun", 26), new Student("Harry", 27),
                new Student("Harish", 28), new Student("Priya", 29), new Student("Rahul", 30),
                new Student("Neha", 31), new Student("Kiran", 32), new Student("Amit", 33),
                new Student("Sanya", 34), new Student("Pooja", 35), new Student("Vikram", 36),
                new Student("Rohan", 37), new Student("Manish", 38), new Student("Sneha", 39),
                new Student("Varun", 40), new Student("Deepika", 41), new Student("Suresh", 42),
                new Student("Meena", 43), new Student("Ajay", 44), new Student("Sunita", 45),
                new Student("Raj", 46), new Student("Komal", 47), new Student("Ashok", 48),
                new Student("Jyoti", 49), new Student("Tarun", 50), new Student("Anjali", 51),
                new Student("Nitin", 52), new Student("Swati", 53), new Student("Mohan", 54)
        );

        int avpro = Runtime.getRuntime().availableProcessors();
        System.out.println("Available processors: " + avpro);

        // Sequential Stream Execution
        long timeNow = System.currentTimeMillis();
        List<String> seq = allStudents.stream()
                .filter(student -> student.getAge() > 10)
                .map(e -> e.getAge() + " Thread: " + Thread.currentThread().getName())
                .collect(Collectors.toList());
        long timeThen = System.currentTimeMillis();
        System.out.println("Total time: " + (timeNow - timeThen));

        // Parallel Stream Execution
        timeNow = System.currentTimeMillis();
        List<String> par = allStudents.parallelStream()
                .filter(student -> student.getAge() > 10).map(e -> e.getAge() + " Thread: " + Thread.currentThread().getName())
                .collect(Collectors.toList());
        timeThen = System.currentTimeMillis();
        System.out.println("Parallel Execution Time: " + (timeThen - timeNow) + " ms");
        par.forEach(student -> System.out.println(student));
    }
}
