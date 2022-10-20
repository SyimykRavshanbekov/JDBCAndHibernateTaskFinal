package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String create = "create table users(id serial primary key, name varchar(30), last_name varchar(30), age int);";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(create);
            System.out.println("Table created successfully");
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public void dropUsersTable() {
        String drop = "drop table users";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
            statement.executeUpdate(drop);
            System.out.println("Table drop successfully");
        } catch (SQLException e) {
            System.out.println("error");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
     String save = "insert into users(name, last_name, age) values (?, ?, ?);";

     try(Connection connection = Util.getConnection();
         PreparedStatement st = connection.prepareStatement(save)) {
         st.setString(1, name);
         st.setString(2, lastName);
         st.setInt(3, age);
         st.execute();
         System.out.println("User saved successfully");
     } catch (SQLException e) {
         System.out.println("User not saved");
     }

    }

    public void removeUserById(long id) {
        String remove = "delete from users where id = ?;";

        try(Connection connection = Util.getConnection();
            PreparedStatement st = connection.prepareStatement(remove)) {
            st.setLong(1, id);
            st.executeUpdate();
            System.out.println("User deleted successfully");
        } catch (SQLException e) {
            System.out.println("User not deleted");
        }
    }

    public List<User> getAllUsers() {
        String getAll = "select * from users";
        ArrayList<User> list = new ArrayList<>();

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAll)) {
            while(resultSet.next()){
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setAge((byte)resultSet.getInt("age"));
                list.add(user);
            }

            return list;
        } catch (SQLException e) {
            System.out.println("User not deleted");
        }

        return list;
    }

    public void cleanUsersTable() {
        String clean = "truncate table users";

        try(Connection connection = Util.getConnection();
            Statement statement = connection.createStatement()) {
           statement.executeUpdate(clean);
            System.out.println("Table users clean successfully");
        } catch (SQLException e) {
            System.out.println("User not deleted");
        }
    }
}