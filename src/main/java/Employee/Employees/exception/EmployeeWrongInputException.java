package Employee.Employees.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class EmployeeWrongInputException extends Exception {
    public EmployeeWrongInputException(String message) {
        super(message);
    }
}
