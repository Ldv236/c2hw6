package pro.sky.c2hw6.service;

import pro.sky.c2hw6.entity.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

public interface DepartmentService {
    Optional<Employee> findMaxSalaryEmployee(Integer department);
    Optional<Employee> findMinSalaryEmployee(Integer department);
    List<Employee> findEmployeesByDepartment(Integer department);
    Map<Integer, List<Employee>> findAllEmployeesSeparatedByDepartment();
    Integer findSumSalaryByDepartment(Integer id);
    OptionalInt findMaxSalaryByDepartment(Integer id);
    OptionalInt findMinSalaryByDepartment(Integer id);
}
