package Controller;

import Database.Connector;
import Model.TicketPriceList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles database related actions with TicketPriceList class
 */
public class TicketPriceListController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public TicketPriceListController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new price list in the database
     * @param startDate starting date of the new price list
     * @param endDate ending date of the new price list
     */
    public void createTicketPriceList(Date startDate, Date endDate) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO tckt_prc_lst (start_date, end_date) VALUES ('" +
                    startDateFormatted + "', '" + endDateFormatted + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all price lists in the database
     * @return list of all price lists in the database
     */
    public List<TicketPriceList> getAllTicketPriceLists() {
        List<TicketPriceList> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                TicketPriceList ticketPriceList = new TicketPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                ticketPriceList.setId(rs.getInt("id"));
                result.add(ticketPriceList);
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
     * Looks for the price list with given id
     * @param id id of the price list
     * @return desired TicketPriceList object or null if the price list couldn't be found
     */
    public TicketPriceList getTicketPriceListById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM tckt_prc_lst WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                TicketPriceList ticketPriceList = new TicketPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                ticketPriceList.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return ticketPriceList;
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
     * Looks for the price list with given day
     * @param day day for which the price list is valid
     * @return desired TicketPriceList object or null if the price list couldn't be found
     */
    public TicketPriceList getTicketPriceListForDay(Date day) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String dayFormatted = new SimpleDateFormat("yyyy-MM-dd").format(day);
            String sql = "SELECT * FROM tckt_prc_lst WHERE start_date <='" + dayFormatted + "'::date AND end_date >='" +
                    dayFormatted + "'::date";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                TicketPriceList ticketPriceList = new TicketPriceList(
                        rs.getDate("start_date"),
                        rs.getDate("end_date")
                );
                ticketPriceList.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return ticketPriceList;
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
     * Updates price list in the database
     * @param id id of the price list to update
     * @param startDate new starting date of the price list
     * @param endDate new ending date of the price list
     */
    public void updateTicketPriceList(int id, Date startDate, Date endDate) {
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE tckt_prc_lst SET start_date='" + startDateFormatted + "', end_date='" + endDateFormatted + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes price list from the database
     * @param id id of the price list to be removed
     */
    public void deleteTicketPriceList(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM tckt_prc_lst WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
