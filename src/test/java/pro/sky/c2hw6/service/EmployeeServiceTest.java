package pro.sky.c2hw6.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.c2hw6.entity.Employee;
import pro.sky.c2hw6.util.EmployeeAlreadyAddedException;
import pro.sky.c2hw6.util.EmployeeNotFoundException;
import pro.sky.c2hw6.util.EmployeeStorageIsFullException;
import pro.sky.c2hw6.util.InvalidInputException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {

    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    public void shouldReturnInvalidInputException() {
        assertThrows(InvalidInputException.class,
                () -> employeeService.addEmployee("Ivan1", "Ivanov", 1, 100_000));
        assertThrows(InvalidInputException.class,
                () -> employeeService.removeEmployee("Ivan1", "Ivanov"));
        assertThrows(InvalidInputException.class,
                () -> employeeService.findEmployee("Ivan1", "Ivanov"));
    }

    @Test
    public void shouldReturnEmployeeStorageIsFullException() {
        for (int i = 0; i < EmployeeServiceImpl.MAX_COUNT; i++) {
            employeeService.addEmployee("Ivan" + (char)(i+65), "Ivanov", 1, 100_000);
        }
        assertThrows(EmployeeStorageIsFullException.class,
                () -> employeeService.addEmployee("Over", "Size", 1, 100_000));
    }

    @Test
    public void shouldReturnEmployeeAlreadyAddedException() {
        employeeService.addEmployee("Ivan", "Ivanov", 1, 100_000);
        assertThrows(EmployeeAlreadyAddedException.class,
                () -> employeeService.addEmployee("Ivan", "Ivanov", 1, 100_000));
    }

    @Test
    public void shouldReturnAddedEmployee() {
        Employee expectedIvan = new Employee("Ivan", "Ivanov", 1, 100_000);

        assertEquals(expectedIvan,
                employeeService.addEmployee("Ivan", "Ivanov", 1, 100_000));
    }

    @Test
    public void shouldReturnEmployeeNotFoundException() {
        employeeService.addEmployee("Ivan", "Ivanov", 1, 100_000);
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("Someone", "Other"));
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Someone", "Other"));
    }

    @Test
    public void shouldReturnRemovedEmployee() {
        Employee expectedIvan = new Employee("Ivan", "Ivanov", 1, 100_000);
        employeeService.addEmployee("Ivan", "Ivanov", 1, 100_000);
        assertEquals(expectedIvan,
                employeeService.removeEmployee("Ivan", "Ivanov"));
    }

    @Test
    public void shouldReturnFoundEmployee() {
        Employee expectedIvan = new Employee("Ivan", "Ivanov", 1, 100_000);
        employeeService.addEmployee("Ivan", "Ivanov", 1, 100_000);
        assertEquals(expectedIvan,
                employeeService.findEmployee("Ivan", "Ivanov"));
    }

    @Test
    public void shouldReturnCountEmployeeAndFindAddedEmployee() {
        boolean result = true;

        for (int i = 0; i < EmployeeServiceImpl.MAX_COUNT; i++) {

            Employee newIvan = new Employee("Ivan" + (char)(i+65), "Ivanov", 1, 100_000);
            employeeService.addEmployee("Ivan" + (char)(i+65), "Ivanov", 1, 100_000);

            if ((employeeService.findAllEmployees().size() != i + 1)
                    && (newIvan.equals(employeeService.findEmployee("Ivan" + (char)(i+65), "Ivanov")))) {
                result = false;
                break;
            }
        }
        assertTrue(result);
    }
}
