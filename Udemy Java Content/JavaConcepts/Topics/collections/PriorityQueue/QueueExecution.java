package collections.PriorityQueue;

import java.util.*;

public class QueueExecution {
    public static void main(String[] args) {
        Queue<Employee> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble((Employee::getSalary)));
        priorityQueue.add(new Employee("Hrithik", 30000, 2));
        priorityQueue.add(new Employee("Harry", 32000, 3));
        priorityQueue.add(new Employee("Ananya", 28000, 1));
        priorityQueue.add(new Employee("Ravi", 45000, 5));
        priorityQueue.add(new Employee("Priya", 40000, 4));
        priorityQueue.add(new Employee("Amit", 37000, 3));
        priorityQueue.add(new Employee("Neha", 35000, 2));
        priorityQueue.add(new Employee("Rahul", 29000, 1));
        priorityQueue.add(new Employee("Simran", 42000, 4));
        priorityQueue.add(new Employee("Ayesha", 31000, 2));
        priorityQueue.add(new Employee("Karan", 38000, 3));
        priorityQueue.add(new Employee("Vikram", 47000, 6));
        priorityQueue.add(new Employee("Sana", 34000, 2));
        priorityQueue.add(new Employee("Arjun", 36000, 3));
        priorityQueue.add(new Employee("Nidhi", 33000, 2));
        priorityQueue.add(new Employee("Siddharth", 39000, 4));
        priorityQueue.add(new Employee("Ishita", 41000, 5));
        priorityQueue.add(new Employee("Kriti", 44000, 5));
        priorityQueue.add(new Employee("Manoj", 46000, 6));
        priorityQueue.add(new Employee("Pooja", 37000, 3));

        while (!priorityQueue.isEmpty()) {
            System.out.println(priorityQueue.poll().getSalary());
        }
    }
}
