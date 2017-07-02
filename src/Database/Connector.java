package Database;

import java.sql.*;

/**
 * Responsible for connecting with the database
 */
public class Connector {

    /**
     * statement to execute
     */
    private Statement statement;

    /**
     * connection with the database
     */
    private Connection connection;

    /**
     * connects to the database
     */
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

    /**
     * closes connection and result set
     * @param resultSet result set to close
     */
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

    /**
     * Getter for statement
     * @return statement of the connector
     */
    public Statement getStatement() {
        return statement;
    }

    /**
     * Setter for statement
     * @param statement new statement of the connector
     */
    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    /**
     * Getter for connection
     * @return connection of the connector
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Setter for connection
     * @param connection new connection of the connector
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
