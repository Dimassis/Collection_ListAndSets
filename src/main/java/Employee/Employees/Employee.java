package Employee.Employees;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final int salary;
    private final int deportmentId;

    public Employee(String firstName, String lastName, int salary, int deportmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.deportmentId = deportmentId;
    }

    public int getSalary() {
        return salary;
    }

    public int getDeportmentId() {
        return deportmentId;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employees = (Employee) o;
        return Objects.equals(firstName, employees.firstName) && Objects.equals(lastName, employees.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return
                ", salary=" + salary +
                ", deportmentId=" + deportmentId;
    }
}
