package Controller;

import Database.Connector;
import Model.AttractionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with Attraction class
 */
public class AttractionTypeController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public AttractionTypeController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new attraction type in the database
     * @param name name of the new attraction type
     */
    public void createAttractionType(String name) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO attraction_type (name) VALUES ('" +
                    name + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all attraction types in the database
     * @return list of all attraction types in the database
     */
    public List<AttractionType> getAllAttractionTypes() {
        List<AttractionType> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction_type";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                AttractionType attractionType = new AttractionType(
                        rs.getString("name")
                );
                attractionType.setId(rs.getInt("id"));
                result.add(attractionType);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return result;
    }

    /**
     * Looks for the attraction type with given id
     * @param id id of the attraction type
     * @return desired AttractionType object or null if the attraction type couldn't be found
     */
    public AttractionType getAttractionTypeById(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction_type WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);


            if (rs.next()) {
                AttractionType attractionType = new AttractionType(
                        rs.getString("name")
                );
                attractionType.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return attractionType;
            } else {
                this.connector.closeConnection(null);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return null;
    }

    /**
     * Looks for the attraction type with given name
     * @param name name of the attraction type
     * @return desired AttractionType object or null if the attraction type couldn't be found
     */
    public AttractionType getAttractionTypeByName(String name) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction_type WHERE name='" + name + "'";
            ResultSet rs = st.executeQuery(sql);


            if (rs.next()) {
                AttractionType attractionType = new AttractionType(
                        rs.getString("name")
                );
                attractionType.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return attractionType;
            } else {
                this.connector.closeConnection(null);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return null;
    }

    /**
     * Updates attraction type in the database
     * @param id id of the attraction type to update
     * @param name new name of the attraction type
     */
    public void updateAttractionType(int id, String name) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE attraction_type SET name='" +
                    name + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes attraction type from the database
     * @param id id of the attraction type to be removed
     */
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
