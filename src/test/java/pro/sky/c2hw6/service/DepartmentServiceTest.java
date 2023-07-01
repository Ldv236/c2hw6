package pro.sky.c2hw6.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.c2hw6.entity.Employee;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

//    @Mock
    private EmployeeService employeeService;

//    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    public void setUp() {
        employeeService = Mockito.mock(EmployeeService.class);
        out = new DepartmentServiceImpl(employeeService);
    }

    private List<Employee> createTestEmployeeList(int departmentId) {
        List<Employee> employeeList = new ArrayList<>();

        if (departmentId == 1 || departmentId == 0) {
            employeeList.add(new Employee("Ivan", "Ivanov", 1, 80_000));
            employeeList.add(new Employee("Ivan", "Sidorov", 1, 90_000));
            employeeList.add(new Employee("Ivan", "Petrov", 1, 100_000));
        }
        if (departmentId == 2 || departmentId == 0) {
            employeeList.add(new Employee("Ivan", "Bashirov", 2, 110_000));
            employeeList.add(new Employee("Vovan", "Lexus", 2, 120_000));
        }

        return employeeList;
    }
    //===================================================================================================

    @Test
    public void shouldReturnAllEmployeesByDepartment() {

        List<Employee> fullList = createTestEmployeeList(0);
        when(employeeService.findAllEmployees()).thenReturn(fullList);

        List<Employee> expected1 = createTestEmployeeList(1);
        List<Employee> actual1 = out.findEmployeesByDepartment(1);
        assertEquals(expected1, actual1);

        List<Employee> expected2 = createTestEmployeeList(2);
        List<Employee> actual2 = out.findEmployeesByDepartment(2);
        assertEquals(expected2, actual2);

        List<Employee> expectedNonExist = createTestEmployeeList(3);
        List<Employee> actualNonExist = out.findEmployeesByDepartment(4);
        assertEquals(expectedNonExist, actualNonExist);
    }

    @Test
    public void shouldReturnGroupingEmployee() {

        List<Employee> fullList = createTestEmployeeList(0);
        when(employeeService.findAllEmployees()).thenReturn(fullList);

        Map<Integer, List<Employee>> actual = out.findAllEmployeesSeparatedByDepartment();

        assertNotNull(actual.get(1));
        assertNotNull(actual.get(2));
        assertNull(actual.get(3));

        assertTrue(actual.get(1).stream().allMatch(e -> e.getDepartment() == 1));
        assertTrue(actual.get(2).stream().allMatch(e -> e.getDepartment() == 2));

        assertEquals("Ivan Ivanov", actual.get(1).get(0).checkFullName());
        assertEquals("Ivan Sidorov", actual.get(1).get(1).checkFullName());
        assertEquals("Ivan Petrov", actual.get(1).get(2).checkFullName());
        assertEquals("Ivan Bashirov", actual.get(2).get(0).checkFullName());
        assertEquals("Vovan Lexus", actual.get(2).get(1).checkFullName());
    }

    @Test
    public void shouldReturnSumSalaryByDepartment() {

        List<Employee> fullList = createTestEmployeeList(0);
        when(employeeService.findAllEmployees()).thenReturn(fullList);

        assertEquals(270_000, out.findSumSalaryByDepartment(1));
        assertEquals(230_000, out.findSumSalaryByDepartment(2));
        assertEquals(0, out.findSumSalaryByDepartment(3));
    }

    @Test
    public void shouldReturnMaxSalaryByDepartment() {

        List<Employee> fullList = createTestEmployeeList(0);
        when(employeeService.findAllEmployees()).thenReturn(fullList);

        assertEquals(100_000, out.findMaxSalaryByDepartment(1).getAsInt());
        assertEquals(120_000, out.findMaxSalaryByDepartment(2).getAsInt());
        assertFalse(out.findMaxSalaryByDepartment(3).isPresent());
    }

    @Test
    public void shouldReturnMinSalaryByDepartment() {

        List<Employee> fullList = createTestEmployeeList(0);
        when(employeeService.findAllEmployees()).thenReturn(fullList);

        assertEquals(80_000, out.findMinSalaryByDepartment(1).getAsInt());
        assertEquals(110_000, out.findMinSalaryByDepartment(2).getAsInt());
        assertFalse(out.findMinSalaryByDepartment(3).isPresent());
    }
}
