package pro.sky.c2hw6.service;

import org.springframework.stereotype.Service;
import pro.sky.c2hw6.entity.Employee;
import pro.sky.c2hw6.util.EmployeeAlreadyAddedException;
import pro.sky.c2hw6.util.EmployeeNotFoundException;
import pro.sky.c2hw6.util.EmployeeStorageIsFullException;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    static final int MAX_COUNT = 3;
    List<Employee> employees;

    public EmployeeServiceImpl(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (employees.size() == MAX_COUNT) {
            throw new EmployeeStorageIsFullException("Max capacity of employee list is reached, new employee not added");
        }

        Employee employee = new Employee(firstName, lastName);

        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Employee already exist in current list");
        }

        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        return employee;
    }

    @Override
    public List<Employee> findAllEmployees() {

        return Collections.unmodifiableList(employees);
    }
}
