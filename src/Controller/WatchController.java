package Controller;

import Database.Connector;
import Model.Watch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with Watch class
 */
public class WatchController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public WatchController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new watch in the database
     * @param status status of the new watch
     */
    public void createWatch(int status) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO watch (status) VALUES (" +
                    status + ")";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all watches in the database
     * @return list of all watches in the database
     */
    public List<Watch> getAllWatches() {
        List<Watch> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM watch";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Watch watch = new Watch(
                        rs.getInt("status")
                );
                watch.setId(rs.getInt("id"));
                result.add(watch);
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
     * Looks for the watch with given id
     * @param id id of the watch
     * @return desired Watch object or null if the watch couldn't be found
     */
    public Watch getWatchById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM watch WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Watch watch = new Watch(
                        rs.getInt("status")
                );
                watch.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return watch;
            } else {
                this.connector.closeConnection(rs);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

    /**
     * Updates watch in the database
     * @param id id of the watch to update
     * @param status new status of the watch
     */
    public void updateWatch(int id, int status) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE watch SET status=" +
                    status + " WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes watch from the database
     * @param id id of the watch to be removed
     */
    public void deleteWatch(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM watch WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
            this.connector.closeConnection(null);
        }
        this.connector.closeConnection(null);
    }

    /**
     * Looks for first watch that is not in use and not damaged
     * @return Watch object that is not in use and not damaged
     */
    public Watch getFreeWatch() {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM watch WHERE status=1 LIMIT 1";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Watch watch = new Watch(
                        rs.getInt("status")
                );
                watch.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return watch;
            } else {
                this.connector.closeConnection(rs);
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }
}
