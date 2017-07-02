package Controller;

import Database.Connector;
import Dictionary.UserRoleDictionary;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with User class
 */
public class UserController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public UserController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new user in the database
     * @param userNick nick of the new user
     * @param userPassword password of the new user
     * @param userFirstName first name of the new user
     * @param userLastName last name of the new user
     * @param userEmail email of the new user
     * @param userTelephone telephone number of the new user
     * @param userRole role of the new user
     */
    public void createClient(String userNick, String userPassword, String userFirstName, String userLastName, String userEmail, String userTelephone, UserRoleDictionary userRole) {
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
                    userRole.toString() +
                    "')";

            statement.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * updates user in the database
     * @param id id of the user to be updated
     * @param nick new nick of the user
     * @param firstName new first name of the user
     * @param lastName new last name of the user
     * @param userRole new role of the user
     */
    public void updateUser(int id, String nick, String firstName, String lastName, String userRole) {
        this.connector.connect();
        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "UPDATE aquapark_user SET first_name='" +
                    firstName + "', last_name='" +
                    lastName + "', nick='" +
                    nick + "', role_user='" +
                    userRole + "' WHERE id = " + id;

            statement.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all users in the database
     * @return list of all users in the database
     */
    public ArrayList<User> getUsers() {
        this.connector.connect();
        ArrayList<User> users = new ArrayList<User>();

        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "SELECT * FROM aquapark_user";
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return null;
            }

            do {
                User user = new User();
                user = this.setUserFields(user, resultSet, false);
                users.add(user);
            } while (resultSet.next());

            this.connector.closeConnection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Looks for the user with given id
     * @param id id of the user
     * @return desired User object or null if the user couldn't be found
     */
    public User findById(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM aquapark_user WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                User user = new User();
                user = this.setUserFields(user, rs, false);
                this.connector.closeConnection(rs);
                return user;
            } else {
                this.connector.closeConnection(rs);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.connector.closeConnection(null);
        }
        return null;
    }

    /**
     * Looks for the user with given parameter
     * @param fieldName parameter name
     * @param fieldValue parameter value
     * @return desired User object or null if the user couldn't be found
     */
    public ArrayList<User> findBy(String fieldName, Object fieldValue) {
        this.connector.connect();
        ArrayList<User> users = new ArrayList<User>();
        String sql = "";

        try {
            Statement statement = this.connector.getConnection().createStatement();

            if (fieldValue instanceof String) {
                sql = "SELECT * FROM aquapark_user WHERE " + fieldName + " LIKE '" + fieldValue + "'";
            } else {
                sql = "SELECT * FROM aquapark_user WHERE " + fieldName + " = '" + fieldValue + "'";
            }

            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return null;
            }

            do {
                User user = new User();
                user = this.setUserFields(user, resultSet, false);
                users.add(user);
            } while (resultSet.next());

            this.connector.closeConnection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Removes user from the database
     * @param user user to be removed
     */
    public boolean deleteUser(User user) {
        this.connector.connect();

        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "DELETE FROM aquapark_user WHERE id = " + user.getId();
            statement.executeUpdate(sql);
            this.connector.getConnection().commit();
            this.connector.closeConnection(null);

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Checks if there is a user with given nick in the database
     * @param nickName nick to check
     * @return true if there is an account with given nick
     */
    public boolean checkIfAccountExist(String nickName) {
        this.connector.connect();

        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "SELECT * FROM aquapark_user WHERE nick LIKE '" + nickName + "'";

            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return false;
            }

            this.connector.closeConnection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * Tries to log in
     * @param nick nick of the user
     * @param password password of the user
     * @return User object that has logged in or null if the login action was unsuccessful
     */
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

            user = this.setUserFields(user, resultSet, false);
            this.connector.closeConnection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * Getter for Role objects based on the name of the role
     * @param userRole nameof the role
     * @return UserRoleDictionary enum object
     */
    public UserRoleDictionary prepareUserRole(String userRole) {
        switch (userRole) {
            case "ROLE_USER":
                return UserRoleDictionary.ROLE_USER;
            case "ROLE_ADMIN":
                return UserRoleDictionary.ROLE_ADMIN;
            case "ROLE_EMPLOYEE":
                return UserRoleDictionary.ROLE_EMPLOYEE;
            case "ROLE_MANAGER":
                return  UserRoleDictionary.ROLE_MANAGER;
            default:
                return null;
        }
    }

    /**
     * Fills fields of the User object based on results from ResultSet
     * @param user user to fill
     * @param resultSet result set
     * @return filled User object
     */
    public User setUserFields(User user, ResultSet resultSet, boolean isAquaparkUserId) {
        try {
            if (isAquaparkUserId) {
                user.setId(resultSet.getInt("aquapark_user_id"));
            } else {
                user.setId(resultSet.getInt("id"));
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

    public List<User> getUsersWithPass() {
        this.connector.connect();
        ArrayList<User> users = new ArrayList<User>();

        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "SELECT DISTINCT(pass.aquapark_user_id) FROM pass, aquapark_user " +
                    "WHERE pass.aquapark_user_id IS NOT NULL " +
                    "AND aquapark_user.id = pass.aquapark_user_id";
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return null;
            }

            do {
                users.addAll(findBy("id", resultSet.getInt("aquapark_user_id")));
            } while (resultSet.next());

            this.connector.closeConnection(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
