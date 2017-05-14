package Controller;

import Database.Connector;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Statement;

public class AttractionTypeController {
    private Connector connector;

    public AttractionTypeController() {
        this.connector = new Connector();
    }

    public void createAttractionType(String name, BigDecimal price) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO attraction_type (name, price) VALUES ('" +
                    name + "'," +
                    price + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
