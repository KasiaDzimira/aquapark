package Controller;

import Database.Connector;

import java.sql.SQLException;
import java.sql.Statement;

public class AttractionController {
    private Connector connector;

    public AttractionController() {
        this.connector = new Connector();
    }

    public void createAttraction(int status, int attractionTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO attraction (status, attraction_type_id) VALUES (" +
                    status + "," +
                    attractionTypeId + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
