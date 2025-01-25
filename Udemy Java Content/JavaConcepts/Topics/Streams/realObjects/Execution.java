package Streams.realObjects;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Execution {
    public static void main(String[] args) {
        Tech tech1 = new Tech(Arrays.asList("Java", "MongoDB", "JavaScript"));
        Tech tech2 = new Tech(Arrays.asList("Java", "Python", "SQL"));
        Tech tech3 = new Tech(Arrays.asList("C#", "Azure", "React"));
        Tech tech4 = new Tech(Arrays.asList("Kotlin", "Spring Boot", "PostgreSQL"));

        Experience exp1 = new Experience("Salesgear", 2, tech1);
        Experience exp2 = new Experience("Microsoft", 3, tech2);
        Experience exp3 = new Experience("Google", 1, tech3);
        Experience exp4 = new Experience("Amazon", 4, tech4);
        Experience exp5 = new Experience("Netflix", 5, tech1);

        List<Employee> employees = Arrays.asList(
                new Employee("Hrithik", "101", 23500, Arrays.asList(exp1, exp2)),
                new Employee("John", "102", 32000, Arrays.asList(exp3, exp4)),
                new Employee("Alice", "103", 45000, Arrays.asList(exp5, exp2)),
                new Employee("Bob", "104", 28000, Arrays.asList(exp1, exp3)),
                new Employee("Eve", "105", 50000, Arrays.asList(exp4, exp5)),
                new Employee("Sophia", "106", 41000, Arrays.asList(exp2, exp3)),
                new Employee("Liam", "107", 37000, Arrays.asList(exp1, exp5)),
                new Employee("Emma", "108", 60000, Arrays.asList(exp3, exp4)),
                new Employee("James", "109", 29000, Arrays.asList(exp2, exp1)),
                new Employee("Olivia", "110", 42000, Arrays.asList(exp4, exp3))
        );


//        List<Employee> salaryMappedEmployees = employees.stream().map(emp -> new Employee(emp.getName(), emp.getEmployeeId(), emp.getSalary() * 2, emp.getExperiences())).collect(Collectors.toList());
//        System.out.println(salaryMappedEmployees);
//        List<Employee> salaryMappedEmployeesAndUpperCaseTech = employees.stream()
//                .map(emp -> new Employee(
//                        emp.getName(),
//                        emp.getEmployeeId(),
//                        emp.getSalary(),
//                        emp.getExperiences().stream()
//                                .map(exp -> new Experience(
//                                        exp.getCompanyName(),
//                                        exp.getExperience(),
//                                        new Tech(
//                                                exp.getTechStack().getName().stream()
//                                                        .map(String::toUpperCase)
//                                                        .collect(Collectors.toList())
//                                        )
//                                ))
//                                .collect(Collectors.toList())
//                ))
//                .collect(Collectors.toList());
//        System.out.println(salaryMappedEmployees);
//        Stream stream = Stream.of(employee);
//        stream.map(s->s.toString().toUpperCase()).forEach(System.out::println);
//        List<String> finalTech= employee.getExperiences().stream().map(s->s.getTechStack().getName().toString().toLowerCase()).collect(Collectors.toList());
//        System.out.println(finalTech);


        Map<String, Integer> experiencePerEmployee = employees.stream().
                collect(Collectors.
                        toMap(e->e.getName(), e->e.getExperiences().stream().
                                mapToInt(x->x.getExperience()).sum()));
        System.out.println(experiencePerEmployee);
    }
}
