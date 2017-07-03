package Controller;

import Database.Connector;
import Model.Daytime;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Handles database related actions with Daytime class
 */
public class DaytimeController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public DaytimeController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new daytime in the database
     * @param name name of the new daytime
     * @param startHour starting hour of the new daytime
     * @param endHour ending hour of the new daytime
     */
    public void createDaytime(String name, Date startHour, Date endHour) {
        this.connector.connect();
        try {
            String startHourFormatted = new SimpleDateFormat("HH:mm:ss").format(startHour);
            String endHourFormatted = new SimpleDateFormat("HH:mm:ss").format(endHour);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO daytime (name, start_hour, end_hour) VALUES ('" +
                    name + "', '" + startHourFormatted + "', '" + endHourFormatted + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all daytimes in the database
     * @return list of all daytimes in the database
     */
    public List<Daytime> getAllDaytimes() {
        List<Daytime> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM daytime";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Daytime daytime = new Daytime(
                        rs.getString("name"),
                        new Date(rs.getTime("start_hour").getTime()),
                        new Date(rs.getTime("end_hour").getTime())
                );
                daytime.setId(rs.getInt("id"));
                result.add(daytime);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return result;
    }

    /**
     * Looks for the daytime with given id
     * @param id id of the daytime
     * @return desired Daytime object or null if the daytime couldn't be found
     */
    public Daytime getDaytimeById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM daytime WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Daytime daytime = new Daytime(
                        rs.getString("name"),
                        rs.getTime("start_hour"),
                        rs.getTime("end_hour")
                );
                daytime.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return daytime;
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
     * Looks for the daytime for given hour
     * @param hour hour
     * @return desired Daytime object or null if the daytime couldn't be found
     */
    public Daytime getDaytimeForHour(Date hour) {
        this.connector.connect();

        try {
            String hourFormatted = new SimpleDateFormat("HH:mm:ss").format(hour);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM daytime WHERE start_hour <'" + hourFormatted + "' AND end_hour > '" + hourFormatted + "'";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Daytime daytime = new Daytime(
                        rs.getString("name"),
                        rs.getTime("start_hour"),
                        rs.getTime("end_hour")
                );
                daytime.setId(rs.getInt("id"));
                this.connector.closeConnection(rs);
                return daytime;
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
     * Updates daytime in the database
     * @param id id of the daytime to update
     * @param name new name of the dayime
     * @param startHour new starting hour of the daytime
     * @param endHour new ending hour of the daytime
     */
    public void updateDaytime(int id, String name, Date startHour, Date endHour) {
        this.connector.connect();
        try {
            String startHourFormatted = new SimpleDateFormat("HH:mm:ss").format(startHour);
            String endHourFormatted = new SimpleDateFormat("HH:mm:ss").format(endHour);
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE daytime SET name='" + name + "', start_hour='" + startHourFormatted + "', end_hour='" + endHourFormatted + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes daytime from the database
     * @param id id of the daytime to be removed
     */
    public void deleteDaytime(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM daytime WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
