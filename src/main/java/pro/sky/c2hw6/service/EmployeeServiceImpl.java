package pro.sky.c2hw6.service;

import org.springframework.stereotype.Service;
import pro.sky.c2hw6.entity.Employee;
import pro.sky.c2hw6.util.EmployeeAlreadyAddedException;
import pro.sky.c2hw6.util.EmployeeNotFoundException;
import pro.sky.c2hw6.util.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    static final int MAX_COUNT = 10;
    Map<String, Employee> employeeMap;

    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {

        if (employeeMap.size() >= MAX_COUNT) {

            throw new EmployeeStorageIsFullException("Max capacity of employee list is reached, new employee not added");
        }

        Employee employee = new Employee(firstName, lastName);

        if (employeeMap.containsKey(employee.checkFullName())) {
            throw new EmployeeAlreadyAddedException("Employee already exist in current list");
        }

        employeeMap.put(employee.checkFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (!employeeMap.containsKey(employee.checkFullName())) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        return employeeMap.remove(employee.checkFullName());
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        Employee employee = new Employee(firstName, lastName);

        if (!employeeMap.containsKey(employee.checkFullName())) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        return employeeMap.get(employee.checkFullName());
    }

    @Override
    public Collection<Employee> findAllEmployees() {

        return Collections.unmodifiableCollection(employeeMap.values());
    }
}
