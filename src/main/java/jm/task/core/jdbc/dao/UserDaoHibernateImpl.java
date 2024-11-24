package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = Util.getHibernetConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(25)," +
                    "lastName VARCHAR(25)," +
                    "age TINYINT )").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getHibernetConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User addUser = new User(name, lastName, age);

        try (Session session = Util.getHibernetConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(addUser);
            transaction.commit();
            System.out.println("User с именем — " + addUser.getName() + " добавлен в базу данных");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getHibernetConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            User userToDelete = session.get(User.class, id);

            if (userToDelete != null) {
                session.delete(userToDelete);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUsers = null;

        try (Session session = Util.getHibernetConnection().openSession()) {
            listUsers = session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getHibernetConnection().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
