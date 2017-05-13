package Controller;

import Database.Connector;

import java.sql.SQLException;
import java.sql.Statement;

public class ClientController {
    private Connector connector;

    public ClientController() {
        this.connector = new Connector();
    }

    public void createClient(String userFirstName, String userLastName, String userEmail, String userTelephone) {
        this.connector.connect();
        try {
            Statement statement = this.connector.getConnection().createStatement();

            String sql = "INSERT INTO client (first_name, last_name, email, telephone)" +
                    " VALUES ('" +
                    userFirstName +
                    "','" +
                    userLastName +
                    "','" +
                    userEmail +
                    "','" +
                    userTelephone +
                    "')";

            statement.executeUpdate(sql);
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
