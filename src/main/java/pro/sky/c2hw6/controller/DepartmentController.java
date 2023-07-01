package pro.sky.c2hw6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.c2hw6.entity.Employee;
import pro.sky.c2hw6.service.DepartmentService;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Optional<Employee> findMaxSalaryEmployee(@RequestParam Integer departmentId) {
        return departmentService.findMaxSalaryEmployee(departmentId);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> findMinSalaryEmployee(@RequestParam Integer departmentId) {
        return departmentService.findMinSalaryEmployee(departmentId);
    }

    @GetMapping(value = "/{id}/employees")
    public List<Employee> findEmployeesByDepartment(@PathVariable Integer id) {
        return departmentService.findEmployeesByDepartment(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> findAllEmployees() {
        return departmentService.findAllEmployeesSeparatedByDepartment();
    }

    @GetMapping("/{id}/salary/sum")
    public Integer findSumSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.findSumSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/max")
    public  OptionalInt findMaxSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.findMaxSalaryByDepartment(id);
    }

    @GetMapping("/{id}/salary/min")
    public  OptionalInt findMinSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.findMinSalaryByDepartment(id);
    }
}
