package Streams.realObjects;

import java.util.List;

public class Employee {
    private String name;
    private int salary;
    private String employeeId;
    private List<Experience> experiences;
    public Employee(String name, String employeeId, int salary,List<Experience> experiences) {
        this.name = name;
        this.employeeId = employeeId;
        this.salary = salary;
        this.experiences = experiences;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    @Override
    public String toString() {
        return employeeId + "\t" + name + "\t" + salary + "\t" + experiences;
    }
}
