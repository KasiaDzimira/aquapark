package Controller;

import Database.Connector;
import Model.*;
import Utils.TimeUtilities;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Handles database related actions with Ticket class
 */
public class TicketController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public TicketController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new ticket in the database
     * @param stamp timestamp of entry of the new ticket
     * @param stampOut timestamp of exit of the new ticket
     * @param watch watch of the new ticket
     */
    public void createTicket(Date stamp, Date stampOut, Watch watch) {
        this.connector.connect();
        try {
            String stampFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(stamp);
            String stampOutFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(stampOut);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO ticket (stamp, stamp_out, watch_id) VALUES ('" +
                    stampFormatted + "', " + watch.getId() + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all tickets in the database
     * @return list of all tickets in the database
     */
    public List<Ticket> getAllTickets() {
        List<Ticket> result = new ArrayList<>();
        WatchController watchController = new WatchController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM ticket";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Pass pass = null;
                int passId = rs.getInt("pass_id");
                if (passId != 0) {
                    PassController passController = new PassController();
                    pass = passController.getPassById(passId);
                }
                Ticket ticket = new Ticket(
                        rs.getTimestamp("stamp"),
                        rs.getTimestamp("stamp_out"),
                        watchController.getWatchById(rs.getInt("watch_id")),
                        pass
                );
                ticket.setId(rs.getInt("id"));
                result.add(ticket);
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
     * Looks for the ticket with given id
     * @param id id of the ticket
     * @return desired Ticket object or null if the ticket couldn't be found
     */
    public Ticket getTicketById(int id) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM ticket WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Pass pass = null;
                int passId = rs.getInt("pass_id");
                if (passId != 0) {
                    PassController passController = new PassController();
                    pass = passController.getPassById(passId);
                }
                Ticket ticket = new Ticket(
                        rs.getTimestamp("stamp"),
                        rs.getTimestamp("stamp_out"),
                        watchController.getWatchById(rs.getInt("watch_id")),
                        pass
                );
                ticket.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return ticket;
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
     * Updates ticket in the database
     * @param id id of the ticket to update
     * @param stamp new timestamp of entry of the ticket
     * @param stampOut new timestamp of exit of the ticket
     * @param watch new watch of the ticket
     */
    public void updateTicket(int id, Date stamp, Date stampOut, Watch watch) {
        this.connector.connect();
        try {
            String stampFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(stamp);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE ticket SET stamp='" +
                    stampFormatted + "', stamp_out=" + stampOut + "', watch_id=" + watch.getId() + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes ticket from the database
     * @param id id of the ticket to be removed
     */
    public void deleteTicket(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM ticket WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Calculates the price of the ticket
     * Requires existence of the attraction type called "Basic Zone"!
     * @param ticket Ticket object that must have field stamp correctly filled
     * @param day day when the ticket is being bought
     * @param discountGroup discount group of the client that is buying the ticket
     * @param daytime daytime when the ticket is being bought
     * @return price of the ticket
     */
    public BigDecimal calculatePrice(Ticket ticket, Day day, DiscountGroup discountGroup, Daytime daytime) {
        BigDecimal price = new BigDecimal(0.0);
        HistoryController historyController = new HistoryController();
        List<History> result = historyController.getAllHistoriesForTicket(ticket);
        Date now = new Date();

        long minutes = TimeUtilities.getDateDiff(ticket.getStamp(), now, TimeUnit.MINUTES);
        if (minutes > 60) {
            TicketPriceListPositionController ticketPriceListPositionController = new TicketPriceListPositionController();
            TicketPriceListController ticketPriceListController = new TicketPriceListController();
            TicketPriceList currentPriceList = ticketPriceListController.getTicketPriceListForDay(now);
            AttractionTypeController attractionTypeController = new AttractionTypeController();
            AttractionType pool = attractionTypeController.getAttractionTypeByName("Basic Zone");
            price = price.add(ticketPriceListPositionController.getPrice(currentPriceList, day, discountGroup, daytime, pool).divide(new BigDecimal(60), BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(minutes - 60)));
        }

        for (History history : result) {
            if (history.getAttraction().getAttractionType().getName().equals("Basic Zone")) {
                continue;
            }
            Date exitTime = history.getExitTime();
            if (exitTime == null) {
                exitTime = now;
                historyController.updateHistory(history.getId(), history.getEntryTime(), now, history.getAttraction(), history.getWatch());
            }
            long timeDiff = TimeUtilities.getDateDiff(history.getEntryTime(), exitTime, TimeUnit.MINUTES);
            TicketPriceListPositionController ticketPriceListPositionController = new TicketPriceListPositionController();
            TicketPriceListController ticketPriceListController = new TicketPriceListController();
            TicketPriceList currentPriceList = ticketPriceListController.getTicketPriceListForDay(now);
            price = price.add(ticketPriceListPositionController.getPrice(currentPriceList, day, discountGroup, daytime, history.getAttraction().getAttractionType()).divide(new BigDecimal(60), BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(timeDiff - 60)));
        }
        return price;
    }

    /**
     * Looks for the ticket with given watch
     * @param watch watch
     * @return desired Ticket object or null if the ticket couldn't be found
     */
    public Ticket findByWatch(Watch watch) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM ticket WHERE watch_id=" + watch.getId();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Pass pass = null;
                int passId = rs.getInt("pass_id");
                if (passId != 0) {
                    PassController passController = new PassController();
                    pass = passController.getPassById(passId);
                }
                Ticket ticket = new Ticket(
                        rs.getTimestamp("stamp"),
                        rs.getTimestamp("stamp_out"),
                        watchController.getWatchById(rs.getInt("watch_id")),
                        pass
                );
                ticket.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return ticket;
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
     * Looks for the ticket with given watch and between timestamps
     * @param watch watch
     * @param entryTime first timestamp (earlier)
     * @param exitTime second timestamp (later)
     * @return desired Ticket object or null if the ticket couldn't be found
     */
    public Ticket findByWatchAndDatesWithinStamps(Watch watch, Date entryTime, Date exitTime) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entryTime);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exitTime);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM ticket WHERE watch_id=" + watch.getId() +
                    " AND stamp <= '" + startDateFormatted +
                    "' AND stamp_out >= '" + endDateFormatted + "'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Pass pass = null;
                int passId = rs.getInt("pass_id");
                if (passId != 0) {
                    PassController passController = new PassController();
                    pass = passController.getPassById(passId);
                }
                Ticket ticket = new Ticket(
                        rs.getTimestamp("stamp"),
                        rs.getTimestamp("stamp_out"),
                        watchController.getWatchById(rs.getInt("watch_id")),
                        pass
                );
                ticket.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return ticket;
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
}
