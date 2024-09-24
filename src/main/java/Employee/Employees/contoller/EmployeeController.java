package Employee.Employees.contoller;

import Employee.Employees.*;
import Employee.Employees.exception.EmployeeAlreadyAddedException;
import Employee.Employees.exception.EmployeeNotFoundException;
import Employee.Employees.exception.EmployeeStorageIsFullException;
import Employee.Employees.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam(value = "firstName") String firstName,
                              @RequestParam(value = "lastName") String lastName,
                                @RequestParam(value = "salary") int salary,
                                @RequestParam(value = "depId") int depId) {
        try {
            employeeService.addEmployee(firstName, lastName , salary, depId);
            return "Сотрудник " + firstName + " " + lastName + " добавлен";
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/remove")
    public String removeEmployee(@RequestParam(value = "firstName") String firstName,
                                 @RequestParam(value = "lastName") String lastName) {
        try {
           Employee employee = new Employee(firstName, lastName, 0, 0);
            employeeService.removeEmployee(firstName, lastName);
            return "Сотрудник " + firstName + " " + lastName + " удален";
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    @GetMapping(path = "/find")
    public String findEmployee (@RequestParam(value = "firstName") String firstName,
                                    @RequestParam(value = "lastName") String lastName){
            try {
                employeeService.findEmployee(firstName, lastName);
                return "Сотрудник " + firstName + " " + lastName + " найден";
            } catch (EmployeeNotFoundException e) {
                return e.getMessage();
            }
        }

    @GetMapping(path = "/all")
    public String allEmployee (){
        try {
           return employeeService.allEmployee().toString();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

   @GetMapping(path = "/departments/max-salary")
    public String maxSalaryDeportment  (@RequestParam(value = "department") int deportment) {
       return "Максимальная зп у сотрудника из " + deportment + " отдела у "
               + employeeService.maxSalaryDepartment(deportment);
    }

    @GetMapping(path =  "/departments/min-salary")
    public String minSalaryDeportment  (@RequestParam(value = "department") int deportment) {
        return employeeService.minSalaryDepartment(deportment);
    }

    @GetMapping(path = "/departments/all")
    public Map<String, Employee> getEmployeesDepartment (@RequestParam(value = "department") int department) {
        return employeeService.getEmployeesDepartment(department);
        }

    @GetMapping(path = "/departments/allsort")
    public Map<String, Employee> getSortedDeportment() {
        return employeeService.getSortedDepartment();
    }
}