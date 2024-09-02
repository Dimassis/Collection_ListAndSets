package Employee.Employees;

public class EmployeeAlreadyAddedException extends Exception {
    public EmployeeAlreadyAddedException(String message) {
        super(message);
    }
}
