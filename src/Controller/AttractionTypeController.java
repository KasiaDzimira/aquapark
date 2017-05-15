package Controller;

import Database.Connector;
import Model.AttractionType;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<AttractionType> getAllAttractionTypes() {
        List<AttractionType> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction_type";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                AttractionType attractionType = new AttractionType(
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                );
                result.add(attractionType);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return result;
    }

    public AttractionType getAttractionTypeById(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction_type WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);


            if (rs.next()) {
                AttractionType attractionType = new AttractionType(
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                );
                attractionType.setId(rs.getInt("id"));
                return attractionType;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

    public void updateAttractionType(int id, String name, BigDecimal price) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE attraction_type SET name='" +
                    name + "', price=" +
                    price + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public void deleteAttractionType(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM attraction_type WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
