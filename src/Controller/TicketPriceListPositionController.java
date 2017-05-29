package Controller;

import Database.Connector;
import Model.Day;
import Model.DiscountGroup;
import Model.TicketPriceListPosition;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TicketPriceListPositionController {
    private Connector connector;

    public TicketPriceListPositionController() {
        this.connector = new Connector();
    }

    public void createTicketPriceListPosition(BigDecimal price, int ticketPriceListId, int daysId, int discountGroupId, int daytimeId, int attractionTypeId) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO tckt_prc_lst_pos (price, tckt_prc_lst_id, days_id, disc_group_id, daytime_id, attraction_type_id) VALUES (" +
                    price + "," + ticketPriceListId + "," + daysId + "," + discountGroupId + "," +
                    daytimeId + "," + attractionTypeId + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

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
        }
        this.connector.closeConnection(null);
        return result;
    }

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
        }
        this.connector.closeConnection(null);
        return result;
    }

    public TicketPriceListPosition getTicketPriceListPositionById(int id) {
        this.connector.connect();
        AttractionTypeController attractionTypeController = new AttractionTypeController();
        DayController dayController = new DayController();
        DaytimeController daytimeController = new DaytimeController();
        DiscountGroupController discountGroupController = new DiscountGroupController();
        TicketPriceListController ticketPriceListController = new TicketPriceListController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM days WHERE id=" + id;
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
                return position;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

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
