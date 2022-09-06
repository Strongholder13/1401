package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {

    //private SessionFactory sessionFactory = getSessionFactory();
    private Session session = Util.getSessionFactory().openSession();
    private Transaction transaction;
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS user (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(40), Lastname VARCHAR(40), Age INT)")
                    .executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {

        }

    }

    @Override
    public void dropUsersTable() {
        try {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {

        }
    }

   @Override

    public void saveUser(String name, String lastName, byte age) {

            //User user = new User(name, lastName, age) ;
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();


    }


    @Override
    public void removeUserById(long id) {

            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

            session.beginTransaction();
            users = (List<User>) session.createNativeQuery("from users", User.class);
            session.getTransaction().commit();


        return users;
    }

    @Override
    public void cleanUsersTable() {

        session.beginTransaction();
        session.createNativeQuery("TRUNCATE TABLE users", User.class).executeUpdate();
        session.getTransaction().commit();
    }
}
