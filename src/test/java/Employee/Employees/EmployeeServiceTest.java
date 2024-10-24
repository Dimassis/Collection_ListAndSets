package Employee.Employees;

import Employee.Employees.exception.EmployeeAlreadyAddedException;
import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.exception.EmployeeStorageIsFullException;
import Employee.Employees.exception.EmployeeWrongInputException;
import Employee.Employees.modul.Employee;
import Employee.Employees.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    public static String ALREADY_EXISTS = "Сотрудник уже добавлен";
    public static String NOT_FOUND = "Сотрудник не найден";
    public static String IS_FULL = "У вас максимальное колво сотрудников";
    public static String WRONG_INPUT = "Некорректный ввод";

    Employee employee1 = new Employee("Franklin", "Clinton", 100000, 1);
    Employee employee2 = new Employee("Trevor", "Filips", 120000, 1);
    Employee employee3 = new Employee("Kevin", "Coin", 120000, 2);

    private EmployeeServiceImpl employeeService;
    Map<String, Employee> employees = new HashMap<>(Map.of());

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
        employees.put(employee1.getFullName(), employee1);
        employees.put(employee2.getFullName(), employee2);
    }

    @Test
    public void addEmployeeTest() {
        assertEquals(2, employees.size());
        assertTrue(employees.containsKey(employee1.getFullName()));
        assertEquals(employee1, employees.get(employee1.getFullName()));
    }

    @Test
    void deleteEmployeeTest() {
        employees.remove(employee1.getFullName());
        assertEquals(1, employees.size());
        assertTrue(employees.containsKey(employee2.getFullName()));
        assertEquals(employee2, employees.get(employee2.getFullName()));
    }

    @Test
    void findEmployeeByFullNameTest() {
        assertTrue(employees.containsKey(employee1.getFullName()));
        employees.put(employee3.getFullName(), employee3);
        assertTrue(employees.containsKey(employee3.getFullName()));
    }

    @Test
    public void testAddEmployee() throws EmployeeAlreadyAddedException, EmployeeWrongInputException, EmployeeStorageIsFullException {
        Employee employee = new Employee("Dmitry", "Dddd", 100000, 1);
        employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDeportmentId());
        assertThrows(EmployeeAlreadyAddedException.class, () -> {
            employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDeportmentId());
        });
    }

    @Test
    public void testIsFull() throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException, EmployeeWrongInputException {
        Employee employee = new Employee("Dmitry", "Dddd", 100000, 1);
        employeeService.addEmployee(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDeportmentId());
        Employee employee2 = new Employee("Dmitr", "Dddd", 100000, 1);
        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastName(), employee2.getSalary(), employee2.getDeportmentId());
        Employee employee3 = new Employee("Dmit", "Dddd", 100000, 1);
        employeeService.addEmployee(employee3.getFirstName(), employee3.getLastName(), employee3.getSalary(), employee3.getDeportmentId());
        Employee employee4 = new Employee("Dmi", "Dddd", 100000, 1);
        employeeService.addEmployee(employee4.getFirstName(), employee4.getLastName(), employee4.getSalary(), employee4.getDeportmentId());
        Employee employee5 = new Employee("Dm", "Dddd", 100000, 1);
        employeeService.addEmployee(employee5.getFirstName(), employee5.getLastName(), employee5.getSalary(), employee5.getDeportmentId());
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee("Dmitryy", "Dddd", 100000, 1));
    }

    @Test
    public void testNotFound()  {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("D","W"));
    }

    @ParameterizedTest
    @MethodSource("provideWrong")
    public void testInvalidInput(Employee employee, String input) {
        Exception exception = assertThrows(EmployeeWrongInputException.class,
                () -> employeeService.checkEmployee(employee.getFullName()));
        Assertions.assertEquals(input, exception.getMessage());
    }

    static Stream<Arguments> provideWrong() {
        return Stream.of(
                Arguments.of(new Employee("Dmitr2", "Dmitry", 100000, 1), WRONG_INPUT),
                Arguments.of(new Employee("D ", "Dmitry", 100000, 1), WRONG_INPUT),
                Arguments.of(new Employee("Dmitry", "D ", 100000, 1), WRONG_INPUT),
                Arguments.of(new Employee("Dmitry", "Dmitry!", 100000, 1), WRONG_INPUT),
                Arguments.of(new Employee(" ", " ", 100000, 1), WRONG_INPUT)
        );
    }
}

