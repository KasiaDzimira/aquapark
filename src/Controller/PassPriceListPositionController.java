package Controller;

import Database.Connector;
import Model.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with PassPriceListPosition class
 */
public class PassPriceListPositionController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public PassPriceListPositionController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new position in the database
     * @param price price for the new position
     * @param passPriceListId id of the price list for the new position
     * @param discountGroupId id of the group for the new position
     * @param passTypeId id of the pass type for the new position
     */
    public void createPassPriceListPosition(BigDecimal price, int passPriceListId, int discountGroupId, int passTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO pass_prc_lst_pos (price, pass_prc_lst_id, disc_group_id, pass_type_id, attraction_type_id) VALUES (" +
                    price + "," + passPriceListId + "," + discountGroupId + "," + passTypeId + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all positions in the database
     * @return list of all positions in the database
     */
    public List<PassPriceListPosition> getAllPassPriceListPositions() {
        List<PassPriceListPosition> result = new ArrayList<>();
        PassTypeController passTypeController = new PassTypeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        PassPriceListController passPriceListController = new PassPriceListController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_prc_lst_pos";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                PassPriceListPosition position = new PassPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        passPriceListController.getPassPriceListById(rs.getInt("pass_prc_lst_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id"))
                );
                position.setId(rs.getInt("id"));
                result.add(position);
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
     * Lists all positions in the database for the given price list
     * @param passPriceListId id of the price list
     * @return list of all positions in the database for the given price list
     */
    public List<PassPriceListPosition> getAllPassPriceListPositionsByPassPriceList(int passPriceListId) {
        List<PassPriceListPosition> result = new ArrayList<>();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        PassTypeController passTypeController = new PassTypeController();
        PassPriceListController passPriceListController = new PassPriceListController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_prc_lst_pos WHERE pass_prc_lst_id=" + passPriceListId;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                PassPriceListPosition position = new PassPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        passPriceListController.getPassPriceListById(rs.getInt("pass_prc_lst_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id"))
                );
                position.setId(rs.getInt("id"));
                result.add(position);
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
     * Looks for the position with given id
     * @param id id of the position
     * @return desired PassPriceListPosition object or null if the position couldn't be found
     */
    public PassPriceListPosition getPassPriceListPositionById(int id) {
        this.connector.connect();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        PassTypeController passTypeController = new PassTypeController();
        PassPriceListController passPriceListController = new PassPriceListController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM pass_prc_lst_pos WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                PassPriceListPosition position = new PassPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        passPriceListController.getPassPriceListById(rs.getInt("pass_prc_lst_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        passTypeController.getPassTypeById(rs.getInt("pass_type_id"))
                );
                position.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return position;
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
     * Reads price from the position for given parameters
     * @param priceList price list
     * @param group discount group
     * @param passType type of the pass
     * @return price from the position for given parameters
     */
    public BigDecimal getPrice(PassPriceList priceList, DiscountGroup group, PassType passType) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT price FROM pass_prc_lst_pos WHERE pass_prc_lst_id=" + priceList.getId() +
                    " AND disc_group_id=" + group.getId() + " AND pass_type_id=" + passType.getId();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                BigDecimal price = new BigDecimal(rs.getString("price"));
                this.connector.closeConnection(rs);
                return price;
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
     * Updates position in the database
     * @param id id of the position to be updated
     * @param price new price for the position
     */
    public void updatePassPriceListPosition(int id, BigDecimal price) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass_prc_lst_pos SET price=" +
                    price + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Updates position in the database
     * @param id id of the position to be updated
     * @param passPriceListId id of the new price list for the position
     */
    public void updatePassPriceListPosition(int id, int passPriceListId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE pass_prc_lst_pos SET pass_prc_lst_id=" +
                    passPriceListId + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes position from the database
     * @param id id of the position to be removed
     */
    public void deletePassPriceListPosition(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM pass_prc_lst_pos WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
