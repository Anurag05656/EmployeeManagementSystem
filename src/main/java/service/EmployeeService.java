package service;

import entity.Employee;

import java.util.List;

public interface EmployeeService {

    void saveEmployee(Employee employee);

    Employee getEmployee(int id);

    List<Employee> getAllEmployees();

    List<Employee> getEmployeesBySalary(double minSalary);

    void updateEmployee(Employee employee);

    void deleteEmployee(int id);

}