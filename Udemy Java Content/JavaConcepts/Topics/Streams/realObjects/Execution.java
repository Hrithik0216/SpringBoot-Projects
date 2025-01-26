package Streams.realObjects;

import javax.swing.plaf.synth.SynthTextAreaUI;
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



//Total experiences of all employess
//        Map<String, Integer> experiencePerEmployee = employees.stream().
//                collect(Collectors.
//                        toMap(e->e.getName(), e->e.getExperiences().stream().
//                                mapToInt(x->x.getExperience()).sum()));
//        System.out.println(experiencePerEmployee);

// How can I retrieve a list of all employee names?
//        Map<Integer,String> names = employees.stream().collect(Collectors.toMap(id-> (Integer.parseInt(id.getEmployeeId())), name->name.getName()));
//        System.out.println(names);

//        How can I get a list of employees whose salary is greater than 40,000?
//        List<Employee> salaryGreaterThan40K = employees.stream().filter(e->e.getSalary()>40000).collect(Collectors.toList());
//        System.out.println(salaryGreaterThan40K);

//        How can I sort employees based on their salary in descending order?
//        List<Employee> salaryInDesc = employees.stream().sorted((e1,e2)->e2.getSalary()-e1.getSalary()).collect(Collectors.toList());
//        System.out.println(salaryInDesc);

//        How can I retrieve a list of all companies where employees have worked?
//        List<List<Experience>> companies = employees.stream().map(e-> e.getExperiences()).toList();
//        System.out.println(companies);
//        List<String> companiesList = companies.stream().flatMap(e->e.stream()).map(ee-> ee.getCompanyName()).toList();
//        System.out.println(companiesList);

//        How can I get a list of distinct technologies used by all employees?
//        List<List<Experience>> techStack =employees.stream()
//                .map(e->e.getExperiences()).toList();
//        List<Experience>  techStackData = techStack.stream().flatMap(e->e.stream()).toList();
//        List<Tech> finalList = techStackData.stream().map(e->e.getTechStack()).toList();
//       List<String> result =finalList.stream().flatMap(e->e.getName().stream()).distinct().toList();
//       System.out.println(result);

        //Mapping & Transformations
//How can I double the salary of each employee and store it in a new list?
//        List<Employee> result = employees.stream().map(e -> new Employee(e.getName(), e.getEmployeeId(), e.getSalary() * 2, e.getExperiences())).toList();
//        System.out.println(result);

        //Employees with uppercase tech stack
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

//        How can I create a map where the key is the employee's name and the value is their salary?
//        Map<String,Integer> MappedEmployeeNameAndSalary = employees.stream().collect(Collectors.toMap(e->e.getName(), e2->e2.getSalary()));
//        System.out.println(MappedEmployeeNameAndSalary);


        //Aggregation & Reduction
        // How can I find the total salary of all employees combined?
//        int totalSalary = employees.stream().mapToInt(e->e.getSalary()).sum();
//        System.out.println("Total salary: " + totalSalary);

        //How can I find the employee with the highest salary?
//        Employee employee = employees.stream().max((e1,e2)->e1.getSalary()-e2.getSalary()).orElse(null);
//        System.out.println(employee);

        //How can I find the average salary of all employees?
//        int totalSalary = employees.stream().mapToInt(e->e.getSalary()).sum();
//        System.out.println("Average salary: " + totalSalary/employees.size());

        //How can I calculate the total years of experience of all employees?
        Map<String, Integer> totalYearsOfExperiences = employees.stream().collect(Collectors.toMap(e1->e1.getName(), e2->e2.getExperiences().stream().mapToInt(x->x.getExperience()).sum()));
        int result=0;
        for(Map.Entry<String,Integer> entry : totalYearsOfExperiences.entrySet()){
            result+=entry.getValue();
        }
        System.out.println("Total number of experiences for all employees: " + result+" years");


    }
}
