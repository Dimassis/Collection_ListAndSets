package Employee.Employees.Interfaces;

import Employee.Employees.Employee;
import Employee.Employees.exception.EmployeeAlreadyAddedException;
import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.exception.EmployeeStorageIsFullException;

import java.util.Map;

public interface EmployeeService {
    void addEmployee(String firstName, String lastName, int salary, int deportmentId) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException;
    void removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException;
    void findEmployee(String firstName, String lastName) throws EmployeeNotFoundException;
    Map<String, Employee> allEmployee() throws EmployeeNotFoundException;
}
