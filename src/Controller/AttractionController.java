package Controller;

import Database.Connector;
import Model.Attraction;
import Model.AttractionType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with Attraction class
 */
public class AttractionController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public AttractionController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new attraction in the database
     * @param name name of the new attraction
     * @param status status of the new attraction
     * @param attractionTypeId id of the attraction type of the new attraction
     */
    public void createAttraction(String name, boolean status, int attractionTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO attraction (name, status, attraction_type_id) VALUES ('" +
                    name + "', " +
                    status + "," +
                    attractionTypeId + ")";
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
     * Lists all attractions in the database
     * @return list of all attractions in the database
     */
    public List<Attraction> getAllAttractions() {
        List<Attraction> result = new ArrayList<>();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Attraction attraction = new Attraction(
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                attraction.setId(rs.getInt("id"));
                result.add(attraction);
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
     * Lists all attractions in the database for the given type
     * @param attractionType type of the attractions
     * @return list of all attractions in the database for the given type
     */
    public List<Attraction> getAttractionByType(AttractionType attractionType) {
        List<Attraction> result = new ArrayList<>();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        this.connector.connect();

        try {
            Statement statement = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction WHERE attraction_type_id=" + attractionType.getId();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Attraction attraction = new Attraction(
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                attraction.setId(rs.getInt("id"));
                result.add(attraction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }

        this.connector.closeConnection(null);

        return result;
    }

    /**
     * Looks for the attraction with given id
     * @param id id of the attraction
     * @return desired Attraction object or null if the attraction couldn't be found
     */
    public Attraction getAttractionById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM attraction WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);


            if (rs.next()) {
                AttractionTypeController attractionTypeController = new AttractionTypeController();
                Attraction attraction = new Attraction(
                        rs.getString("name"),
                        rs.getBoolean("status"),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                attraction.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return attraction;
            } else {
                this.connector.closeConnection(rs);
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
     * Updates attraction in the database
     * @param id id of the attraction to update
     * @param name new name of the attraction
     * @param status new status of the attraction
     * @param attractionTypeId new id of the attraction type of the attraction
     */
    public void updateAttraction(int id, String name, Boolean status, int attractionTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE attraction SET name='" + name + "', status=" +
                    status + ", attraction_type_id=" +
                    attractionTypeId + " WHERE id=" + id;
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
     * Removes attraction from the database
     * @param id id of the attraction to be removed
     */
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
