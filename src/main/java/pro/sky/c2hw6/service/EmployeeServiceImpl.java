package pro.sky.c2hw6.service;

import org.springframework.stereotype.Service;
import pro.sky.c2hw6.entity.Employee;
import pro.sky.c2hw6.util.EmployeeAlreadyAddedException;
import pro.sky.c2hw6.util.EmployeeNotFoundException;
import pro.sky.c2hw6.util.EmployeeStorageIsFullException;
import pro.sky.c2hw6.util.InvalidInputException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    static final int MAX_COUNT = 10;
    Map<String, Employee> employeeMap;


    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer department, Integer salary) {

        if (!validateInputNames(firstName, lastName)) {
            throw new InvalidInputException();
        }

        if (employeeMap.size() >= MAX_COUNT) {
            throw new EmployeeStorageIsFullException("Max capacity of employee list is reached, new employee not added");
        }

        Employee employee = new Employee(firstName, lastName, department, salary);

        if (employeeMap.containsKey(employee.checkFullName())) {
            throw new EmployeeAlreadyAddedException("Employee already exist in current list");
        }

        employeeMap.put(employee.checkFullName(), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        if (!validateInputNames(firstName, lastName)) {
            throw new InvalidInputException();
        }

        if (!employeeMap.containsKey(firstName + " " + lastName)) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        return employeeMap.remove(firstName + " " + lastName);
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {

        if (!validateInputNames(firstName, lastName)) {
            throw new InvalidInputException();
        }

        if (!employeeMap.containsKey(firstName + " " + lastName)) {
            throw new EmployeeNotFoundException("Employee not found");
        }

        return employeeMap.get(firstName + " " + lastName);
    }

    @Override
    public Collection<Employee> findAllEmployees() {

        return Collections.unmodifiableCollection(employeeMap.values());
    }

    private boolean validateInputNames(String... names) {

        for (String name : names) {
            if(!isAlpha(name)) {
                return false;
            }
        }
        return true;
    }
}
