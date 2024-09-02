package Employee.Employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeService {
    static final int MAX_EMPLOYEES = 5;
    ArrayList<Employees> employees = new ArrayList<Employees>();

    public EmployeeService(ArrayList<Employees> employees) {
        this.employees = employees;
    }

    public void addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException  {
        if(checkEmployee(firstName, lastName)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен");
        } else if(employees.size() >= MAX_EMPLOYEES) {
            throw new EmployeeStorageIsFullException("Превышено максимальное количество сотрудников");
        }
        else {
            employees.add(new Employees(firstName, lastName));
        }
    }

    public void removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        if(!checkEmployee(firstName, lastName)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        } else {
            for (int i = 0; i < employees.size(); i++) {
                if(employees.get(i).getFirstName().equals(firstName) && employees.get(i).getLastName().equals(lastName)) {
                    employees.remove(i);
                }
            }
        }
    }

    public void findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        if(!checkEmployee(firstName, lastName)) {
            throw new EmployeeNotFoundException("Сотрудник не найден");
        }
    }
    public String allEmployee() throws EmployeeNotFoundException {
        if(employees.size() <= 0) {
            throw new EmployeeNotFoundException("Нет сотрудников");
        } else {
            return employees.toString();
            }
        }

    public boolean checkEmployee(String firstName, String lastName) {
        for(Employees employee : employees) {
            if(employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

}
