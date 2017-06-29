package Controller;

import Database.Connector;
import Model.DiscountGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with DiscountGroup class
 */
public class DiscountGroupController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public DiscountGroupController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new attraction in the database
     * @param name name of the new group
     * @param discount discount of the new group
     */
    public void createDiscountGroup(String name, float discount) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO disc_group (name, discount) VALUES ('" +
                    name + "', " + discount + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all groups in the database
     * @return list of all groups in the database
     */
    public List<DiscountGroup> getAllDiscountGroups() {
        List<DiscountGroup> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM disc_group";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                DiscountGroup discountGroup = new DiscountGroup(
                        rs.getString("name"),
                        rs.getFloat("discount")
                );
                discountGroup.setId(rs.getInt("id"));
                result.add(discountGroup);
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
     * Looks for the group with given id
     * @param id id of the group
     * @return desired DiscountGroup object or null if the group couldn't be found
     */
    public DiscountGroup getDiscountGroupById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM disc_group WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                DiscountGroup discountGroup = new DiscountGroup(
                        rs.getString("name"),
                        rs.getFloat("discount")
                );
                discountGroup.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return discountGroup;
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
     * Updates group in the database
     * @param id id of the group to update
     * @param name new name of the group
     * @param discount new discount of the group
     */
    public void updateDiscountGroup(int id, String name, float discount) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE disc_group SET name='" +
                    name + "', discount=" + discount + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes group from the database
     * @param id id of the group to be removed
     */
    public void deleteDiscountGroup(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM disc_group WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
