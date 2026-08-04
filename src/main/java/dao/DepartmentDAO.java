package dao;

import entity.Department;

import java.util.List;

public interface DepartmentDAO {

    void save(Department department);

    Department findById(int id);

    List<Department> findAll();

}