package pro.sky.c2hw6.service;

import org.springframework.stereotype.Service;
import pro.sky.c2hw6.entity.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private  final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Optional<Employee> findMaxSalary(Integer department) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public Optional<Employee> findMinSalary(Integer department) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary));
    }

    @Override
    public List<Employee> findEmployeesOfDepartment(Integer department) {
        return employeeService.findAllEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Map<Integer, List<Employee>> findAllEmployeesWithDepartmentSeparation() {

        return employeeService.findAllEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
