package service;

import dao.DepartmentDAO;
import dao.DepartmentDAOImpl;
import entity.Department;
import exception.DepartmentNotFoundException;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDAO departmentDAO;

    public DepartmentServiceImpl() {

        departmentDAO = new DepartmentDAOImpl();

    }

    @Override
    public void saveDepartment(Department department) {

        departmentDAO.save(department);

    }

    @Override
    public Department getDepartment(int id) {

        Department department =
                departmentDAO.findById(id);

        if (department == null) {

            throw new DepartmentNotFoundException(
                    "Department not found."
            );

        }

        return department;

    }

    @Override
    public List<Department> getAllDepartments() {

        return departmentDAO.findAll();

    }

}