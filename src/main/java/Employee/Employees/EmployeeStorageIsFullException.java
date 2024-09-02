package Employee.Employees;

public class EmployeeStorageIsFullException extends Exception {
    public EmployeeStorageIsFullException(String message) {
        super(message);
    }
}
