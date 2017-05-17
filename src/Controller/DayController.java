package Controller;

import Database.Connector;
import Model.Attraction;
import Model.Day;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DayController {
    private Connector connector;

    public DayController() {
        this.connector = new Connector();
    }

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

    public List<Day> getAllDays() {
        List<Day> result = new ArrayList<>();
        this.connector.connect();
        try {
            Statement st = this.connector.getConnection().createStatement();
            String sql = "SELECT * FROM days";
            ResultSet rs = st.executeQuery(sql);

            if (rs.next()) {
                Day day = new Day(
                        rs.getString("name")
                );
                day.setId(rs.getInt("id"));
                result.add(day);
            }
            System.out.println("Query has been executed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return result;
    }

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
                return day;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.connector.closeConnection(null);
        return null;
    }

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
