package Employee.Employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {
    static final int MAX_EMPLOYEES = 5;
    private Map<Employee, EmployeeBook> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public void addEmployee(String firstName, String lastName, int salary, int deportmentId) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException  {
        if(employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("У вас максимальное колво сотрудников");
        }
        Employee employeeKey = new Employee(firstName, lastName);
        if(employees.containsKey(employeeKey)) {
            throw new EmployeeAlreadyAddedException("Сотруник уже добавлен");
        }
        EmployeeBook employeeBook = new EmployeeBook(salary, deportmentId);
        employees.put(employeeKey, employeeBook);
    }

    public void removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employeeKey = new Employee(firstName, lastName);
        if(employees.remove(employeeKey) == null) {
            throw new EmployeeNotFoundException("Сотрудник, которого вы хотите удалить, не найден");
        }
    }

    public void findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employeeKey = new Employee(firstName, lastName);
        EmployeeBook employee = employees.get(employeeKey);
        if(employees == null) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }

    public Map<Employee, EmployeeBook> allEmployee()  throws EmployeeNotFoundException {
        if(employees.isEmpty()) {
            throw new EmployeeNotFoundException("Нет сотрудников");
        }
        return employees;
        }
}
