package dao;

import entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    void save(Employee employee);

    Employee findById(int id);

    List<Employee> findAll();

    List<Employee> findBySalary(double salary);

    void update(Employee employee);

    void delete(int id);

}