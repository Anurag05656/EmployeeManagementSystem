package dao;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final SessionFactory sessionFactory;

    public EmployeeDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Employee employee) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.persist(employee);

            transaction.commit();

            System.out.println("Employee Saved Successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

    }

    @Override
    public Employee findById(int id) {

        try (Session session = sessionFactory.openSession()) {

            return session.get(Employee.class, id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Employee> findAll() {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery(
                    "SELECT e FROM Employee e LEFT JOIN FETCH e.department",
                    Employee.class
            ).list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public List<Employee> findBySalary(double salary) {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery(
                            "SELECT e FROM Employee e LEFT JOIN FETCH e.department WHERE e.salary >= :salary",
                            Employee.class
                    )
                    .setParameter("salary", salary)
                    .list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of();
    }

    @Override
    public void update(Employee employee) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            session.merge(employee);

            transaction.commit();

            System.out.println("Employee Updated Successfully.");

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {

        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);

            if (employee != null) {

                session.remove(employee);

                System.out.println("Employee Deleted Successfully.");

            } else {

                System.out.println("Employee Not Found.");

            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }

    }

}