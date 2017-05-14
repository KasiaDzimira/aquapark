package Controller;

import Database.Connector;
import Model.Attraction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Attraction> getAllAttractions() {
        List<Attraction> result = new ArrayList<>();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Attraction attraction = new Attraction(
                        rs.getInt("status"),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                attraction.setId(rs.getInt("id"));
                result.add(attraction);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return result;
    }

    public Attraction getAttractionById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);


            if (rs.next()) {
                AttractionTypeController attractionTypeController = new AttractionTypeController();
                Attraction attraction = new Attraction(
                        rs.getInt("status"),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                attraction.setId(rs.getInt("id"));
                return attraction;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

    public void updateAttraction(int id, int status, int attractionTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE attraction SET status=" +
                    status + ", attraction_type_id=" +
                    attractionTypeId + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public void deleteAttraction(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM attraction WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
