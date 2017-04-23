package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

    public static void main(String[] args) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(
                    DBContract.HOST+DBContract.DB_NAME,
                    DBContract.USERNAME,
                    DBContract.PASSWORD
            );

            System.out.println("Database connected");
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }
}
