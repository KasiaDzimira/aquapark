package Database;

import java.sql.*;
import java.sql.Driver;

public class Connector {
    private Statement statement;
    private Connection connection;

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    DBContract.HOST+DBContract.DB_NAME,
                    DBContract.USERNAME,
                    DBContract.PASSWORD
            );

            this.connection.setAutoCommit(false);
            this.statement = this.connection.createStatement();
        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            this.statement.close();
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
