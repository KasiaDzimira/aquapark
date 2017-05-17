package Controller;

import Database.Connector;
import Model.Day;
import Model.DiscountGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DiscountGroupController {
    private Connector connector;

    public DiscountGroupController() {
        this.connector = new Connector();
    }

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

    public List<DiscountGroup> getAllDiscountGroups() {
        List<DiscountGroup> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM disc_group";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
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
        }
        this.connector.closeConnection(null);
        return result;
    }

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
                return discountGroup;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

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
