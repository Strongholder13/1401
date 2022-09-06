package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao userDaoHiper = new UserDaoHibernateImpl();


    public void createUsersTable() {
        userDaoHiper.createUsersTable();
        }

    public void dropUsersTable() {
        userDaoHiper.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHiper.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHiper.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDaoHiper.getAllUsers();
    }

    public void cleanUsersTable() {
        userDaoHiper.cleanUsersTable();
    }
}