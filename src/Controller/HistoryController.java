package Controller;

import Database.Connector;
import Model.Attraction;
import Model.History;
import Model.Ticket;
import Model.Watch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles database related actions with Attraction class
 */
public class HistoryController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public HistoryController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new history in the database
     * @param entryTime entry time of the new history
     * @param exitTime exit time of the new history
     * @param attraction attraction of the new history
     * @param watch watch of the new history
     */
    public void createHistory(Date entryTime, Date exitTime, Attraction attraction, Watch watch) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String entryTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entryTime);
            String exitTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exitTime);
            String sql = "INSERT INTO history (entry_time, exit_time, attraction_id, watch_id) VALUES ('" +
                    entryTimeFormatted + "', '" + exitTimeFormatted + "', " + attraction.getId() + ", " +
                    watch.getId() + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all histories in the database
     * @return list of all histories in the database
     */
    public List<History> getAllHistories() {
        List<History> result = new ArrayList<>();
        AttractionController attractionController = new AttractionController();
        WatchController watchController = new WatchController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM history";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                History history = new History(
                        rs.getTimestamp("entry_time"),
                        rs.getTimestamp("exit_time"),
                        attractionController.getAttractionById(rs.getInt("attraction_id")),
                        watchController.getWatchById(rs.getInt("watch_id"))
                );
                history.setId(rs.getInt("id"));
                result.add(history);
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
     * Lists all histories in the database for the given ticket
     * @param ticket ticket
     * @return list of all histories in the database for the given ticket
     */
    public List<History> getAllHistoriesForTicket(Ticket ticket) {
        List<History> result = new ArrayList<>();
        AttractionController attractionController = new AttractionController();
        this.connector.connect();
        try {
            Date now = new Date();
            Statement st = this.connector.getConnection().createStatement();
            String ticketStampFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ticket.getStamp());
            String nowFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ticket.getStamp());
            String sql = "SELECT * FROM history WHERE watch_id=" + ticket.getWatch().getId() + " AND entry_time >= timestamp '" +
                    ticketStampFormatted + "' AND (exit_time IS NULL OR exit_time <= timestamp '" + nowFormatted + "')";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                History history = new History(
                        rs.getTimestamp("entry_time"),
                        rs.getTimestamp("exit_time"),
                        attractionController.getAttractionById(rs.getInt("attraction_id")),
                        ticket.getWatch()
                );
                history.setId(rs.getInt("id"));
                result.add(history);
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
     * Looks for the history with given id
     * @param id id of the history
     * @return desired History object or null if the history couldn't be found
     */
    public History getHistoryById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            AttractionController attractionController = new AttractionController();
            WatchController watchController = new WatchController();
            String sql = "SELECT * FROM history WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                History history = new History(
                        rs.getTimestamp("entry_time"),
                        rs.getTimestamp("exit_time"),
                        attractionController.getAttractionById(rs.getInt("attraction_id")),
                        watchController.getWatchById(rs.getInt("watch_id"))
                );
                history.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return history;
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
     * Updates history in the database
     * @param id id of the history to update
     * @param entryTime new entry time of the history
     * @param exitTime new exit time of the history
     * @param attraction new attraction of the history
     * @param watch new watch of the history
     */
    public void updateHistory(int id, Date entryTime, Date exitTime, Attraction attraction, Watch watch) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String entryTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(entryTime);
            String exitTimeFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(exitTime);
            String sql = "UPDATE history SET entry_time='" +
                    entryTimeFormatted + "', exit_time='" + exitTimeFormatted + "', attraction_id=" +
                    attraction.getId() + ", watch_id=" + watch.getId() + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes history from the database
     * @param id id of the history to be removed
     */
    public void deleteHistory(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM history WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all histories in the database for the given attraction and between dates
     * @param attraction attraction
     * @param dateFrom starting date
     * @param dateTo ending date
     * @return list of all histories in the database for the given attraction and between dates
     */
    public List<History> findAllByAttractionAndDateFromAndDateTo(Attraction attraction, Date dateFrom, Date dateTo) {
        List<History> result = new ArrayList<>();
        WatchController watchController = new WatchController();
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateFrom);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTo);
            System.out.println(startDateFormatted);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM history WHERE " +
                    "attraction_id = " + attraction.getId() +
                    " AND entry_time >= '" + startDateFormatted +
                    "' AND exit_time <= '" + endDateFormatted + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                History history = new History(
                        rs.getTimestamp("entry_time"),
                        rs.getTimestamp("exit_time"),
                        attraction,
                        watchController.getWatchById(rs.getInt("watch_id"))
                );
                history.setId(rs.getInt("id"));
                result.add(history);
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
     * Lists all histories in the database between dates
     * @param dateFrom starting date
     * @param dateTo ending date
     * @return list of all histories in the database between dates
     */
    public List<History> findAllByDateFromAndDateTo(Date dateFrom, Date dateTo) {
        List<History> result = new ArrayList<>();
        AttractionController attractionController = new AttractionController();
        WatchController watchController = new WatchController();
        this.connector.connect();
        try {
            String startDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateFrom);
            String endDateFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(dateTo);
            System.out.println(startDateFormatted);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM history WHERE " +
                    "entry_time >= '" + startDateFormatted +
                    "' AND exit_time <= '" + endDateFormatted + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                History history = new History(
                        rs.getTimestamp("entry_time"),
                        rs.getTimestamp("exit_time"),
                        attractionController.getAttractionById(rs.getInt("attraction_id")),
                        watchController.getWatchById(rs.getInt("watch_id"))
                );
                history.setId(rs.getInt("id"));
                result.add(history);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
        return result;
    }
}
