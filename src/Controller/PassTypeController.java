package Controller;

import Database.Connector;
import Model.PassType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with PassType class
 */
public class PassTypeController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public PassTypeController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new pass type in the database
     * @param name name of the new pass type
     */
    public void createPassType(String name) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO pass_type (name) VALUES ('" +
                    name + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all pass types in the database
     * @return list of all pass types in the database
     */
    public List<PassType> getAllPassTypes() {
        List<PassType> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_type";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                PassType passType = new PassType(
                        rs.getString("name")
                );
                passType.setId(rs.getInt("id"));
                result.add(passType);
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
     * Looks for the pass type with given id
     * @param id id of the pass type
     * @return desired PassType object or null if the pass type couldn't be found
     */
    public PassType getPassTypeById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_type WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                PassType passType = new PassType(
                        rs.getString("name")
                );
                passType.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return passType;
            } else {
                this.connector.closeConnection(null);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

    /**
     * Updates pass type in the database
     * @param id id of the pass type to update
     * @param name new name of the pass type
     */
    public void updatePassType(int id, String name) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass_type SET name='" +
                    name + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes pass type from the database
     * @param id id of the pass type to be removed
     */
    public void deletePassType(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM pass_type WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
