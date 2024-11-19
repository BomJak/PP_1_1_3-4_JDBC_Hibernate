package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();

        user.saveUser("Иван", "Иванов", (byte) 55);
        user.saveUser("Борис", "Неиванов", (byte) 14);
        user.saveUser("Виталий", "Неборисов", (byte) 88);
        user.saveUser("Олег", "Тикток", (byte) 44);

        List<User> arr = user.getAllUsers();
        for (User u : arr) {
            System.out.println(u.toString());
        }
        user.cleanUsersTable();

        user.dropUsersTable();
    }
}
