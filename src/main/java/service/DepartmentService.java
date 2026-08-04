package service;

import entity.Department;

import java.util.List;

public interface DepartmentService {

    void saveDepartment(Department department);

    Department getDepartment(int id);

    List<Department> getAllDepartments();

}