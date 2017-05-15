package Controller;

import Database.Connector;
import Dictionary.UserRoleDictionary;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserController {
    private Connector connector;

    public UserController() {
        this.connector = new Connector();
    }

    public void createClient(String userNick, String userPassword, String userFirstName, String userLastName, String userEmail, String userTelephone) {
        this.connector.connect();
        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "INSERT INTO aquapark_user (first_name, last_name, email, telephone, nick, password, role_user)" +
                    " VALUES ('" +
                    userFirstName +
                    "','" +
                    userLastName +
                    "','" +
                    userEmail +
                    "','" +
                    userTelephone +
                    "','" +
                    userNick +
                    "','" +
                    userPassword +
                    "','" +
                    UserRoleDictionary.ROLE_USER.toString() +
                    "')";

            statement.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public boolean checkIfAccountExist(String nickName) {
        this.connector.connect();

        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "SELECT * FROM aquapark_user WHERE nick LIKE '" + nickName + "'";

            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public User logInAction(String nick, String password) {
        this.connector.connect();

        User user = new User();
        try {
            Statement statement = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM aquapark_user WHERE nick LIKE '" + nick + "' AND password LIKE '" + password + "'";
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return null;
            }

            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setTelephone(resultSet.getString("telephone"));
            user.setNick(resultSet.getString("nick"));
            user.setPassword(resultSet.getString("password"));
            user.setRole(this.prepareUserRole(resultSet.getString("role_user")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public UserRoleDictionary prepareUserRole(String userRole) {
        switch (userRole) {
            case "ROLE_USER":
                return UserRoleDictionary.ROLE_USER;
            case "ROLE_ADMIN":
                return UserRoleDictionary.ROLE_ADMIN;
            case "ROLE_EMPLOYEE":
                return UserRoleDictionary.ROLE_EMPLOYEE;
            default:
                return null;
        }
    }
}
