package Employee.Employees.contoller;

import Employee.Employees.exception.EmployeeAlreadyAddedException;
import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.exception.EmployeeStorageIsFullException;
import Employee.Employees.exception.EmployeeWrongInputException;
import Employee.Employees.modul.Employee;
import Employee.Employees.service.DepartmentServiceImpl;
import Employee.Employees.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeServiceImpl employeeService;
    private final DepartmentServiceImpl departmentService;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl, DepartmentServiceImpl departmentService) {
        this.employeeService = employeeServiceImpl;
        this.departmentService = departmentService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam(value = "firstName") String firstName,
                              @RequestParam(value = "lastName") String lastName,
                              @RequestParam(value = "salary") int salary,
                              @RequestParam(value = "depId") int depId) {
        try {
            employeeService.addEmployee(firstName, lastName, salary, depId);
            return "Сотрудник " + firstName + " " + lastName + " добавлен";
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException | EmployeeWrongInputException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "lastName") String lastName) {
        try {
            employeeService.removeEmployee(firstName, lastName);
            return "Сотрудник " + firstName + " " + lastName + " удален";
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/find")
    public String findEmployee(@RequestParam(value = "firstName") String firstName,
                               @RequestParam(value = "lastName") String lastName) {
        try {
            employeeService.findEmployee(firstName, lastName);
            return "Сотрудник " + firstName + " " + lastName + " найден";
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/all")
    public String allEmployee() {
        try {
            return employeeService.allEmployee().toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/departments/{id}/salary/max")
    public String maxSalaryDeportment(@PathVariable("id") int deportment) throws EmployeeNotFoundException {
        return "Максимальная зп у сотрудника из " + deportment + " отдела у "
                + departmentService.maxSalaryDepartment(deportment);
    }

    @GetMapping(path = "/departments/{id}/salary/min")
    public String minSalaryDeportment(@PathVariable("id") int deportment) throws EmployeeNotFoundException {
        return departmentService.minSalaryDepartment(deportment);
    }

    @GetMapping(path = "/departments/{id}/salary/sum")
    public int salarySumDeportment(@PathVariable("id") int deportment) throws EmployeeNotFoundException {
        return departmentService.getSumSalaryDepartment(deportment);
    }

    @GetMapping(path = "/department/{id}/employees")
    public Map<String, Employee> getEmployeesDepartment(@PathVariable("id") int department) throws EmployeeNotFoundException {
        return departmentService.getEmployeesDepartment(department);
    }

    @GetMapping(path = "/departments/allSort")
    public Map<String, Employee> getSortedDeportment() throws EmployeeNotFoundException {
        return departmentService.getSortedDepartment();
    }

}