package Employee.Employees;

import java.util.Objects;

public class EmployeeBook {
    private final int salary;
    private final int deportmentId;

    public EmployeeBook(int salary, int deportmentId) {
        this.salary = salary;
        this.deportmentId = deportmentId;
    }

    public int getSalary() {
        return salary;
    }

    public int getDeportmentId() {
        return deportmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeBook that = (EmployeeBook) o;
        return salary == that.salary && deportmentId == that.deportmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, deportmentId);
    }

    @Override
    public String toString() {
        return "Зарплата" + salary + " отел" + deportmentId;
    }
}
