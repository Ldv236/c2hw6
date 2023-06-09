package pro.sky.c2hw6.entity;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Employee {

    private final String firstName;
    private final String lastName;
    private int department;
    private int salary;

    // for add employee
    public Employee(String firstName, String lastName, Integer department, Integer salary) {
        this.firstName = capitalize(firstName.toLowerCase());
        this.lastName = capitalize(lastName.toLowerCase());
        this.department = department;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public String checkFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Name " + this.checkFullName() +
                " Department " + department +
                " Salary " + salary;
    }
}
