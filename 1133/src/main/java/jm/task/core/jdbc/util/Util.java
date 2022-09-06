package jm.task.core.jdbc.util;


import com.mysql.cj.jdbc.Driver;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/pp101";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Bdfyxbr9";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static SessionFactory factory;

    public static Connection getConnection (){
        try {
            Driver drive = new Driver();
            DriverManager.registerDriver(drive);
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//    public static SessionFactory getSessionFactory() {
//        if (factory == null) {
//            try {
//                SessionFactory factory = new Configuration()
//
//                        .setProperty("hibernate.connection.driver_class", DRIVER)
//                        .setProperty("hibernate.connection.url", URL)
//                        .setProperty("hibernate.connection.username", USERNAME)
//                        .setProperty("hibernate.connection.password", PASSWORD)
//                        //.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect")
//                        .setProperty("hibernate.current_session_context_class", "thread")
//                        // .setProperty("hibernate.hbm2ddl.auto", "")
//
//                       // .setProperty("hibernate.hbm2ddl.auto", "none")
//
//                        .addAnnotatedClass(User.class)
//                        .buildSessionFactory();
//                System.out.println("connection ok");
//                return factory;
//            } catch (HibernateException var3) {
//                System.out.println("-------------------------------");
//                var3.printStackTrace();
//            }
//
//        } return factory;
//    }

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration()
                        .setProperty(Environment.URL, URL)
                        .setProperty(Environment.USER, USERNAME)
                        .setProperty(Environment.PASS, PASSWORD)
                       // .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                        .setProperty(Environment.HBM2DDL_AUTO, "update")
                        .setProperty(Environment.SHOW_SQL, "true")
                        .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                        .addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("connection ok");
            } catch(HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
