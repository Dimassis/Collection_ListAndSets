package Employee.Employees.service;

import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.modul.Employee;
import Employee.Employees.Interfaces.DepartmentService;
import Employee.Employees.Interfaces.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class DepartmentServiceImpl implements DepartmentService {

   private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String maxSalaryDepartment(int depId) throws EmployeeNotFoundException {
        return employeeService.allEmployee().values().stream()
                .filter(e -> e.getDeportmentId() == depId)
                .max(Comparator.comparingInt(Employee::getSalary))
                .map(employee -> String.valueOf(employee.getSalary()))
                .orElse("Нет сотдруников");
    }

    @Override
    public String minSalaryDepartment(int depId) throws EmployeeNotFoundException {
        return employeeService.allEmployee().values().stream()
                .filter(e -> e.getDeportmentId() == depId)
                .min(Comparator.comparingInt(Employee::getSalary))
                .map(employee -> String.valueOf(employee.getSalary()))
                .orElse("Нет сотрудников");
    }
    @Override
    public int getSumSalaryDepartment(int depId) throws EmployeeNotFoundException {
        return employeeService.allEmployee().values().stream()
                .filter(e -> e.getDeportmentId() == depId)
                .mapToInt(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<String, Employee> getEmployeesDepartment(int depId) throws EmployeeNotFoundException {

        return employeeService.allEmployee().values().stream()
                .filter(e -> e.getDeportmentId() == depId)
                .collect(Collectors.toMap(Employee::getFullName, e -> e));
    }

    @Override
    public Map<String, Employee> getSortedDepartment() throws EmployeeNotFoundException {
        return employeeService.allEmployee().entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getValue().getDeportmentId()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
