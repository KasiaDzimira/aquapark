package Controller;

import Database.Connector;
import Model.Day;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles database related actions with Day class
 */
public class DayController {

    /**
     * Responsible for connection to the database
     */
    private Connector connector;

    /**
     * Constructor without parametric
     */
    public DayController() {
        this.connector = new Connector();
    }

    /**
     * Creates a new day in the database
     * @param name name of the new day
     */
    public void createDay(String name) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "INSERT INTO days (name) VALUES ('" +
                    name + "')";
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Lists all days in the database
     * @return list of all days in the database
     */
    public List<Day> getAllDays() {
        List<Day> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM days";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Day day = new Day(
                        rs.getString("name")
                );
                day.setId(rs.getInt("id"));
                result.add(day);
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
     * Looks for the day with given id
     * @param id id of the day
     * @return desired Day object or null if the day couldn't be found
     */
    public Day getDayById(int id) {
        this.connector.connect();

        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM days WHERE id=" + id;
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Day day = new Day(
                        rs.getString("name")
                );
                day.setId(rs.getInt("id"));
                this.connector.closeConnection(null);
                return day;
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
     * Updates day in the database
     * @param id id of the day to update
     * @param name new name of the day
     */
    public void updateDay(int id, String name) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "UPDATE days SET name='" +
                    name + "' WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }

    /**
     * Removes day from the database
     * @param id id of the day to be removed
     */
    public void deleteDay(int id) {
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "DELETE FROM days WHERE id=" + id;
            st.executeUpdate(sql);
            this.connector.getConnection().commit();
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
    }
}
