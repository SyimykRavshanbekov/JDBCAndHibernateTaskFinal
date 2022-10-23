package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.model.User;
import peaksoft.util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

   private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery(
                    "create table if not exists users(" +
                            "id serial primary key," +
                            "name varchar(50)  ," +
                            "last_name varchar(50) ," +
                            "age int not null );").executeUpdate();
            session.getTransaction().commit();
            session.close();
            System.out.println("Table created successfully!");
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropUsersTable() {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.createNativeQuery(
                    "drop table if exists users;").executeUpdate();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void saveUser(String name, String lastname, byte age) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.save(new User(name,lastname,age));

            session.getTransaction().commit();
            session.close();
        } catch (Exception exception) {
            System.out.println("error saveUsers!!!");
        }


    }

    @Override
    public void removeUserById(long id) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<User> getAllUsers() {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List users = session.createQuery("select u from User u").list();
            session.getTransaction().commit();
            session.close();
            return users;
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            session.createSQLQuery("truncate table users;").executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
