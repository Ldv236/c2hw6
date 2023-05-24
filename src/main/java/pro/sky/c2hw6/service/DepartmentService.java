package pro.sky.c2hw6.service;

import pro.sky.c2hw6.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    Optional<Employee> findMaxSalary(Integer department);
    Optional<Employee> findMinSalary(Integer department);
    List<Employee> findEmployeesOfDepartment(Integer department);
    Map<Integer, List<Employee>> findAllEmployeesWithDepartmentSeparation();
}
