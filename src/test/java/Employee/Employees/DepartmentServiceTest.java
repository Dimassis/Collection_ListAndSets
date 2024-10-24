package Employee.Employees;

import Employee.Employees.Interfaces.DepartmentService;
import Employee.Employees.Interfaces.EmployeeService;
import Employee.Employees.exception.EmployeeAlreadyAddedException;
import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.exception.EmployeeStorageIsFullException;
import Employee.Employees.exception.EmployeeWrongInputException;
import Employee.Employees.modul.Employee;
import Employee.Employees.service.DepartmentServiceImpl;
import Employee.Employees.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;

    Employee employee1 = new Employee("Franklin", "Clinton", 100000, 1);
    Employee employee2 = new Employee("Trevor", "Filips", 120000, 1);
    Map<String, Employee> employee = new HashMap<>();

    @BeforeEach
    public void setUp() throws EmployeeAlreadyAddedException, EmployeeWrongInputException, EmployeeStorageIsFullException {
        employee.put(employee1.getFullName(), employee1);
        employee.put(employee2.getFullName(), employee2);

        employeeService.addEmployee(employee1.getFirstName(), employee1.getLastName(), employee1.getSalary(), employee1.getDeportmentId());
        employeeService.addEmployee(employee2.getFirstName(), employee2.getLastName(), employee2.getSalary(), employee2.getDeportmentId());
    }

    @Test
    public void testSumSalary() throws EmployeeNotFoundException {

        Mockito.when(employeeService.allEmployee()).thenReturn(employee);

        double sumSalary = departmentService.getSumSalaryDepartment(1);
        double expectedSum = employee1.getSalary() + employee2.getSalary();

        Assertions.assertEquals(sumSalary, expectedSum);
    }

    @Test
    public void testMaxSalaryDepartment() throws EmployeeNotFoundException {
        Mockito.when(employeeService.allEmployee()).thenReturn(employee);

        String sumSalary = departmentService.maxSalaryDepartment(1);
        String expectedSum = String.valueOf(
                employee.values().stream()
                        .max(Comparator.comparingInt(Employee::getSalary))
                        .map(Employee::getSalary)
                        .orElseThrow(() -> new EmployeeNotFoundException(""))
        );

        Assertions.assertEquals(sumSalary, expectedSum);
    }

    @Test
    public void testMinSalaryDepartment() throws EmployeeNotFoundException {
        Mockito.when(employeeService.allEmployee()).thenReturn(employee);

        String sumSalary = departmentService.minSalaryDepartment(1);
        String expectedSum = String.valueOf(
                employee.values().stream()
                        .min(Comparator.comparingInt(Employee::getSalary))
                        .map(Employee::getSalary)
                        .orElseThrow(() -> new EmployeeNotFoundException(""))
        );

        Assertions.assertEquals(sumSalary, expectedSum);
    }

    @Test
    public void testGetAllEmployeeDep() throws EmployeeNotFoundException {
        Mockito.when(employeeService.allEmployee()).thenReturn(employee);

        Map<String, Employee> employeeMap = departmentService.getEmployeesDepartment(1);

        Assertions.assertEquals(employee, employeeMap);
    }

    @Test
    public void testSortedDepartment() throws EmployeeNotFoundException {

        Employee employee10 = new Employee("Dmitry", "Dddd", 100000, 1);
        Employee employee11 = new Employee("Dmitr", "Dddd", 120000, 2);
        Employee employee12 = new Employee("Dmit", "Dddd", 1100000, 3);
        Employee employee13 = new Employee("Dmi", "Dddd", 150000, 4);
        Employee employee14 = new Employee("Dm", "Dddd", 1040000, 5);

        Map<String, Employee> employee = new LinkedHashMap<>();
        employee.put(employee12.getFullName(), employee12);
        employee.put(employee11.getFullName(), employee11);
        employee.put(employee13.getFullName(), employee13);
        employee.put(employee10.getFullName(), employee10);
        employee.put(employee14.getFullName(), employee14);

        Mockito.when(employeeService.allEmployee()).thenReturn(employee);

        Map<String, Employee> expectedSortedMap = new LinkedHashMap<>();
        expectedSortedMap.put(employee10.getFullName(), employee10);
        expectedSortedMap.put(employee11.getFullName(), employee11);
        expectedSortedMap.put(employee12.getFullName(), employee12);
        expectedSortedMap.put(employee13.getFullName(), employee13);
        expectedSortedMap.put(employee14.getFullName(), employee14);

        Map<String, Employee> sortedDepartmentMap = departmentService.getSortedDepartment();

        List<String> expectedOrder = new ArrayList<>(expectedSortedMap.keySet());
        List<String> actualOrder = new ArrayList<>(sortedDepartmentMap.keySet());

        Assertions.assertEquals(expectedOrder, actualOrder);
    }


    @Test
    public void shouldWrongSortDepartment() throws EmployeeNotFoundException {
        Employee employee10 = new Employee("Dmitry", "Dddd", 100000, 1);
        Employee employee11 = new Employee("Dmitr", "Dddd", 120000, 2);
        Employee employee12 = new Employee("Dmit", "Dddd", 1100000, 3);
        Employee employee13 = new Employee("Dmi", "Dddd", 150000, 4);
        Employee employee14 = new Employee("Dm", "Dddd", 1040000, 5);

        Map<String, Employee> employee = new LinkedHashMap<>();
        employee.put(employee12.getFullName(), employee12);
        employee.put(employee11.getFullName(), employee11);
        employee.put(employee13.getFullName(), employee13);
        employee.put(employee10.getFullName(), employee10);
        employee.put(employee14.getFullName(), employee14);

        Mockito.when(employeeService.allEmployee()).thenReturn(employee);

        Map<String, Employee> expectedSortedMap = new LinkedHashMap<>();
        expectedSortedMap.put(employee11.getFullName(), employee11);
        expectedSortedMap.put(employee10.getFullName(), employee10);
        expectedSortedMap.put(employee12.getFullName(), employee12);
        expectedSortedMap.put(employee13.getFullName(), employee13);
        expectedSortedMap.put(employee14.getFullName(), employee14);

        Map<String, Employee> sortedDepartmentMap = departmentService.getSortedDepartment();

        List<String> expected = new ArrayList<>(expectedSortedMap.keySet());
        List<String> actual = new ArrayList<>(sortedDepartmentMap.keySet());

        Assertions.assertEquals(expected, actual);

    }
}
