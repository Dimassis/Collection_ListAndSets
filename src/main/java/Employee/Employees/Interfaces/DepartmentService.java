package Employee.Employees.Interfaces;

import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.modul.Employee;

import java.util.Map;

public interface DepartmentService {
    String maxSalaryDepartment(int depId) throws EmployeeNotFoundException;

    String minSalaryDepartment(int depId) throws EmployeeNotFoundException;

    int getSumSalaryDepartment(int depId) throws EmployeeNotFoundException;

    Map<String, Employee> getEmployeesDepartment(int depId) throws EmployeeNotFoundException;

    Map<String, Employee> getSortedDepartment() throws EmployeeNotFoundException;
}
