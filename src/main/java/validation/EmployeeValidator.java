package validation;

import entity.Employee;

public class EmployeeValidator {

    public static void validate(Employee employee) {

        if (employee == null) {

            throw new IllegalArgumentException(
                    "Employee cannot be null."
            );

        }

        if (employee.getName() == null ||
                employee.getName().trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Employee name cannot be empty."
            );

        }

        if (employee.getSalary() < 15000) {

            throw new IllegalArgumentException(
                    "Salary must be at least 15000."
            );

        }

        if (employee.getEmail() == null ||
                !employee.getEmail().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new IllegalArgumentException(
                    "Invalid Email Address."
            );

        }
    }
}