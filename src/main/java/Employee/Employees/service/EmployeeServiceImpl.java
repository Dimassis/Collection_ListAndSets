package Employee.Employees.service;

import Employee.Employees.modul.Employee;
import Employee.Employees.Interfaces.EmployeeService;
import Employee.Employees.exception.EmployeeAlreadyAddedException;
import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.exception.EmployeeStorageIsFullException;
import Employee.Employees.exception.EmployeeWrongInputException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    static final int MAX_EMPLOYEES = 5;
    public static Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }

    @Override
    public String checkEmployee(String getFullName) throws EmployeeWrongInputException {
        if (StringUtils.containsAny(getFullName, "0123456789!@#$%^&*()_+={}[];:'\\\"<>,./?\\\\|`~\\\"")
                || StringUtils.countMatches(getFullName, " ") != 1) {
            throw new EmployeeWrongInputException("Некорректный ввод");
        }
        String[] split = getFullName.split(" ");
        Arrays.setAll(split, i -> StringUtils.capitalize(split[i]));
        return split[0] + " " + split[1];
    }

    @Override
    public void addEmployee(String firstName, String lastName, int salary, int deportmentId) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException, EmployeeWrongInputException {
        if (employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("У вас максимальное колво сотрудников");
        }
        Employee employee = new Employee(firstName, lastName, salary, deportmentId);
        String isVerifiedEmployee = checkEmployee(employee.getFullName());
        if (employees.containsKey(isVerifiedEmployee)) {
            throw new EmployeeAlreadyAddedException("Сотруник уже добавлен");
        }

        employees.put(isVerifiedEmployee, employee);
    }

    @Override
    public void removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, 0, 0);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employees.remove(employee.getFullName());
    }

    @Override
    public void findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName, 0, 0);
        if (employee.getFullName() == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
        employee.toString();
    }

    @Override
    public Map<String, Employee> allEmployee() throws EmployeeNotFoundException {
        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException("Нет сотрудников");
        }
        return employees;
    }
}