package main;

import entity.Department;
import entity.Employee;
import exception.DepartmentNotFoundException;
import exception.EmployeeNotFoundException;
import service.DepartmentService;
import service.DepartmentServiceImpl;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        EmployeeService employeeService = new EmployeeServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();

        int choice;

        do {

            System.out.println("\n=========================================");
            System.out.println("      EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee by ID");
            System.out.println("3. View All Employees");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Filter Employees by Min Salary");
            System.out.println("7. Department Management");
            System.out.println("8. Exit");
            System.out.print("\nEnter Choice : ");

            choice = sc.nextInt();

            switch (choice) {

                case 1: {

                    sc.nextLine();

                    System.out.print("Enter Name   : ");
                    String name = sc.nextLine();

                    System.out.print("Enter Email  : ");
                    String email = sc.nextLine();

                    System.out.print("Enter Salary : ");
                    double salary = sc.nextDouble();

                    Employee employee = new Employee(name, email, salary);

                    List<Department> depts = departmentService.getAllDepartments();
                    if (!depts.isEmpty()) {
                        System.out.println("\nAvailable Departments:");
                        for (Department d : depts) {
                            System.out.println("  [" + d.getDeptId() + "] " + d.getDeptName()
                                    + " - " + (d.getLocation() != null ? d.getLocation() : "N/A"));
                        }
                        System.out.print("Enter Department ID (0 to skip) : ");
                        int deptId = sc.nextInt();
                        if (deptId != 0) {
                            try {
                                Department dept = departmentService.getDepartment(deptId);
                                employee.setDepartment(dept);
                            } catch (DepartmentNotFoundException e) {
                                System.out.println("Warning: " + e.getMessage() + " Employee saved without department.");
                            }
                        }
                    }

                    try {
                        employeeService.saveEmployee(employee);
                        System.out.println("Employee saved successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Validation Error: " + e.getMessage());
                    }

                    break;
                }

                case 2: {

                    System.out.print("Enter Employee ID : ");
                    int id = sc.nextInt();

                    try {
                        Employee emp = employeeService.getEmployee(id);
                        System.out.println(emp);
                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                }

                case 3: {

                    List<Employee> employees = employeeService.getAllEmployees();

                    if (employees.isEmpty()) {
                        System.out.println("No Employees Found.");
                    } else {
                        System.out.println("\nTotal Employees: " + employees.size());
                        for (Employee e : employees) {
                            System.out.println(e);
                        }
                    }

                    break;
                }

                case 4: {

                    System.out.print("Enter Employee ID to Update : ");
                    int updateId = sc.nextInt();

                    try {
                        Employee updateEmployee = employeeService.getEmployee(updateId);

                        sc.nextLine();

                        System.out.print("Enter New Name  : ");
                        updateEmployee.setName(sc.nextLine());

                        System.out.print("Enter New Email : ");
                        updateEmployee.setEmail(sc.nextLine());

                        System.out.print("Enter New Salary : ");
                        updateEmployee.setSalary(sc.nextDouble());

                        try {
                            employeeService.updateEmployee(updateEmployee);
                            System.out.println("Employee updated successfully!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Validation Error: " + e.getMessage());
                        }

                    } catch (EmployeeNotFoundException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                }

                case 5: {

                    System.out.print("Enter Employee ID to Delete : ");
                    int deleteId = sc.nextInt();

                    employeeService.deleteEmployee(deleteId);

                    break;
                }

                case 6: {

                    System.out.print("Enter Minimum Salary : ");
                    double minSalary = sc.nextDouble();

                    List<Employee> filtered = employeeService.getEmployeesBySalary(minSalary);

                    if (filtered.isEmpty()) {
                        System.out.println("No employees found with salary >= " + minSalary);
                    } else {
                        System.out.println("\nEmployees with salary >= " + minSalary + " :");
                        for (Employee e : filtered) {
                            System.out.println(e);
                        }
                    }

                    break;
                }

                case 7: {

                    System.out.println("\n--- Department Management ---");
                    System.out.println("1. Add Department");
                    System.out.println("2. View All Departments");
                    System.out.println("3. View Department by ID");
                    System.out.print("Enter choice : ");
                    int deptChoice = sc.nextInt();

                    switch (deptChoice) {

                        case 1: {
                            sc.nextLine();
                            System.out.print("Enter Department Name     : ");
                            String deptName = sc.nextLine();
                            System.out.print("Enter Department Location : ");
                            String location = sc.nextLine();
                            Department dept = new Department(deptName, location);
                            departmentService.saveDepartment(dept);
                            System.out.println("Department saved successfully!");
                            break;
                        }

                        case 2: {
                            List<Department> depts = departmentService.getAllDepartments();
                            if (depts.isEmpty()) {
                                System.out.println("No Departments Found.");
                            } else {
                                System.out.println("\nAll Departments:");
                                for (Department d : depts) {
                                    System.out.println("  " + d);
                                }
                            }
                            break;
                        }

                        case 3: {
                            System.out.print("Enter Department ID : ");
                            int deptId = sc.nextInt();
                            try {
                                Department dept = departmentService.getDepartment(deptId);
                                System.out.println(dept);
                            } catch (DepartmentNotFoundException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                            break;
                        }

                        default:
                            System.out.println("Invalid department menu choice.");
                    }

                    break;
                }

                case 8:
                    System.out.println("Thank You! Goodbye.");
                    break;

                default:
                    System.out.println("Invalid Choice. Please try again.");
            }

        } while (choice != 8);

        sc.close();

        HibernateUtil.shutdown();

    }

}