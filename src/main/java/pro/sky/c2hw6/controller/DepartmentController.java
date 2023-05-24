package pro.sky.c2hw6.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.c2hw6.entity.Employee;
import pro.sky.c2hw6.service.DepartmentService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Optional<Employee> addEmployee(@RequestParam Integer departmentId) {
        return departmentService.findMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Optional<Employee> removeEmployee(@RequestParam Integer departmentId) {
        return departmentService.findMinSalary(departmentId);
    }

    @GetMapping("/all/{departmentId}")
    public List<Employee> findEmployee(@PathVariable Integer departmentId) {
        return departmentService.findEmployeesOfDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAllEmployee() {
        return departmentService.findAllEmployeesWithDepartmentSeparation();
    }
}
