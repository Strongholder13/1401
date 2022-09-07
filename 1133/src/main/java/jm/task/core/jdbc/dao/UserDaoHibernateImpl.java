package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;




public class UserDaoHibernateImpl implements UserDao {

    private Session session = Util.getSessionFactory().openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(40), lastname VARCHAR(40), age INT)")
                    .executeUpdate();
            session.getTransaction().commit();

        } catch (HibernateException e) {

        }

    }

    @Override
    public void dropUsersTable() {
        try {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {

        }
    }

   @Override

    public void saveUser(String name, String lastName, byte age) {

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
        List<User> users ;
        session.beginTransaction();
        users = session.createQuery("select i from User i").list();
        session.getTransaction().commit();
        return users;
    }

    @Override
    public void cleanUsersTable() {

        session.beginTransaction();
        session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
        session.getTransaction().commit();
    }
}
