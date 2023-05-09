package pro.sky.c2hw6.service;

import pro.sky.c2hw6.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);
    Employee removeEmployee(String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    List<Employee> findAllEmployees();
}
