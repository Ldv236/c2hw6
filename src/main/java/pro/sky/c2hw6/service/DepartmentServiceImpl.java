package pro.sky.c2hw6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.c2hw6.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private  final EmployeeService employeeService;

    @Autowired
    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Optional<Employee> findMaxSalaryEmployee(Integer department) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public Optional<Employee> findMinSalaryEmployee(Integer department) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public List<Employee> findEmployeesByDepartment(Integer department) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllEmployeesSeparatedByDepartment() {

        return employeeService.findAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public  Integer findSumSalaryByDepartment(Integer id) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == id)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public  OptionalInt findMaxSalaryByDepartment(Integer id) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == id)
                .mapToInt(Employee::getSalary)
                .max();
    }

    @Override
    public  OptionalInt findMinSalaryByDepartment(Integer id) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == id)
                .mapToInt(Employee::getSalary)
                .min();
    }
}
