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
 * Handles database related actions with TicketPriceListPosition class
 */
public class TicketPriceListPositionController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public TicketPriceListPositionController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new position in the database
     * @param price price for the new position
     * @param ticketPriceListId id of the price list for the new position
     * @param daysId id of the day for the new position
     * @param discountGroupId id of the group for the new position
     * @param daytimeId if of the daytime for the new position
     * @param attractionTypeId id of the attraction type for the new position
     */
    public void createTicketPriceListPosition(BigDecimal price, int ticketPriceListId, int daysId, int discountGroupId, int daytimeId, int attractionTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (" +
                    price + "," + ticketPriceListId + "," + daysId + "," + discountGroupId + "," +
                    daytimeId + "," + attractionTypeId + ")";
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
    public List<TicketPriceListPosition> getAllTicketPriceListPositions() {
        List<TicketPriceListPosition> result = new ArrayList<>();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        DayController dayController = new DayController();
        DaytimeController daytimeController = new DaytimeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        TicketPriceListController ticketPriceListController = new TicketPriceListController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst_pos";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                TicketPriceListPosition position = new TicketPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        ticketPriceListController.getTicketPriceListById(rs.getInt("tckt_prc_lst_id")),
                        dayController.getDayById(rs.getInt("days_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        daytimeController.getDaytimeById(rs.getInt("daytime_id")),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
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
     * @param ticketPriceListId id of the price list
     * @return list of all positions in the database for the given price list
     */
    public List<TicketPriceListPosition> getAllTicketPriceListPositionsByTicketPriceList(int ticketPriceListId) {
        List<TicketPriceListPosition> result = new ArrayList<>();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        DayController dayController = new DayController();
        DaytimeController daytimeController = new DaytimeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        TicketPriceListController ticketPriceListController = new TicketPriceListController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst_pos WHERE tckt_prc_lst_id=" + ticketPriceListId;
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                TicketPriceListPosition position = new TicketPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        ticketPriceListController.getTicketPriceListById(rs.getInt("tckt_prc_lst_id")),
                        dayController.getDayById(rs.getInt("days_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        daytimeController.getDaytimeById(rs.getInt("daytime_id")),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
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
     * @return desired TicketPriceListPosition object or null if the position couldn't be found
     */
    public TicketPriceListPosition getTicketPriceListPositionById(int id) {
        this.connector.connect();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        DayController dayController = new DayController();
        DaytimeController daytimeController = new DaytimeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        TicketPriceListController ticketPriceListController = new TicketPriceListController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst_pos WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                TicketPriceListPosition position = new TicketPriceListPosition(
                        new BigDecimal(rs.getString("price")),
                        ticketPriceListController.getTicketPriceListById(rs.getInt("tckt_prc_lst_id")),
                        dayController.getDayById(rs.getInt("days_id")),
                        discountGroupController.getDiscountGroupById(rs.getInt("disc_group_id")),
                        daytimeController.getDaytimeById(rs.getInt("daytime_id")),
                        attractionTypeController.getAttractionTypeById(rs.getInt("attraction_type_id"))
                );
                position.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return position;
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
     * Reads price from the position for given parameters
     * @param priceList price list
     * @param day day
     * @param group discount group
     * @param daytime daytime
     * @param attractionType type of the attraction
     * @return price from the position for given parameters
     */
    public BigDecimal getPrice(TicketPriceList priceList, Day day, DiscountGroup group, Daytime daytime, AttractionType attractionType) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT price FROM tckt_prc_lst_pos WHERE tckt_prc_lst_id=" + priceList.getId() +
                    " AND days_id=" + day.getId() + " AND disc_group_id=" + group.getId() + " AND daytime_id=" +
                    daytime.getId() + " AND attraction_type_id="
                    + attractionType.getId();
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
    public void updateTicketPriceListPosition(int id, BigDecimal price) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE tckt_prc_lst_pos SET price=" +
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
     * @param ticketPriceListId id of the new price list for the position
     */
    public void updateTicketPriceListPosition(int id, int ticketPriceListId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE tckt_prc_lst_pos SET tckt_prc_lst_id=" +
                    ticketPriceListId + " WHERE id=" + id;
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
    public void deleteTicketPriceListPosition(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM tckt_prc_lst_pos WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
