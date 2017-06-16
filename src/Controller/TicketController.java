package Controller;

import Database.Connector;
import Model.Attraction;
import Model.History;
import Model.Ticket;
import Model.Watch;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TicketController {
    private Connector connector;

    public TicketController() {
        this.connector = new Connector();
    }

    public void createTicket(Date stamp, Watch watch) {
        this.connector.connect();
        try {
            String stampFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(stamp);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO ticket (stamp, watch_id) VALUES ('" +
                    stampFormatted + "', " + watch.getId() + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> result = new ArrayList<>();
        WatchController watchController = new WatchController();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM ticket";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getDate("stamp"),
                        watchController.getWatchById(rs.getInt("watch_id"))
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

    public Ticket getTicketById(int id) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM ticket WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getDate("stamp"),
                        watchController.getWatchById(rs.getInt("watch_id"))
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

    public void updateTicket(int id, Date stamp, Watch watch) {
        this.connector.connect();
        try {
            String stampFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(stamp);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE ticket SET stamp='" +
                    stampFormatted + "', watch_id=" + watch.getId() + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

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
     * Get a diff between two dates
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    private static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }

    public BigDecimal countPrice(Ticket ticket) {
        BigDecimal price = new BigDecimal(0.0);
        this.connector.connect();
        List<History> result = new ArrayList<>();
        AttractionController attractionController = new AttractionController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String ticketStampFormatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ticket.getStamp());
            String sql = "SELLECT * FROM history WHERE watch_id=" + ticket.getWatch().getId() + " AND entry_time > timestamp '" +
                    ticketStampFormatted + "'";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                History history = new History(
                        rs.getDate("entry_time"),
                        rs.getDate("exit_time"),
                        attractionController.getAttractionById(rs.getInt("attraction_id")),
                        ticket.getWatch()
                );
                history.setId(rs.getInt("id"));
                result.add(history);
            }

            Date now = new Date();
            long minutes = getDateDiff(ticket.getStamp(), now, TimeUnit.MINUTES);
            if (minutes > 60) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        return price;
    }

    public Ticket findByWatch(Watch watch) {
        this.connector.connect();
        WatchController watchController = new WatchController();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM ticket WHERE watch_id=" + watch.getId();
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Ticket ticket = new Ticket(
                        rs.getDate("stamp"),
                        watchController.getWatchById(rs.getInt("watch_id"))
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
