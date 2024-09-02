package Employee.Employees;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public String addEmployee(@RequestParam(value = "firstName") String firstName,
                              @RequestParam(value = "lastName") String lastName) {
        try {
            employeeService.addEmployee(firstName, lastName);
            return "Сотрудник " + firstName + " " + lastName + " добавлен";
        } catch (EmployeeAlreadyAddedException | EmployeeStorageIsFullException e) {
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
           return employeeService.allEmployee();
        } catch (EmployeeNotFoundException e) {
            return e.getMessage();
        }
    }

    }
