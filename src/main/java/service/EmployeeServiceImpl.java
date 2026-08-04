package service;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import entity.Employee;
import validation.EmployeeValidator;
import exception.EmployeeNotFoundException;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl() {
        employeeDAO = new EmployeeDAOImpl();
    }

    @Override
    public void saveEmployee(Employee employee) {

        EmployeeValidator.validate(employee);

        employeeDAO.save(employee);

    }

    @Override
    public Employee getEmployee(int id) {

        Employee employee = employeeDAO.findById(id);

        if (employee == null) {

            throw new EmployeeNotFoundException(
                    "Employee ID " + id + " not found."
            );

        }

        return employee;

    }

    @Override
    public List<Employee> getAllEmployees() {

        return employeeDAO.findAll();

    }

    @Override
    public List<Employee> getEmployeesBySalary(double minSalary) {

        return employeeDAO.findBySalary(minSalary);

    }

    @Override
    public void updateEmployee(Employee employee) {

        EmployeeValidator.validate(employee);

        employeeDAO.update(employee);

    }

    @Override
    public void deleteEmployee(int id) {

        employeeDAO.delete(id);

    }

}