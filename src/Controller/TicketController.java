package Controller;

import Database.Connector;
import Model.Ticket;
import Model.Watch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
}
