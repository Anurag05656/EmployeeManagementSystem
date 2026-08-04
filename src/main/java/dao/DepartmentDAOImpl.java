package dao;

import entity.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {

    private final SessionFactory sessionFactory;

    public DepartmentDAOImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public void save(Department department) {

        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()){

            transaction = session.beginTransaction();

            session.persist(department);

            transaction.commit();

            System.out.println("Department Saved Successfully.");

        }catch(Exception e){

            if(transaction!=null)
                transaction.rollback();

            e.printStackTrace();

        }

    }

    @Override
    public Department findById(int id) {

        try (Session session = sessionFactory.openSession()) {

            return session.get(Department.class, id);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Department> findAll() {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery(
                    "FROM Department",
                    Department.class
            ).list();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return List.of();
    }

}