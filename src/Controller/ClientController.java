package Controller;

import Database.Connector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ClientController {
    private Connector connector;

    public ClientController() {
        this.connector = new Connector();
    }

    public void createClient(String userNick, String userPassword, String userFirstName, String userLastName, String userEmail, String userTelephone) {
        this.connector.connect();
        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "INSERT INTO client (first_name, last_name, email, telephone, nick, password)" +
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

            String sql = "SELECT * FROM client WHERE nick LIKE '" + nickName + "'";

            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
