package Employee.Employees.Interfaces;

import Employee.Employees.modul.Employee;
import Employee.Employees.exception.EmployeeAlreadyAddedException;
import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.exception.EmployeeStorageIsFullException;
import Employee.Employees.exception.EmployeeWrongInputException;

import java.util.Map;

public interface EmployeeService {

    String checkEmployee(String getFullName) throws EmployeeWrongInputException;

    void addEmployee(String firstName, String lastName, int salary, int deportmentId) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException, EmployeeWrongInputException;
    void removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException;
    void findEmployee(String firstName, String lastName) throws EmployeeNotFoundException;
    Map<String, Employee> allEmployee() throws EmployeeNotFoundException;


}
