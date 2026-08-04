package entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private int deptId;

    @Column(name = "dept_name", nullable = false, unique = true)
    private String deptName;

    @Column(name = "location", length = 100)
    private String location;

    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Employee> employees = new ArrayList<>();

    public Department() {
    }

    public Department(String deptName) {
        this.deptName = deptName;
    }

    public Department(String deptName, String location) {
        this.deptName = deptName;
        this.location = location;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee){

        if(employee==null){

            throw new IllegalArgumentException(
                    "Employee cannot be null."
            );

        }

        employees.add(employee);

        employee.setDepartment(this);

    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", location='" + (location != null ? location : "N/A") + '\'' +
                '}';
    }
}